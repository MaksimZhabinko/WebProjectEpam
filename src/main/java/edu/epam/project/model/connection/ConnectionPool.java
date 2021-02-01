package edu.epam.project.model.connection;

import edu.epam.project.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public enum ConnectionPool {
    INSTANCE;

    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final String DATABASE_PROPERTIES = "database.properties";
    private static final String URL = "url";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String DRIVER = "driver";
    private static final int DEFAULT_POOL_SIZE = 8;

    private final BlockingQueue<ProxyConnection> freeConnection;
    private final Queue<ProxyConnection> givenAwayConnection;

    ConnectionPool() {
        freeConnection = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        givenAwayConnection = new ArrayDeque<>();
        initializePool();
    }

    public ProxyConnection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = freeConnection.take();
            givenAwayConnection.offer(proxyConnection);
        } catch (InterruptedException e) {
            logger.error("The connection is not received " + e);
        }
        return proxyConnection;
    }

    public void releaseConnection(Connection connection) throws ConnectionException {
        if (connection instanceof ProxyConnection) {
            givenAwayConnection.remove(connection);
            freeConnection.offer((ProxyConnection) connection);
        } else {
            throw new ConnectionException("Invalid connection type passed");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnection.take().reallyClose();
            } catch (SQLException | InterruptedException e) {
                logger.error("The pool was not destroyed " + e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Can not deregister driver: " + e);
            }
        });
    }

    private void initializePool() {
        ClassLoader classLoader = ConnectionPool.class.getClassLoader();
        Properties properties = new Properties();
        try {
            InputStream inputStream = classLoader.getResourceAsStream(DATABASE_PROPERTIES);
            properties.load(inputStream);
            String url = properties.getProperty(URL);
            String driver = properties.getProperty(DRIVER);
            String username = properties.getProperty(USERNAME);
            String password = properties.getProperty(PASSWORD);
            Class.forName(driver);
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(url, username, password);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnection.offer(proxyConnection);
            }
        } catch (ClassNotFoundException | MissingResourceException | IOException | SQLException e) {
            logger.error("Error of file: " + e);
            throw new RuntimeException(e);
        }
    }
}
