package edu.epam.course.model.connection;

import edu.epam.course.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Timer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool { // todo посмотрите ConnectionPool getConnection и releaseConnection и ConnectionTimerTask
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private static final Timer timerTask = new Timer();
    static final int DEFAULT_POOL_SIZE = 8;
    final BlockingQueue<ProxyConnection> freeConnection;
    private final Queue<ProxyConnection> givenAwayConnection;

    private ConnectionPool() {
        freeConnection = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        givenAwayConnection = new ArrayDeque<>();
        initializePool();
        timerTask.schedule(new ConnectionTimerTask(), 3600000, 3600000); // через час и каждый час
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public ProxyConnection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = freeConnection.take();
            givenAwayConnection.offer(proxyConnection);
        } catch (InterruptedException e) {
            logger.fatal("The connection is not received " + e);
//            Thread.currentThread().interrupt(); todo зачем он тут?
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
//                Thread.currentThread().interrupt(); // todo remove
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

    void initializePool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = ConnectionCreator.getConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnection.offer(proxyConnection);
            } catch (SQLException e) {
                logger.fatal(e);
                throw new RuntimeException();
            }
        }
    }

    int getSize() {
        return freeConnection.size() + givenAwayConnection.size();
    }
}
