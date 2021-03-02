package edu.epam.course.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.Properties;

public final class ConnectionCreator {
    private static final Logger logger = LogManager.getLogger(ConnectionCreator.class);
    private static final String DATABASE_PROPERTIES = "database.properties";
    private static final String URL = "url";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String DRIVER = "driver";
    private static final String DATABASE_URL;
    private static final String DATABASE_USERNAME;
    private static final String DATABASE_PASSWORD;

    static {
        ClassLoader classLoader = ConnectionPool.class.getClassLoader();
        Properties properties = new Properties();
        try {
            InputStream inputStream = classLoader.getResourceAsStream(DATABASE_PROPERTIES);
            properties.load(inputStream);
            DATABASE_URL = properties.getProperty(URL);
            String driver = properties.getProperty(DRIVER);
            DATABASE_USERNAME = properties.getProperty(USERNAME);
            DATABASE_PASSWORD = properties.getProperty(PASSWORD);
            Class.forName(driver);
        } catch (ClassNotFoundException | MissingResourceException | IOException e) {
            logger.fatal("fatal error: config file " + e);
            throw new RuntimeException(e);
        }
    }

    private ConnectionCreator() {
    }

    protected static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    }
}
