package edu.epam.course.model.service.impl;

import edu.epam.course.exception.EmailException;
import edu.epam.course.model.dao.UserDao;
import edu.epam.course.model.dao.impl.UserDaoImpl;
import edu.epam.course.model.entity.User;
import edu.epam.course.exception.DaoException;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.service.UserService;
import edu.epam.course.util.MailSenderUtil;
import edu.epam.course.util.PaginationUtil;
import edu.epam.course.util.PasswordEncryptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The type User service.
 */
public class UserServiceImpl implements UserService {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private UserDao userDao = new UserDaoImpl();

    @Override
    public Long findMaxUserId() throws ServiceException {
        Long id;
        try {
            id = userDao.findMaxUserId();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return id;
    }

    @Override
    public Optional<User> findById(long userId) throws ServiceException {
        try {
            Optional<User> user = userDao.findById(userId);
            return user;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws ServiceException {
        try {
            Optional<User> user = userDao.findByEmail(email);
            return user;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) throws ServiceException {
        try {
            String encoderPassword = PasswordEncryptionUtil.getEncoder(password);
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
            String encoderPassword = PasswordEncryptionUtil.getEncoder(password);
            isAdd = userDao.addUser(user, encoderPassword);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isAdd;
    }

    @Override
    public boolean forgotPassword(User user) throws ServiceException {
        String password = UUID.randomUUID().toString();
        String encoderPassword = PasswordEncryptionUtil.getEncoder(password);
        boolean isChange;
        try {
            isChange = userDao.updatePassword(encoderPassword, user.getId());
            MailSenderUtil.sendNewPassword(user.getEmail(), password);
        } catch (DaoException | EmailException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isChange;
    }

    @Override
    public boolean updateBalance(String money, User user) throws ServiceException {
        boolean isUpdate;
        try {
            BigDecimal moneyBigDecimal = new BigDecimal(money);
            BigDecimal userMoney = user.getMoney();
            BigDecimal result = moneyBigDecimal.add(userMoney);
            isUpdate = userDao.updateBalance(result , user.getId());
            user.setMoney(result);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public List<User> findAllLimit(int page) throws ServiceException {
        int start;
        final int position = 1;
        if (page == position) {
            start = 0;
        } else {
            start = (page - position) * PaginationUtil.USER_LIMIT;
        }
        List<User> users;
        try {
            users = userDao.findAllLimit(start, PaginationUtil.USER_LIMIT);
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
            userDao.updateBalance(transaction, user.getId());
            user.setMoney(transaction);
            isUpdate = userDao.enrollCourse(user, courseId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean isHaveCourse(Long userId, Long courseId) throws ServiceException {
        boolean isHave;
        try {
            isHave = userDao.isHaveCourse(userId, courseId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isHave;
    }

    @Override
    public boolean updatePhoto(User user) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = userDao.updatePhoto(user);
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

    @Override
    public boolean unblockUser(List<Long> usersId) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = userDao.unblockUser(usersId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateUserToAdmin(User user) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = userDao.updateUserToAdmin(user);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateNameAndSurname(String name, String surname, Long userId) throws ServiceException {
        boolean isUpdate;
        try {
            User user = User.builder()
                    .setId(userId)
                    .setName(name)
                    .setSurname(surname)
                    .build();
            isUpdate = userDao.updateNameAndSurname(user);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updatePassword(String password, User user) throws ServiceException {
        boolean isUpdate;
        try {
            String encoderPassword = PasswordEncryptionUtil.getEncoder(password);
            isUpdate = userDao.updatePassword(encoderPassword, user.getId());
            MailSenderUtil.sendPassword(user.getEmail(), password);
        } catch (DaoException | EmailException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public List<User> findAllEnrolledCourse() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findAllEnrolledCourse();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return users;
    }
}
