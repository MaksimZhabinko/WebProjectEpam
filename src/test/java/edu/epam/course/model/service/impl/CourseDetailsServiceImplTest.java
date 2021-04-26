package edu.epam.course.model.service.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.dao.CourseDetailsDao;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.CourseDetails;
import edu.epam.course.model.entity.Teacher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

/**
 * The type Course details service impl test.
 */
public class CourseDetailsServiceImplTest {
    private CourseDetails expected;

    @Mock
    private CourseDetailsDao courseDetailsDao;
    private AutoCloseable autoCloseable;

    @InjectMocks
    private CourseDetailsServiceImpl courseDetailsService;

    /**
     * Init service.
     */
    @BeforeClass
    public void initService() {
        Course course = Course.builder()
                .setId(1L)
                .setName("java")
                .setEnrollmentActive(false)
                .build();
        Teacher teacher = Teacher.builder()
                .setId(1L)
                .setName("Maksim")
                .setSurname("Zhabinko")
                .build();
        expected = CourseDetails.builder()
                .setId(1L)
                .setHours(77)
                .setDescription("description")
                .setStartCourse(LocalDate.parse("2020-01-01"))
                .setEndCourse(LocalDate.parse("2021-01-01"))
                .setStartOfClass(LocalTime.parse("19:00"))
                .setCost(BigDecimal.valueOf(1499))
                .setCourse(course)
                .setTeacher(teacher)
                .build();
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
     * Test find course details by id.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void testFindCourseDetailsById() throws ServiceException, DaoException {
        Mockito.when(courseDetailsDao.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(expected));
        Optional<CourseDetails> actual = courseDetailsService.findCourseDetailsById(1L);
        Assert.assertEquals(actual, Optional.of(expected));
    }

    /**
     * Test find course details by course id.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void testFindCourseDetailsByCourseId() throws ServiceException, DaoException {
        Mockito.when(courseDetailsDao.findCourseDetailsByCourseId(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(expected));
        Optional<CourseDetails> actual = courseDetailsService.findCourseDetailsByCourseId(1L);
        Assert.assertEquals(actual, Optional.of(expected));
    }

    /**
     * Test is course have details.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void testIsCourseHaveDetails() throws ServiceException, DaoException {
        Mockito.when(courseDetailsDao.isCourseHaveDetails(Mockito.anyLong()))
                .thenReturn(true);
        boolean condition = courseDetailsService.isCourseHaveDetails(1L);
        Assert.assertTrue(condition);
    }

    /**
     * Test add.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void testAdd() throws ServiceException, DaoException {
        Mockito.when(courseDetailsDao.add(Mockito.anyObject()))
                .thenReturn(true);
        boolean condition = courseDetailsService.add(String.valueOf(expected.getHours()), expected.getDescription(),
                String.valueOf(expected.getStartCourse()), String.valueOf(expected.getEndCourse()),
                String.valueOf(expected.getStartOfClass()), String.valueOf(expected.getCost()),
                expected.getCourse(), expected.getTeacher());
        Assert.assertTrue(condition);
    }

    /**
     * Test update description.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void testUpdateDescription() throws ServiceException, DaoException {
        Mockito.when(courseDetailsDao.updateDescription(Mockito.anyObject()))
                .thenReturn(true);
        boolean condition = courseDetailsService.updateDescription(expected);
        Assert.assertTrue(condition);
    }

    /**
     * Test update hours.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void testUpdateHours() throws ServiceException, DaoException {
        Mockito.when(courseDetailsDao.updateHours(Mockito.anyObject()))
                .thenReturn(true);
        boolean condition = courseDetailsService.updateHours(expected);
        Assert.assertTrue(condition);
    }

    /**
     * Test update start of class.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void testUpdateStartOfClass() throws ServiceException, DaoException {
        Mockito.when(courseDetailsDao.updateStartOfClass(Mockito.anyObject()))
                .thenReturn(true);
        boolean condition = courseDetailsService.updateStartOfClass(expected);
        Assert.assertTrue(condition);
    }

    /**
     * Test update cost.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void testUpdateCost() throws ServiceException, DaoException {
        Mockito.when(courseDetailsDao.updateCost(Mockito.anyObject()))
                .thenReturn(true);
        boolean condition = courseDetailsService.updateCost(expected);
        Assert.assertTrue(condition);
    }

    /**
     * Test update teacher.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void testUpdateTeacher() throws ServiceException, DaoException {
        Mockito.when(courseDetailsDao.updateTeacher(Mockito.anyObject()))
                .thenReturn(true);
        boolean condition = courseDetailsService.updateTeacher(expected);
        Assert.assertTrue(condition);
    }

    /**
     * Test update start end.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void testUpdateStartEnd() throws ServiceException, DaoException {
        Mockito.when(courseDetailsDao.updateStartEnd(Mockito.anyObject()))
                .thenReturn(true);
        boolean condition = courseDetailsService.updateStartEnd(expected);
        Assert.assertTrue(condition);
    }
}
