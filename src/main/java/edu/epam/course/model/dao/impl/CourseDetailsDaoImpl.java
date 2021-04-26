package edu.epam.course.model.dao.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.connection.ConnectionPool;
import edu.epam.course.model.dao.CourseDetailsDao;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.CourseDetails;
import edu.epam.course.model.entity.Teacher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

/**
 * The type Course details dao.
 */
public class CourseDetailsDaoImpl implements CourseDetailsDao {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(CourseDetailsDaoImpl.class);
    /**
     * The constant find course details by course id.
     */
    private static final String FIND_COURSE_DETAILS_BY_COURSE_ID = "SELECT course_detail_id, number_of_hours, description, start_course, end_course, start_of_class, cost, course_id, course_name, enrollment_active, teacher_id, name, surname, photo FROM course.course_details INNER JOIN course.courses ON fk_course_id = course_id INNER JOIN course.teachers ON fk_teacher_name_id = teacher_id WHERE course_id = ?";
    /**
     * The constant find coure details by id.
     */
    private static final String FIND_COURSE_DETAILS_BY_ID = "SELECT course_detail_id, number_of_hours, description, start_course, end_course, start_of_class, cost, course_id, course_name, enrollment_active, teacher_id, name, surname, photo FROM course.course_details INNER JOIN course.courses ON fk_course_id = course_id INNER JOIN course.teachers ON fk_teacher_name_id = teacher_id WHERE course_detail_id = ?";
    /**
     * The constant course have deatils.
     */
    private static final String COURSE_HAVE_DETAILS = "SELECT course_detail_id FROM course.course_details where fk_course_id = ?";
    /**
     * The constant course details add.
     */
    static final String COURSE_DETAILS_ADD = "INSERT INTO course.course_details (`number_of_hours`, `description`, `start_course`, `end_course`, `start_of_class`, `cost`, `fk_course_id`, `fk_teacher_name_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    /**
     * The constant course details description update.
     */
    private static final String COURSE_DETAILS_DESCRIPTION_UPDATE = "UPDATE course.course_details SET description = ? WHERE course_detail_id = ?";
    /**
     * The constant course details hours update.
     */
    private static final String COURSE_DETAILS_HOURS_UPDATE = "UPDATE course.course_details SET number_of_hours = ? WHERE course_detail_id = ?";
    /**
     * The constant course details cost update.
     */
    private static final String COURSE_DETAILS_COST_UPDATE = "UPDATE course.course_details SET cost = ? WHERE course_detail_id = ?";
    /**
     * The constant course details start of class update.
     */
    private static final String COURSE_DETAILS_START_OF_CLASS_UPDATE = "UPDATE course.course_details SET start_of_class = ? WHERE course_detail_id = ?";
    /**
     * The constant course details teacher update.
     */
    private static final String COURSE_DETAILS_TEACHER_UPDATE = "UPDATE course.course_details SET fk_teacher_name_id = ? WHERE course_detail_id = ?";
    /**
     * The constant course details start and end update.
     */
    private static final String COURSE_DETAILS_START_END_UPDATE = "UPDATE course.course_details SET start_course = ?, end_course = ? WHERE course_detail_id = ?";



    @Override
    public Optional<CourseDetails> findById(Long id) throws DaoException {
        Optional<CourseDetails> courseDetailsOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_COURSE_DETAILS_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Teacher teacher = Teacher.builder()
                        .setId(resultSet.getLong(11))
                        .setName(resultSet.getString(12))
                        .setSurname(resultSet.getString(13))
                        .setPhoto(resultSet.getString(14))
                        .build();
                Course course = Course.builder()
                        .setId(resultSet.getLong(8))
                        .setName(resultSet.getString(9))
                        .setEnrollmentActive(resultSet.getBoolean(10))
                        .build();
                CourseDetails courseDetails = CourseDetails.builder()
                        .setId(resultSet.getLong(1))
                        .setHours(resultSet.getInt(2))
                        .setDescription(resultSet.getString(3))
                        .setStartCourse(resultSet.getDate(4).toLocalDate())
                        .setEndCourse(resultSet.getDate(5).toLocalDate())
                        .setStartOfClass(resultSet.getTime(6).toLocalTime())
                        .setCost(resultSet.getBigDecimal(7))
                        .setCourse(course)
                        .setTeacher(teacher)
                        .build();
                courseDetailsOptional = Optional.of(courseDetails);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return courseDetailsOptional;
    }

