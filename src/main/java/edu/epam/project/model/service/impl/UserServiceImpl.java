package edu.epam.project.model.service.impl;

import edu.epam.project.model.dao.UserDao;
import edu.epam.project.model.dao.impl.UserDaoImpl;
import edu.epam.project.model.entity.User;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.model.service.UserService;
import edu.epam.project.util.PasswordEncryption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private UserDao userDao = new UserDaoImpl();

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
            boolean update = userDao.addUser(user, encoderPassword);
            return update;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
