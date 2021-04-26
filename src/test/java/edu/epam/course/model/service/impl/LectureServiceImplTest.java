package edu.epam.course.model.service.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.dao.LectureDao;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.Lecture;
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
 * The type Lecture service impl test.
 */
public class LectureServiceImplTest {
    private Lecture expected;
    private List<Lecture> expectedList = new ArrayList<>();

    @Mock
    private LectureDao lectureDao;
    private AutoCloseable autoCloseable;

    @InjectMocks
    private LectureServiceImpl lectureService;

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
        expected = Lecture.builder()
                .setId(1L)
                .setLecture("java good")
                .setCourse(course)
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
     * Test find all lecture by course id.
     *
     * @throws DaoException     the dao exception
     * @throws ServiceException the service exception
     */
    @Test
    public void testFindAllLectureByCourseId() throws DaoException, ServiceException {
        Mockito.when(lectureDao.findAllLectureByCourseId(Mockito.anyLong()))
                .thenReturn(expectedList);
        List<Lecture> actualList = lectureService.findAllLectureByCourseId(1L);
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
        Mockito.when(lectureDao.add(Mockito.anyObject()))
                .thenReturn(true);
        boolean condition = lectureService.add(expected);
        Assert.assertTrue(condition);
    }

    /**
     * Test delete.
     *
     * @throws DaoException     the dao exception
     * @throws ServiceException the service exception
     */
    @Test
    public void testDelete() throws DaoException, ServiceException {
        Mockito.when(lectureDao.deleteById(Mockito.anyLong()))
                .thenReturn(true);
        boolean condition = lectureService.delete(1L);
        Assert.assertTrue(condition);
    }

    /**
     * Test update.
     *
     * @throws DaoException     the dao exception
     * @throws ServiceException the service exception
     */
    @Test
    public void testUpdate() throws DaoException, ServiceException {
        Mockito.when(lectureDao.update(Mockito.anyObject()))
                .thenReturn(true);
        boolean condition = lectureService.update(expected);
        Assert.assertTrue(condition);
    }

    /**
     * Test find lecture by id and course id.
     *
     * @throws DaoException     the dao exception
     * @throws ServiceException the service exception
     */
    @Test
    public void testFindLectureByIdAndCourseId() throws DaoException, ServiceException {
        Mockito.when(lectureDao.findLectureByIdAndCourseId(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(expected));
        Optional<Lecture> actual = lectureService.findLectureByIdAndCourseId(1L, 1L);
        Assert.assertEquals(actual, Optional.of(expected));
    }
}
