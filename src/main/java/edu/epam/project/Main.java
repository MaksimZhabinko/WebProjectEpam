package edu.epam.project;

import edu.epam.project.connector.CustomConnection;
import edu.epam.project.dao.impl.UserDaoImpl;
import edu.epam.project.entity.RoleType;
import edu.epam.project.entity.User;
import edu.epam.project.exception.DaoException;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.UserServiceImpl;
import edu.epam.project.validator.UserValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Optional;

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









//        String updateUser = "UPDATE rename_base.users SET password = ?, user_name = ? WHERE user_id = ?";
//        try (Connection connection = CustomConnection.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(updateUser)) {
//            preparedStatement.setString(1, "jkrbkvjrbvkbsjvbsfbvksbfkvnsjkvks");
//            preparedStatement.setString(2, "qwertyuiop");
//            preparedStatement.setInt(3, 2);
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        }
//        System.out.println("ok");
    }
}