    @Override
    public Optional<CourseDetails> findCourseDetailsByCourseId(Long courseId) throws DaoException {
        Optional<CourseDetails> courseDetailsOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_COURSE_DETAILS_BY_COURSE_ID)) {
            preparedStatement.setLong(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Teacher teacher = Teacher.builder()
                        .setId(resultSet.getLong(11))
                        .setName(resultSet.getString(12))
                        .setSurname(resultSet.getString(13))
                        .setPhoto(resultSet.getString(14))
                        .build();
                Course course = Course.builder()
                        .setId(resultSet.getLong(8))
                        .setName(resultSet.getString(9))
                        .setEnrollmentActive(resultSet.getBoolean(10))
                        .build();
                CourseDetails courseDetails = CourseDetails.builder()
                        .setId(resultSet.getLong(1))
                        .setHours(resultSet.getInt(2))
                        .setDescription(resultSet.getString(3))
                        .setStartCourse(resultSet.getDate(4).toLocalDate())
                        .setEndCourse(resultSet.getDate(5).toLocalDate())
                        .setStartOfClass(resultSet.getTime(6).toLocalTime())
                        .setCost(resultSet.getBigDecimal(7))
                        .setCourse(course)
                        .setTeacher(teacher)
                        .build();
                courseDetailsOptional = Optional.of(courseDetails);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return courseDetailsOptional;
    }

    @Override
    public boolean isCourseHaveDetails(Long courseId) throws DaoException {
        boolean isHave = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(COURSE_HAVE_DETAILS)) {
            preparedStatement.setLong(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                isHave = true;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isHave;
    }

    @Override
    public boolean add(CourseDetails courseDetails) throws DaoException {
        boolean isAdd;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(COURSE_DETAILS_ADD)) {
            preparedStatement.setInt(1, courseDetails.getHours());
            preparedStatement.setString(2, courseDetails.getDescription());
            preparedStatement.setDate(3, Date.valueOf(courseDetails.getStartCourse()));
            preparedStatement.setDate(4, Date.valueOf(courseDetails.getEndCourse()));
            preparedStatement.setTime(5, Time.valueOf(courseDetails.getStartOfClass()));
            preparedStatement.setBigDecimal(6, courseDetails.getCost());
            preparedStatement.setLong(7, courseDetails.getCourse().getId());
            preparedStatement.setLong(8, courseDetails.getTeacher().getId());

            isAdd = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isAdd;
    }

    @Override
    public boolean updateDescription(CourseDetails courseDetails) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COURSE_DETAILS_DESCRIPTION_UPDATE)) {
            preparedStatement.setString(1, courseDetails.getDescription());
            preparedStatement.setLong(2, courseDetails.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateHours(CourseDetails courseDetails) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COURSE_DETAILS_HOURS_UPDATE)) {
            preparedStatement.setInt(1, courseDetails.getHours());
            preparedStatement.setLong(2, courseDetails.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateStartOfClass(CourseDetails courseDetails) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COURSE_DETAILS_START_OF_CLASS_UPDATE)) {
            preparedStatement.setTime(1, Time.valueOf(courseDetails.getStartOfClass()));
            preparedStatement.setLong(2, courseDetails.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateCost(CourseDetails courseDetails) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COURSE_DETAILS_COST_UPDATE)) {
            preparedStatement.setBigDecimal(1, courseDetails.getCost());
            preparedStatement.setLong(2, courseDetails.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateTeacher(CourseDetails courseDetails) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COURSE_DETAILS_TEACHER_UPDATE)) {
            preparedStatement.setLong(1, courseDetails.getTeacher().getId());
            preparedStatement.setLong(2, courseDetails.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateStartEnd(CourseDetails courseDetails) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COURSE_DETAILS_START_END_UPDATE)) {
            preparedStatement.setDate(1, Date.valueOf(courseDetails.getStartCourse()));
            preparedStatement.setDate(2, Date.valueOf(courseDetails.getEndCourse()));
            preparedStatement.setLong(3, courseDetails.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public List<CourseDetails> findAll() throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }
}
