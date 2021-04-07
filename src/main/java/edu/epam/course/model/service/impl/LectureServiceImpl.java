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
    public List<Lecture> findAllLectureByCourseId(Long id) throws ServiceException {
        List<Lecture> lectures;
        try {
            lectures = lectureDao.findAllByCourseId(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return lectures;
    }

    @Override
    public boolean addLecture(Lecture lecture) throws ServiceException {
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
    public boolean deleteLecture(Long id) throws ServiceException {
        boolean isDelete;
        try {
            isDelete = lectureDao.deleteById(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isDelete;
    }

    @Override
    public boolean updateLecture(Lecture lecture) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = lectureDao.updateLecture(lecture);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }
}
