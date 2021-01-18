package edu.epam.project.service;

import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;

import java.util.Optional;

public interface UserService {
    boolean isValidEmail(String email);
    boolean isValidNameAndSurname(String name, String surname);
    boolean isValidPasswordAndRepeatPassword(String password, String repeatPassword);

    boolean isExistUser(String email, String password) throws ServiceException;
    boolean isExistUser(String email) throws ServiceException;

    Optional<User> findUserById(long id);
    Optional<User> findUserByEmail(String email) throws ServiceException;
    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException;
    boolean addUser(User user, String password) throws ServiceException;
}
