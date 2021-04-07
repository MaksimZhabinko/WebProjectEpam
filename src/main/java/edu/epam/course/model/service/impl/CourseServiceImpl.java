package edu.epam.course.model.service.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.dao.CourseDao;
import edu.epam.course.model.dao.impl.CourseDaoImpl;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.service.CourseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * The type Course service.
 */
public class CourseServiceImpl implements CourseService {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(CourseServiceImpl.class);
    private CourseDao courseDao = new CourseDaoImpl();

    @Override
    public List<Course> findAll() throws ServiceException {
        List<Course> courses;
        try {
            courses = courseDao.findAll();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return courses;
    }

    @Override
    public boolean addCourse(Course course) throws ServiceException {
        boolean idAdd;
        try {
            idAdd = courseDao.add(course);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return idAdd;
    }

    @Override
    public boolean deleteCourse(Long id) throws ServiceException {
        boolean isDelete;
        try {
            isDelete = courseDao.deleteById(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isDelete;
    }

    @Override
    public Optional<Course> findCourseById(Long id) throws ServiceException {
        Optional<Course> courseOptional;
        try {
            courseOptional = courseDao.findEntityById(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return courseOptional;
    }

    @Override
    public List<Course> findUserEnrolledByCourse(Long userId) throws ServiceException {
        List<Course> courses;
        try {
            courses = courseDao.findUserEnrolledByCourse(userId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return courses;
    }
}
