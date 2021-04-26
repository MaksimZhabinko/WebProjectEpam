package edu.epam.course.model.dao.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.connection.ConnectionPool;
import edu.epam.course.model.dao.TeacherDao;
import edu.epam.course.model.entity.Lecture;
import edu.epam.course.model.entity.Teacher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Teacher dao.
 */
public class TeacherDaoImpl implements TeacherDao {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(TeacherDaoImpl.class);
    /**
     * The constant add teacher.
     */
    private static final String ADD_TEACHER = "INSERT INTO `teachers` (`name`, `surname`) VALUES (?, ?)";
    /**
     * The constant find all teachers.
     */
    private static final String FIND_ALL_TEACHERS = "SELECT teacher_id, name, surname, photo FROM course.teachers";
    /**
     * The constant delete teacher.
     */
    private static final String DELETE_TEACHER = "DELETE FROM course.teachers WHERE teacher_id = ?";
    /**
     * The constant update teacher photo.
     */
    private static final String UPDATE_TEACHER_PHOTO = "UPDATE course.teachers SET photo = ? WHERE teacher_id = ?";
    /**
     * The constant find teacher by name and surname.
     */
    private static final String FIND_TEACHER_BY_NAME_AND_SURNAME = "SELECT teacher_id, name, surname, photo FROM course.teachers WHERE name = ? AND surname = ?";

    @Override
    public List<Teacher> findAll() throws DaoException {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_TEACHERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Teacher teacher = Teacher.builder()
                        .setId(resultSet.getLong(1))
                        .setName(resultSet.getString(2))
                        .setSurname(resultSet.getString(3))
                        .setPhoto(resultSet.getString(4))
                        .build();
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return teachers;
    }

    @Override
    public boolean add(Teacher teacher) throws DaoException {
        boolean isAdd;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_TEACHER)) {
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, teacher.getSurname());

            isAdd = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isAdd;
    }

    @Override
    public boolean deleteTeachers(List<Long> teachersId) throws DaoException {
        boolean isDelete;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_TEACHER);
            for (Long teacherId : teachersId) {
                preparedStatement.setLong(1, teacherId);
                preparedStatement.executeUpdate();
            }
            connection.commit();
            isDelete = true;
        } catch (SQLException e) {
            rollback(connection);
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return isDelete;
    }

    @Override
    public boolean updatePhoto(Teacher teacher) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TEACHER_PHOTO)) {
            preparedStatement.setString(1, teacher.getPhoto());
            preparedStatement.setLong(2, teacher.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public Optional<Teacher> findByNameAndSurname(String name, String surname) throws DaoException {
        Optional<Teacher> teacherOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_TEACHER_BY_NAME_AND_SURNAME)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Teacher teacher = Teacher.builder()
                        .setId(resultSet.getLong(1))
                        .setName(resultSet.getString(2))
                        .setSurname(resultSet.getString(3))
                        .setPhoto(resultSet.getString(4))
                        .build();
                teacherOptional = Optional.of(teacher);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return teacherOptional;
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }


    @Override
    public Optional<Teacher> findById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }
}
