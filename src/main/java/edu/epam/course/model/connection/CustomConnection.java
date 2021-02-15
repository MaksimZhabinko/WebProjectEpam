//package edu.epam.project.model.connection;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Properties;
//
//public class CustomConnection {
//    private static final String DATABASE_PROPERTIES = "database.properties";
//    private static final String URL = "url";
//    private static final String USERNAME = "username";
//    private static final String PASSWORD = "password";
//    private static final String DRIVER = "driver";
//
//    public static Connection getConnection() {
//        try {
//            Properties properties = getProperties(DATABASE_PROPERTIES);
//            String driver = properties.getProperty(DRIVER);
//            String url = properties.getProperty(URL);
//            String username = properties.getProperty(USERNAME);
//            String password = properties.getProperty(PASSWORD);
//            Class.forName(driver);
//            Connection connection = DriverManager.getConnection(url, username, password);
//            return connection;
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static Properties getProperties(String propertiesFile) {
//        try {
//            ClassLoader classLoader = CustomConnection.class.getClassLoader();
//            InputStream inputStream = classLoader.getResourceAsStream(propertiesFile);
//            Properties properties = new Properties();
//            properties.load(inputStream);
//            return properties;
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException();
//        }
//    }
//}
