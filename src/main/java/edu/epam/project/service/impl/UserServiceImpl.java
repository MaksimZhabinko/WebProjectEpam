package edu.epam.project.service.impl;

import edu.epam.project.dao.UserDao;
import edu.epam.project.dao.impl.UserDaoImpl;
import edu.epam.project.entity.User;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.UserService;
import edu.epam.project.util.PasswordEncryption;
import edu.epam.project.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final UserValidator userValidator = new UserValidator();
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public boolean isValidEmail(String email) {
        return userValidator.isValidEmail(email);
    }

    @Override
    public boolean isValidNameAndSurname(String name, String surname) {
        return userValidator.isValidNameAndSurname(name, surname);
    }

    @Override
    public boolean isValidPasswordAndRepeatPassword(String password, String repeatPassword) {
        boolean isCorrect = false;
        if (userValidator.isValidPassword(password) && password.equals(repeatPassword)) {
            isCorrect = true;
        }
        return isCorrect;
    }

    @Override
    public boolean isExistUser(String email, String password) throws ServiceException {
        Optional<User> user = findUserByEmailAndPassword(email, password);
        boolean isExist = false;
        if (user.isPresent()) {
            isExist = true;
        }
        return isExist;
    }

    @Override
    public boolean isExistUser(String email) throws ServiceException {
        Optional<User> user = findUserByEmail(email);
        boolean isExist = false;
        if (user.isPresent()) {
           isExist = true;
        }
        return isExist;
    }

    @Override
    public Optional<User> findUserById(long id) {
        return Optional.empty();
    }


    @Override
    public Optional<User> findUserByEmail(String email) throws ServiceException {
        try {
            Optional<User> user = userDao.findByEmail(email);
            return user;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException {
        try {
            String encoderPassword = PasswordEncryption.getEncoder(password);
            Optional<User> user = userDao.findByEmailAndPassword(email, encoderPassword);
            return user;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addUser(User user, String password) throws ServiceException {
        try {
            String encoderPassword = PasswordEncryption.getEncoder(password);
            boolean update = userDao.add(user, encoderPassword);
            return update;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
