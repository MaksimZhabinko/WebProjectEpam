package edu.epam.course.model.service.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.dao.TeacherDao;
import edu.epam.course.model.entity.Teacher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Teacher service impl test.
 */
public class TeacherServiceImplTest {
    private Teacher expected;
    private List<Teacher> expectedList = new ArrayList<>();

    @Mock
    private TeacherDao teacherDao;
    private AutoCloseable autoCloseable;

    @InjectMocks
    private TeacherServiceImpl teacherService;

    /**
     * Init service.
     */
    @BeforeClass
    public void initService() {
        expected = Teacher.builder()
                .setId(1L)
                .setName("name")
                .setSurname("surname")
                .setPhoto("photo")
                .build();
        expectedList.add(expected);
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    /**
     * Close service.
     *
     * @throws Exception the exception
     */
    @AfterClass
    void closeService() throws Exception {
        autoCloseable.close();
    }

    /**
     * Test add.
     *
     * @throws DaoException     the dao exception
     * @throws ServiceException the service exception
     */
    @Test
    public void testAdd() throws DaoException, ServiceException {
        Mockito.when(teacherDao.add(Mockito.anyObject()))
                .thenReturn(true);
        boolean condition = teacherService.add("name", "surname");
        Assert.assertTrue(condition);
    }

    /**
     * Test find all.
     *
     * @throws DaoException     the dao exception
     * @throws ServiceException the service exception
     */
    @Test
    public void testFindAll() throws DaoException, ServiceException {
        Mockito.when(teacherDao.findAll())
                .thenReturn(expectedList);
        List<Teacher> actualList = teacherService.findAll();
        Assert.assertEquals(actualList, expectedList);
    }

    /**
     * Test delete teachers.
     *
     * @throws DaoException     the dao exception
     * @throws ServiceException the service exception
     */
    @Test
    public void testDeleteTeachers() throws DaoException, ServiceException {
        Mockito.when(teacherDao.deleteTeachers(Mockito.anyList()))
                .thenReturn(true);
        boolean condition = teacherService.deleteTeachers(List.of(1L, 2L));
        Assert.assertTrue(condition);
    }

    /**
     * Test update photo.
     *
     * @throws DaoException     the dao exception
     * @throws ServiceException the service exception
     */
    @Test
    public void testUpdatePhoto() throws DaoException, ServiceException  {
        Mockito.when(teacherDao.updatePhoto(Mockito.anyObject()))
                .thenReturn(true);
        boolean condition = teacherService.updatePhoto(expected);
        Assert.assertTrue(condition);
    }

    /**
     * Test find by name and surname.
     *
     * @throws DaoException     the dao exception
     * @throws ServiceException the service exception
     */
    @Test
    public void testFindByNameAndSurname() throws DaoException, ServiceException  {
        Mockito.when(teacherDao.findByNameAndSurname(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Optional.ofNullable(expected));
        Optional<Teacher> actual = teacherService.findByNameAndSurname(expected.getName(), expected.getSurname());
        Assert.assertEquals(actual, Optional.of(expected));
    }
}
