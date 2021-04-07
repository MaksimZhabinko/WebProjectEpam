package edu.epam.course.model.connection;

import edu.epam.course.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type Connection timer task.
 */
public class ConnectionTimerTask extends TimerTask {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(ConnectionTimerTask.class);
    /**
     * The constant lock.
     */
    private static final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            lock.lock();
            ConnectionPool.timeTaskIsWork.set(true);
            ConnectionPool instance = ConnectionPool.getInstance();
            while (instance.getSize() < ConnectionPool.DEFAULT_POOL_SIZE) {
                Connection connection = ConnectionCreator.getConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                instance.freeConnection.offer(proxyConnection);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.timeTaskIsWork.set(false);
            lock.unlock();
        }
    }
}
