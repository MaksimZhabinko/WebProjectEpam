package edu.epam.course.model.service.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.dao.CourseDetailsDao;
import edu.epam.course.model.dao.impl.CourseDetailsDaoImpl;
import edu.epam.course.model.entity.CourseDetails;
import edu.epam.course.model.service.CourseDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class CourseDetailsServiceImpl implements CourseDetailsService {
    private static final Logger logger = LogManager.getLogger(CourseDetailsDaoImpl.class);
    private CourseDetailsDao courseDetailsDao = new CourseDetailsDaoImpl();

    @Override
    public Optional<CourseDetails> findCourseDetailsById(Long id) throws ServiceException {
        Optional<CourseDetails> courseDetailsOptional;
        try {
            courseDetailsOptional = courseDetailsDao.findEntityById(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return courseDetailsOptional;
    }
}
