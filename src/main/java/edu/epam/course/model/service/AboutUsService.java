package edu.epam.course.model.service;

import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.entity.AboutUs;

import java.util.List;

/**
 * The interface about us service.
 */
public interface AboutUsService {
    /**
     * Find all about us.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<AboutUs> findAll() throws ServiceException;

    /**
     * Update message.
     *
     * @param aboutUs the about us
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateMessage(AboutUs aboutUs) throws ServiceException;
}
