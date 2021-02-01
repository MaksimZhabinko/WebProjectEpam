package edu.epam.project.model.service;

import edu.epam.project.model.entity.User;
import edu.epam.project.exception.ServiceException;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(long id);
    Optional<User> findUserByEmail(String email) throws ServiceException;
    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException;
    boolean addUser(User user, String password) throws ServiceException;
}
