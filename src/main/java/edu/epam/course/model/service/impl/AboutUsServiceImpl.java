package edu.epam.course.model.service.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.dao.AboutUsDao;
import edu.epam.course.model.dao.impl.AboutUsDaoImpl;
import edu.epam.course.model.entity.AboutUs;
import edu.epam.course.model.service.AboutUsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AboutUsServiceImpl implements AboutUsService {
    private static final Logger logger = LogManager.getLogger(AboutUsServiceImpl.class);
    private AboutUsDao aboutUsDao = new AboutUsDaoImpl();

    @Override
    public List<AboutUs> findAll() throws ServiceException {
        List<AboutUs> aboutUsList;
        try {
            aboutUsList = aboutUsDao.findAll();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return aboutUsList;
    }

    @Override
    public boolean updateMessage(AboutUs aboutUs) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = aboutUsDao.updateMessage(aboutUs);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }
}
