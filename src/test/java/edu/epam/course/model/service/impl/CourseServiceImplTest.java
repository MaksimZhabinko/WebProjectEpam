package edu.epam.course.model.service.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.dao.CourseDao;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.CourseDetails;
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
 * The type Course service impl test.
 */
public class CourseServiceImplTest {
    private Course expected;
    private List<Course> expectedList = new ArrayList<>();

    @Mock
    private CourseDao courseDao;
    private AutoCloseable autoCloseable;

    @InjectMocks
    private CourseServiceImpl courseService;

    /**
     * Init service.
     */
    @BeforeClass
    public void initService() {
        expected = Course.builder()
                .setId(1L)
                .setName("java")
                .setEnrollmentActive(false)
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
     * Test find all.
     *
     * @throws DaoException     the dao exception
     * @throws ServiceException the service exception
     */
    @Test
    public void testFindAll() throws DaoException, ServiceException {
        Mockito.when(courseDao.findAll())
                .thenReturn(expectedList);
        List<Course> actualList = courseService.findAll();
        Assert.assertEquals(actualList, expectedList);
    }

    /**
     * Test add.
     *
     * @throws DaoException     the dao exception
     * @throws ServiceException the service exception
     */
    @Test
    public void testAdd() throws DaoException, ServiceException {
        Mockito.when(courseDao.add(Mockito.anyObject()))
                .thenReturn(true);
        boolean condition = courseService.add(expected);
        Assert.assertTrue(condition);
    }

    /**
     * Test delete.
     *
     * @throws DaoException     the dao exception
     * @throws ServiceException the service exception
     */
    @Test
    public void testDelete() throws DaoException, ServiceException  {
        Mockito.when(courseDao.deleteById(Mockito.anyLong()))
                .thenReturn(true);
        boolean condition = courseService.delete(1L);
        Assert.assertTrue(condition);
    }

    /**
     * Test find by id.
     *
     * @throws DaoException     the dao exception
     * @throws ServiceException the service exception
     */
    @Test
    public void testFindById() throws DaoException, ServiceException {
        Mockito.when(courseDao.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(expected));
        Optional<Course> actual = courseService.findById(1L);
        Assert.assertEquals(actual, Optional.of(expected));
    }

    /**
     * Test find user enrolled by course.
     *
     * @throws DaoException     the dao exception
     * @throws ServiceException the service exception
     */
    @Test
    public void testFindUserEnrolledByCourse() throws DaoException, ServiceException {
        Mockito.when(courseDao.findUserEnrolledByCourse(Mockito.anyLong()))
                .thenReturn(expectedList);
        List<Course> actualList = courseService.findUserEnrolledByCourse(1L);
        Assert.assertEquals(actualList, expectedList);
    }

    /**
     * Test update course name.
     *
     * @throws DaoException     the dao exception
     * @throws ServiceException the service exception
     */
    @Test
    public void testUpdateCourseName() throws DaoException, ServiceException {
        Mockito.when(courseDao.updateCourseName(Mockito.anyObject()))
                .thenReturn(true);
        boolean condition = courseService.updateCourseName(expected);
        Assert.assertTrue(condition);
    }

    /**
     * Test update start and end new course.
     *
     * @throws DaoException     the dao exception
     * @throws ServiceException the service exception
     */
    @Test
    public void testUpdateStartAndEndNewCourse() throws DaoException, ServiceException {
        Mockito.when(courseDao.updateStartAndEndNewCourse(Mockito.anyObject(), Mockito.anyList()))
                .thenReturn(1L);
        Long actual = courseService.updateStartAndEndNewCourse(new CourseDetails(), new ArrayList<>());
        Assert.assertEquals(actual, expected.getId());
    }
}
