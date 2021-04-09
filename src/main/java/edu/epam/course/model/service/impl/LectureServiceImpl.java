package edu.epam.course.model.service.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.dao.LectureDao;
import edu.epam.course.model.dao.impl.LectureDaoImpl;
import edu.epam.course.model.entity.Lecture;
import edu.epam.course.model.service.LectureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * The type Lecture service.
 */
public class LectureServiceImpl implements LectureService {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(LectureDaoImpl.class);
    private LectureDao lectureDao = new LectureDaoImpl();

    @Override
    public List<Lecture> findAllLectureByCourseId(Long courseId) throws ServiceException {
        List<Lecture> lectures;
        try {
            lectures = lectureDao.findAllLectureByCourseId(courseId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return lectures;
    }

    @Override
    public boolean add(Lecture lecture) throws ServiceException {
        boolean idAdd;
        try {
            idAdd = lectureDao.add(lecture);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return idAdd;
    }

    @Override
    public boolean delete(Long lectureId) throws ServiceException {
        boolean isDelete;
        try {
            isDelete = lectureDao.deleteById(lectureId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isDelete;
    }

    @Override
    public boolean update(Lecture lecture) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = lectureDao.update(lecture);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public Optional<Lecture> findLectureByIdAndCourseId(Long lectureId, Long courseId) throws ServiceException {
        Optional<Lecture> lectureOptional;
        try {
            lectureOptional = lectureDao.findLectureByIdAndCourseId(lectureId, courseId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return lectureOptional;
    }
}
