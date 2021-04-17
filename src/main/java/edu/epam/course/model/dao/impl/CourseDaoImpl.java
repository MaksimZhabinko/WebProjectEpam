package edu.epam.course.model.dao.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.connection.ConnectionPool;
import edu.epam.course.model.dao.CourseDao;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.CourseDetails;
import edu.epam.course.model.entity.Lecture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static edu.epam.course.model.dao.impl.CourseDetailsDaoImpl.COURSE_DETAILS_ADD;
import static edu.epam.course.model.dao.impl.LectureDaoImpl.ADD_LECTURE;

/**
 * The type Course dao.
 */
public class CourseDaoImpl implements CourseDao {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(CourseDaoImpl.class);
    /**
     * The constant find all course.
     */
    private static final String FIND_ALL_COURSE = "SELECT course_id, course_name, enrollment_active FROM course.courses";
    /**
     * The constant add course.
     */
    private static final String ADD_COURSE = "INSERT INTO courses (course_name, enrollment_active) VALUES (?, ?)";
    /**
     * The constant delete course.
     */
    private static final String DELETE_COURSE = "DELETE FROM course.courses WHERE course_id = ?";
    /**
     * The constant find course by id.
     */
    private static final String FIND_COURSE_BY_ID = "SELECT course_id, course_name, enrollment_active FROM course.courses WHERE course_id = ?";
    /**
     * The constant find user enrolled by course.
     */
    private static final String FIND_USER_ENROLLED_BY_COURSE = "SELECT course_id, course_name, enrollment_active FROM course.users INNER JOIN course.users_x_courses ON fk_user_id = user_id INNER JOIN course.courses ON fk_course_id = course_id WHERE fk_user_id = ?";
    /**
     * The constant update course name.
     */
    private static final String UPDATE_COURSE_NAME = "UPDATE course.courses SET course_name = ? WHERE course_id = ?";
    /**
     * The constant update course enrollment active.
     */
    private static final String UPDATE_COURSE_ENROLLMENT_ACTIVE = "UPDATE course.courses SET enrollment_active = false WHERE course_id = ?";

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
                course.setEnrollmentActive(resultSet.getBoolean(3));
                courses.add(course);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return courses;
    }

    @Override
    public Optional<Course> findById(Long id) throws DaoException {
        Optional<Course> courseOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_COURSE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getLong(1));
                course.setName(resultSet.getString(2));
                course.setEnrollmentActive(resultSet.getBoolean(3));
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
            preparedStatement.setBoolean(2, course.getEnrollmentActive());

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
                course.setEnrollmentActive(resultSet.getBoolean(3));
                courses.add(course);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return courses;
    }

    @Override
    public boolean updateCourseName(Course course) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COURSE_NAME)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setLong(2, course.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public Long updateStartAndEndNewCourse(CourseDetails courseDetails, List<Lecture> lectures) throws DaoException{
        Connection connection = null;
        PreparedStatement preparedStatementCourse = null;
        PreparedStatement preparedStatementCurseDetails = null;
        PreparedStatement preparedStatementLecture = null;
        Long newCourseId;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            preparedStatementCourse = connection.prepareStatement(ADD_COURSE, Statement.RETURN_GENERATED_KEYS);
            preparedStatementCourse.setString(1, courseDetails.getCourse().getName());
            preparedStatementCourse.setBoolean(2, courseDetails.getCourse().getEnrollmentActive());
            preparedStatementCourse.executeUpdate();
            ResultSet resultSet = preparedStatementCourse.getGeneratedKeys();
            resultSet.next();
            newCourseId = resultSet.getLong(1);

            preparedStatementCurseDetails = connection.prepareStatement(COURSE_DETAILS_ADD);
            preparedStatementCurseDetails.setInt(1, courseDetails.getHours());
            preparedStatementCurseDetails.setString(2, courseDetails.getDescription());
            preparedStatementCurseDetails.setDate(3, Date.valueOf(courseDetails.getStartCourse()));
            preparedStatementCurseDetails.setDate(4, Date.valueOf(courseDetails.getEndCourse()));
            preparedStatementCurseDetails.setTime(5, Time.valueOf(courseDetails.getStartOfClass()));
            preparedStatementCurseDetails.setBigDecimal(6, courseDetails.getCost());
            preparedStatementCurseDetails.setLong(7, newCourseId);
            preparedStatementCurseDetails.setLong(8, courseDetails.getTeacher().getId());
            preparedStatementCurseDetails.executeUpdate();

            preparedStatementLecture = connection.prepareStatement(ADD_LECTURE);
            for (Lecture lecture : lectures) {
                preparedStatementLecture.setString(1, lecture.getLecture());
                preparedStatementLecture.setLong(2, newCourseId);
                preparedStatementLecture.executeUpdate();
            }

            preparedStatementCourse = connection.prepareStatement(UPDATE_COURSE_ENROLLMENT_ACTIVE);
            preparedStatementCourse.setLong(1, courseDetails.getCourse().getId());
            preparedStatementCourse.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            logger.error(e);
            throw new DaoException();
        } finally {
            close(preparedStatementCourse);
            close(preparedStatementCurseDetails);
            close(preparedStatementLecture);
            close(connection);
        }
        return newCourseId;
    }
}
