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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private UserDao userDao = new UserDaoImpl();

    @Override
    public Optional<User> findUserById(long id) throws ServiceException {
        try {
            Optional<User> user = userDao.findEntityById(id);
            return user;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
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

    @Override
    public boolean updateUserBalance(String money, User user) throws ServiceException {
        boolean isUpdate;
        try {
            BigDecimal moneyBigDecimal = new BigDecimal(money);
            BigDecimal userMoney = user.getMoney();
            BigDecimal result = moneyBigDecimal.add(userMoney);
            isUpdate = userDao.updateUserBalance(result , user.getId());
            user.setMoney(result);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findAll();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public boolean enrollCourse(User user, Long courseId, BigDecimal transaction) throws ServiceException {
        boolean isUpdate;
        try {
            userDao.updateUserBalance(transaction, user.getId());
            user.setMoney(transaction);
            isUpdate = userDao.enrollCourse(user, courseId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean userHaveCourse(Long userId, Long courseId) throws ServiceException {
        boolean isHave;
        try {
            isHave = userDao.userHaveCourse(userId, courseId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isHave;
    }

    @Override
    public boolean updateUserPhoto(User user) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = userDao.updateUserPhoto(user);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean blockUser(List<Long> usersId) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = userDao.blockUser(usersId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }
}
