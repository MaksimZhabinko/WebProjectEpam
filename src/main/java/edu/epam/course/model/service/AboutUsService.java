package edu.epam.course.model.service;

import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.AboutUs;

import java.util.List;

public interface AboutUsService {
    List<AboutUs> findAll() throws ServiceException;
    boolean updateMessage(AboutUs aboutUs) throws ServiceException;
}
