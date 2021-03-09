package edu.epam.course.model.service.impl;

import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.AboutUs;
import edu.epam.course.model.service.AboutUsService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class AboutUsServiceImplTest {
    AboutUsService aboutUsService = new AboutUsServiceImpl();

    @Test
    public void testFindAll() {
        List<AboutUs> aboutUsList = null;
        try {
            aboutUsList = aboutUsService.findAll();
        } catch (ServiceException e) {
            e.printStackTrace(); // todo так можно здесь?
        }
        Assert.assertNotNull(aboutUsList);
    }
}
