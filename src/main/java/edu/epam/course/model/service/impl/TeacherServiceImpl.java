package edu.epam.course.model.service.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.exception.ServiceException;
import edu.epam.course.model.dao.TeacherDao;
import edu.epam.course.model.dao.impl.TeacherDaoImpl;
import edu.epam.course.model.entity.Teacher;
import edu.epam.course.model.service.TeacherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * The type Teacher service.
 */
public class TeacherServiceImpl implements TeacherService {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(TeacherServiceImpl.class);
    private TeacherDao teacherDao = new TeacherDaoImpl();

    @Override
    public boolean addTeacher(String name, String surname) throws ServiceException {
        boolean isAdd;
        try {
            Teacher teacher = new Teacher();
            teacher.setName(name);
            teacher.setSurname(surname);
            isAdd = teacherDao.add(teacher);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isAdd;
    }

    @Override
    public List<Teacher> findAllTeachers() throws ServiceException {
        List<Teacher> teachers;
        try {
            teachers = teacherDao.findAll();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return teachers;
    }

    @Override
    public boolean deleteTeacher(List<Long> teachersId) throws ServiceException {
        boolean isDelete;
        try {
            isDelete = teacherDao.deleteTeachers(teachersId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isDelete;
    }

    @Override
    public boolean updateTeacherPhoto(Teacher teacher) throws ServiceException {
        boolean isUpdate;
        try {
            isUpdate = teacherDao.updateTeacherPhoto(teacher);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isUpdate;
    }

    @Override
    public Optional<Teacher> findTeacherByNameAndSurname(String name, String surname) throws ServiceException {
        Optional<Teacher> teacherOptional;
        try {
            teacherOptional = teacherDao.findTeacherByNameAndSurname(name.trim(), surname.trim());
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return teacherOptional;
    }
}
