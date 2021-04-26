package edu.epam.course.model.service.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.dao.impl.AboutUsDaoImpl;
import edu.epam.course.model.entity.AboutUs;
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

/**
 * The type About us service impl test.
 */
public class AboutUsServiceImplTest {

    @Mock
    private AboutUsDaoImpl aboutUsDao;
    private AutoCloseable autoCloseable;

    @InjectMocks
    private AboutUsServiceImpl aboutUsService;

    /**
     * Init service.
     */
    @BeforeClass
    public void initService() {
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
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void testFindAll() throws ServiceException, DaoException {
        List<AboutUs> expected = new ArrayList<>();
        AboutUs aboutUs = AboutUs.builder()
                .setId(1L)
                .setMessage("Мы лучшая компания по изучению ИТ специалистов")
                .build();
        expected.add(aboutUs);
        Mockito.when(aboutUsDao.findAll()).thenReturn(expected);
        List<AboutUs> actual = aboutUsService.findAll();
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test update message.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void testUpdateMessage() throws ServiceException, DaoException {
        AboutUs aboutUs = AboutUs.builder()
                .setId(1L)
                .setMessage("some text")
                .build();
        Mockito.when(aboutUsDao.updateMessage(aboutUs)).thenReturn(true);
        boolean condition = aboutUsService.updateMessage(aboutUs);
        Assert.assertTrue(condition);
    }
}
