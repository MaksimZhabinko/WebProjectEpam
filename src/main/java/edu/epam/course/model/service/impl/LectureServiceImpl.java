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

public class LectureServiceImpl implements LectureService {
    private static final Logger logger = LogManager.getLogger(LectureDaoImpl.class);
    private LectureDao lectureDao = new LectureDaoImpl();

    @Override
    public List<Lecture> findAllLectureById(Long id) throws ServiceException {
        List<Lecture> lectures;
        try {
            lectures = lectureDao.findAllById(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return lectures;
    }
}
