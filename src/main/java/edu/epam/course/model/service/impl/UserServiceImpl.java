package edu.epam.course.model.service.impl;

import edu.epam.course.exception.EmailException;
import edu.epam.course.model.dao.UserDao;
import edu.epam.course.model.dao.impl.UserDaoImpl;
import edu.epam.course.model.entity.User;
import edu.epam.course.exception.DaoException;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.service.UserService;
import edu.epam.course.util.MailSender;
import edu.epam.course.util.PasswordEncryption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.UUID;

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
        boolean isAdd;
        try {
            String encoderPassword = PasswordEncryption.getEncoder(password);
            isAdd = userDao.addUser(user, encoderPassword);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isAdd;
    }

    @Override
    public boolean forgotUserPassword(User user) throws ServiceException {
        String password = UUID.randomUUID().toString();
        String encoderPassword = PasswordEncryption.getEncoder(password);
        boolean isChange;
        try {
            isChange = userDao.updateUserPassword(encoderPassword, user.getId());
            MailSender.sendPassword(user.getEmail(), password);
        } catch (DaoException | EmailException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isChange;
    }
}
