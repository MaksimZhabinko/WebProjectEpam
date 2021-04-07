package edu.epam.course.model.dao;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.entity.AboutUs;

/**
 * The interface about us dao.
 */
public interface AboutUsDao extends BaseDao<Long, AboutUs> {
    /**
     * Update message.
     *
     * @param aboutUs the about us
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateMessage(AboutUs aboutUs) throws DaoException;
}
