package edu.epam.course.model.dao.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.connection.ConnectionPool;
import edu.epam.course.model.dao.CourseDao;
import edu.epam.course.model.entity.Course;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseDaoImpl implements CourseDao {
    private static final Logger logger = LogManager.getLogger(CourseDaoImpl.class);
    private static final String FIND_ALL_COURSE = "SELECT course_id, course_name FROM course.courses";
    private static final String ADD_COURSE = "INSERT INTO courses (course_name) VALUES (?)";
    private static final String DELETE_COURSE = "DELETE FROM course.courses WHERE course_id = ?";
    private static final String FIND_COURSE_BY_ID = "SELECT course_id, course_name FROM course.courses WHERE course_id = ?";
    private static final String FIND_USER_ENROLLED_BY_COURSE = "SELECT course_id, course_name FROM course.users INNER JOIN course.users_x_courses ON fk_user_id = user_id INNER JOIN course.courses ON fk_course_id = course_id WHERE fk_user_id = ?";

    @Override
    public List<Course> findAll() throws DaoException {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_COURSE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getLong(1));
                course.setName(resultSet.getString(2));
                courses.add(course);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return courses;
    }

    @Override
    public Optional<Course> findEntityById(Long id) throws DaoException {
        Optional<Course> courseOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_COURSE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getLong(1));
                course.setName(resultSet.getString(2));
                courseOptional = Optional.ofNullable(course);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return courseOptional;
    }

    @Override
    public boolean add(Course course) throws DaoException {
        boolean isAdd;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_COURSE)) {
            preparedStatement.setString(1, course.getName());

            isAdd = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isAdd;
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        boolean isDelete;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COURSE)) {
            preparedStatement.setLong(1, id);

            isDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isDelete;
    }

    @Override
    public List<Course> findUserEnrolledByCourse(Long userId) throws DaoException {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_ENROLLED_BY_COURSE)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getLong(1));
                course.setName(resultSet.getString(2));
                courses.add(course);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return courses;
    }
}
