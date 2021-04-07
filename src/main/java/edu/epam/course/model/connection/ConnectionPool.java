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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type Connection pool.
 */
public class ConnectionPool {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private static final Timer timerTask = new Timer();
    /**
     * The Default pool size.
     */
    static final int DEFAULT_POOL_SIZE = 8;
    /**
     * The Free connection.
     */
    final BlockingQueue<ProxyConnection> freeConnection;
    private final Queue<ProxyConnection> givenAwayConnection;
    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static final Lock lock = new ReentrantLock();
    /**
     * The Time task is work.
     */
    static final AtomicBoolean timeTaskIsWork = new AtomicBoolean(false);

    private ConnectionPool() {
        freeConnection = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        givenAwayConnection = new ArrayDeque<>();
        initializePool();
        timerTask.schedule(new ConnectionTimerTask(), 3600000, 3600000); // через час и каждый час
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ConnectionPool getInstance() {
        if (!isInitialized.get()) {
            lock.lock();
            if (instance == null) {
                instance = new ConnectionPool();
                isInitialized.set(true);
            }
            lock.unlock();
        }
        return instance;
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        while (timeTaskIsWork.get()) {
            waitingOneSecond();
        }
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = freeConnection.take();
            givenAwayConnection.offer(proxyConnection);
        } catch (InterruptedException e) {
            logger.error("The connection is not received " + e);
            Thread.currentThread().interrupt();
        }
        return proxyConnection;
    }

    /**
     * Release connection.
     *
     * @param connection the connection
     * @throws ConnectionException the connection exception
     */
    public void releaseConnection(Connection connection) throws ConnectionException {
        while (timeTaskIsWork.get()) {
            waitingOneSecond();
        }
        if (connection instanceof ProxyConnection) {
            givenAwayConnection.remove(connection);
            freeConnection.offer((ProxyConnection) connection);
        } else {
            logger.error("Connection is not proxy or null!");
            throw new ConnectionException("Invalid connection type passed");
        }
    }

    /**
     * Destroy pool.
     */
    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnection.take().reallyClose();
            } catch (SQLException | InterruptedException e) {
                logger.error("The pool was not destroyed " + e);
                Thread.currentThread().interrupt();
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

    /**
     * Initialize pool.
     */
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

    /**
     * Gets size.
     *
     * @return the size
     */
    int getSize() {
        return freeConnection.size() + givenAwayConnection.size();
    }

    private static void waitingOneSecond() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            logger.error(e);
            Thread.currentThread().interrupt();
        }
    }
}
