package edu.epam.course.model.dao;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.entity.AboutUs;

public interface AboutUsDao extends BaseDao<Long, AboutUs> {
    boolean updateMessage(AboutUs aboutUs) throws DaoException;
}
