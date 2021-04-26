package edu.epam.course.model.service.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.dao.UserDao;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.RoleType;
import edu.epam.course.model.entity.User;
import edu.epam.course.util.MailSenderUtil;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserServiceImplTest {
    private User expected;
    private List<User> expectedList = new ArrayList<>();

    @Mock
    private UserDao userDao;
    private AutoCloseable autoCloseable;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeClass
    public void initService() {
        Course course = Course.builder()
                .setId(1L)
                .setName("java")
                .setEnrollmentActive(false)
                .build();
        expected = User.builder()
                .setId(1L)
                .setEmail("email")
                .setName("name")
                .setSurname("surname")
                .setRole(RoleType.ADMIN)
                .setEnabled(true)
                .setMoney(BigDecimal.valueOf(1000))
                .setPhoto("photo")
                .setCourse(course)
                .build();
        expectedList.add(expected);
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterClass
    void closeService() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void testFindMaxUserId() throws DaoException, ServiceException {
        Mockito.when(userDao.findMaxUserId())
                .thenReturn(1L);
        Long actual = userService.findMaxUserId();
        Long expected = 1L;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindById() throws DaoException, ServiceException  {
        Mockito.when(userDao.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(expected));
        Optional<User> actual = userService.findById(1L);
        Assert.assertEquals(actual, Optional.of(expected));
    }

    @Test
    public void testFindByEmail() throws DaoException, ServiceException {
        Mockito.when(userDao.findByEmail(Mockito.anyString()))
                .thenReturn(Optional.ofNullable(expected));
        Optional<User> actual = userService.findByEmail("email");
        Assert.assertEquals(actual, Optional.of(expected));
    }

    @Test
    public void testFindByEmailAndPassword() throws DaoException, ServiceException {
        Mockito.when(userDao.findByEmailAndPassword(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Optional.ofNullable(expected));
        Optional<User> actual = userService.findByEmailAndPassword("email", "password");
        Assert.assertEquals(actual, Optional.of(expected));
    }

    @Test
    public void testAddUser() throws DaoException, ServiceException {
        Mockito.when(userDao.addUser(Mockito.anyObject(), Mockito.anyString()))
                .thenReturn(true);
        boolean condition = userService.addUser(expected, "password");
        Assert.assertTrue(condition);
    }

    @Test
    public void testForgotPassword() throws DaoException, ServiceException {
        // todo там еще емайл
//        Mockito.when(userDao.updatePassword(Mockito.anyString(), Mockito.anyLong()))
//                .thenReturn(true);
//        boolean condition = userService.updatePassword("password", expected);
//        Assert.assertTrue(condition);
    }

    @Test
    public void testUpdateBalance() throws DaoException, ServiceException {
        Mockito.when(userDao.updateBalance(Mockito.any(), Mockito.anyLong()))
                .thenReturn(true);
        boolean condition = userService.updateBalance("1499", expected);
        Assert.assertTrue(condition);
    }

    @Test
    public void testFindAllLimit() throws DaoException, ServiceException {
        Mockito.when(userDao.findAllLimit(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(expectedList);
        List<User> actualList = userService.findAllLimit(5);
        Assert.assertEquals(actualList, expectedList);
    }

    @Test
    public void testEnrollCourse() throws DaoException, ServiceException {
        Mockito.when(userDao.enrollCourse(Mockito.anyObject(), Mockito.anyLong()))
                .thenReturn(true);
        boolean condition = userService.enrollCourse(expected, 1L, BigDecimal.valueOf(1000));
        Assert.assertTrue(condition);
    }

    @Test
    public void testIsHaveCourse() throws DaoException, ServiceException {
        Mockito.when(userDao.isHaveCourse(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(true);
        boolean condition = userService.isHaveCourse(1L, 1L);
        Assert.assertTrue(condition);
    }

    @Test
    public void testUpdatePhoto() throws DaoException, ServiceException {
        Mockito.when(userDao.updatePhoto(Mockito.anyObject()))
                .thenReturn(true);
        boolean condition = userService.updatePhoto(expected);
        Assert.assertTrue(condition);
    }

    @Test
    public void testBlockUser() throws DaoException, ServiceException {
        Mockito.when(userDao.blockUser(Mockito.anyList()))
                .thenReturn(true);
        boolean condition = userService.blockUser(List.of(1L, 2L));
        Assert.assertTrue(condition);
    }

    @Test
    public void testUnblockUser() throws DaoException, ServiceException {
        Mockito.when(userDao.unblockUser(Mockito.anyList()))
                .thenReturn(true);
        boolean condition = userService.unblockUser(List.of(1L, 2L));
        Assert.assertTrue(condition);
    }

    @Test
    public void testUpdateUserToAdmin() throws DaoException, ServiceException {
        Mockito.when(userDao.updateUserToAdmin(Mockito.anyObject()))
                .thenReturn(true);
        boolean condition = userService.updateUserToAdmin(expected);
        Assert.assertTrue(condition);
    }

    @Test
    public void testUpdateNameAndSurname() throws DaoException, ServiceException {
        Mockito.when(userDao.updateNameAndSurname(Mockito.anyObject()))
                .thenReturn(true);
        boolean condition = userService.updateNameAndSurname("name", "surname", 1L);
        Assert.assertTrue(condition);
    }

    @Test
    public void testUpdatePassword() throws DaoException, ServiceException {
        // todo email sender
//        Mockito.when(userDao.updatePassword(Mockito.anyString(), Mockito.anyLong()))
//                .thenReturn(true);
//        try (MockedStatic<MailSenderUtil> mail = Mockito.mockStatic(MailSenderUtil.class)) {
//            mail.when(() -> MailSenderUtil.sendPassword(Mockito.anyString(), Mockito.anyString()));
//        }
//        boolean condition = userService.updatePassword("password", expected);
//        Assert.assertTrue(condition);
    }

    @Test
    public void testFindAllEnrolledCourse() throws DaoException, ServiceException {
        Mockito.when(userDao.findAllEnrolledCourse())
                .thenReturn(expectedList);
        List<User> actualList = userService.findAllEnrolledCourse();
        Assert.assertEquals(actualList, expectedList);
    }
}
