package edu.epam.course.model.service.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.dao.CourseDetailsDao;
import edu.epam.course.model.dao.impl.CourseDetailsDaoImpl;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.CourseDetails;
import edu.epam.course.model.entity.Teacher;
import edu.epam.course.model.service.CourseDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

/**
 * The type Course details service.
 */
public class CourseDetailsServiceImpl implements CourseDetailsService {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(CourseDetailsDaoImpl.class);
    private CourseDetailsDao courseDetailsDao = new CourseDetailsDaoImpl();

    @Override
    public Optional<CourseDetails> findCourseDetailsById(Long courseDetailsId) throws ServiceException {
        Optional<CourseDetails> courseDetailsOptional;
        try {
            courseDetailsOptional = courseDetailsDao.findById(courseDetailsId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return courseDetailsOptional;
    }

    @Override
    public Optional<CourseDetails> findCourseDetailsByCourseId(Long courseId) throws ServiceException {
        Optional<CourseDetails> courseDetailsOptional;
        try {
            courseDetailsOptional = courseDetailsDao.findCourseDetailsByCourseId(courseId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return courseDetailsOptional;
    }

    @Override
    public boolean isCourseHaveDetails(Long courseId) throws ServiceException {
        boolean isHave;
        try {
            isHave = courseDetailsDao.isCourseHaveDetails(courseId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isHave;
    }

    @Override
    public boolean add(String hours, String description, String startCourse, String endCourse,
                       String startOfClass, String cost, Course course,
                       Teacher teacher) throws ServiceException {
        boolean isAdd;
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setHours(Integer.valueOf(hours));
        courseDetails.setDescription(description);
        courseDetails.setStartCourse(LocalDate.parse(startCourse));
        courseDetails.setEndCourse(LocalDate.parse(endCourse));
        courseDetails.setStartOfClass(LocalTime.parse(startOfClass));
        courseDetails.setCost(new BigDecimal(cost));
        courseDetails.setCourse(course);
        courseDetails.setTeacher(teacher);
        try {
            isAdd = courseDetailsDao.add(courseDetails);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isAdd;
    }

    @Override
    public boolean updateDescription(CourseDetails courseDetails) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = courseDetailsDao.updateDescription(courseDetails);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateHours(CourseDetails courseDetails) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = courseDetailsDao.updateHours(courseDetails);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateStartOfClass(CourseDetails courseDetails) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = courseDetailsDao.updateStartOfClass(courseDetails);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateCost(CourseDetails courseDetails) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = courseDetailsDao.updateCost(courseDetails);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateTeacher(CourseDetails courseDetails) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = courseDetailsDao.updateTeacher(courseDetails);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateStartEnd(CourseDetails courseDetails) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = courseDetailsDao.updateStartEnd(courseDetails);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }
}
