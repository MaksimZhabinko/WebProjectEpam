package edu.epam.course.model.dao.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.connection.ConnectionPool;
import edu.epam.course.model.dao.CourseDao;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.CourseDetails;
import edu.epam.course.model.entity.Lecture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private static final String UPDATE_COURSE_NAME = "UPDATE course.courses SET course_name = ? WHERE course_id = ?";

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
    // todo должен ли он тут находится?
    public void example(Course course, CourseDetails courseDetails, List<Lecture> lectures) {
        Connection connection = null;
        // todo или использовать только один? или для каждого нужно создавать
        PreparedStatement preparedStatementCourse = null;
        PreparedStatement preparedStatementCurseDetails = null;
        PreparedStatement preparedStatementLecture = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            preparedStatementCourse = connection.prepareStatement(ADD_COURSE, Statement.RETURN_GENERATED_KEYS);
            preparedStatementCourse.setString(1, course.getName());
            preparedStatementCourse.setBoolean(2, course.getEnrollmentActive());
            preparedStatementCourse.executeUpdate();
            ResultSet resultSet = preparedStatementCourse.getGeneratedKeys();
            resultSet.next();
            long newCourseId = resultSet.getLong(1);
            // todo куда деть это sql запрос
            preparedStatementCurseDetails = connection.prepareStatement("INSERT INTO `course_details` (`number_of_hours`, `description`, `start_course`, `end_course`, `start_of_class`, `cost`, `fk_course_id`, `fk_teacher_name_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatementCurseDetails.setInt(1, courseDetails.getHours());
            preparedStatementCurseDetails.setString(2, courseDetails.getDescription());
            preparedStatementCurseDetails.setDate(3, Date.valueOf(courseDetails.getStartCourse()));
            preparedStatementCurseDetails.setDate(4, Date.valueOf(courseDetails.getEndCourse()));
            preparedStatementCurseDetails.setTime(5, Time.valueOf(courseDetails.getStartOfClass()));
            preparedStatementCurseDetails.setBigDecimal(6, courseDetails.getCost());
            preparedStatementCurseDetails.setLong(7, newCourseId);
            preparedStatementCurseDetails.setLong(8, courseDetails.getTeacher().getId());
            preparedStatementCurseDetails.executeUpdate();

            preparedStatementLecture = connection.prepareStatement("INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES (?, ?)");
            for (Lecture lecture : lectures) {
                preparedStatementLecture.setString(1, lecture.getLecture());
                preparedStatementLecture.setLong(2, newCourseId);
                preparedStatementLecture.executeUpdate();
            }

            // todo исп тот же prepare
            preparedStatementCourse = connection.prepareStatement("UPDATE course.courses SET enrollment_active = false WHERE course_id = ?");
            preparedStatementCourse.setLong(1, course.getId());
            preparedStatementCourse.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            logger.error(e);

        } finally {
            close(preparedStatementCourse);
            close(preparedStatementCurseDetails);
            close(connection);
        }
    }
}
