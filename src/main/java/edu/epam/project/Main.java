package edu.epam.project;

import edu.epam.project.dao.connector.CustomConnection;
import edu.epam.project.entity.RoleType;
import edu.epam.project.exception.DaoException;
import edu.epam.project.validator.UserValidator;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws DaoException {
//        String pass = "Maksman14789632@";
//        String string = Base64.getEncoder().encodeToString(pass.getBytes());
//        System.out.println(string);

//        byte[] decode = Base64.getDecoder().decode(string);
//        String str = new String(decode);
//        System.out.println(str);

//        System.out.println(email.equals(str));
//        System.out.println(string.equals("TWFrc21hbjE0Nzg5NjMy"));
//        UserDaoImpl userDao = new UserDaoImpl();
//        UserServiceImpl userService = new UserServiceImpl();
//        Optional<User> user = userService.findUserByUserName("aNti");
//        System.out.println(user.get().toString());


        String role = "User";
        RoleType user = RoleType.valueOf(role.toUpperCase());


        try {
            Driver driver = new com.mysql.jdbc.Driver();
        } catch (SQLException e) {
            System.out.println("Unable to load driver class.");
            e.printStackTrace();
        }
        String URL = "jdbc:mysql://localhost/test";
        String USER = "root";
        String PASSWORD = "Dassik5550";
//        String sql = "INSERT INTO `users` (`email`, `name`, `surname`, `password`, `role`, `enabled`)\n" +
//                " VALUES (?, ?, ?, ?, ?, ?)";
        String sql = "";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "maxim.ffffff@mail.ru");
            preparedStatement.setString(2, "Maksim");
            preparedStatement.setString(3, "Zhabinko");
            preparedStatement.setString(4, "TWFrc21hbjE0Nzg5NjMyQA==");
            preparedStatement.setString(5,  "user");
            preparedStatement.setBoolean(6,  true);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("OK");
    }
}
