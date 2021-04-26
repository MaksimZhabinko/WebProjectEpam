package edu.epam.course.model.service.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.dao.ReviewDao;
import edu.epam.course.model.entity.Review;
import edu.epam.course.model.entity.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Review service impl test.
 */
public class ReviewServiceImplTest {
    private Review expected;
    private List<Review> expectedList = new ArrayList<>();

    @Mock
    private ReviewDao reviewDao;
    private AutoCloseable autoCloseable;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    /**
     * Init service.
     */
    @BeforeClass
    public void initService() {
        User user = User.builder()
                .setId(1L)
                .setName("maksim")
                .setSurname("zhabinko")
                .build();
        expected = Review.builder()
                .setId(1L)
                .setMessage("message review")
                .setDateMessage(LocalDate.now())
                .setUser(user)
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
        Mockito.when(reviewDao.findAll())
                .thenReturn(expectedList);
        List<Review> actualList = reviewService.findAll();
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
        Mockito.when(reviewDao.add(Mockito.anyObject()))
                .thenReturn(true);
        boolean condition = reviewService.add(expected);
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
        Mockito.when(reviewDao.deleteById(Mockito.anyLong()))
                .thenReturn(true);
        boolean condition = reviewService.delete(1L);
        Assert.assertTrue(condition);
    }

    /**
     * Test is have review by user id.
     *
     * @throws DaoException     the dao exception
     * @throws ServiceException the service exception
     */
    @Test
    public void testIsHaveReviewByUserId() throws DaoException, ServiceException {
        Mockito.when(reviewDao.isHaveReviewByUserId(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(true);
        boolean condition = reviewService.isHaveReviewByUserId(1L, 1L);
        Assert.assertTrue(condition);
    }
}
