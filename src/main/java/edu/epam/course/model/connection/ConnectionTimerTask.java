package edu.epam.course.model.connection;

import edu.epam.course.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionTimerTask extends TimerTask {
    private static final Logger logger = LogManager.getLogger(ConnectionTimerTask.class);
    private static final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        ConnectionPool instance = ConnectionPool.getInstance();
        while (instance.getSize() < ConnectionPool.DEFAULT_POOL_SIZE) {
            try {
                Connection connection = ConnectionCreator.getConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                instance.freeConnection.offer(proxyConnection); // todo нужно так делать?
            } catch (SQLException e) {
                logger.fatal(e);
                throw new RuntimeException(e);
            }
        }
        lock.unlock();
    }
}
