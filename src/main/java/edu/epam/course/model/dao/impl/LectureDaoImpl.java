package edu.epam.course.model.dao.impl;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.connection.ConnectionPool;
import edu.epam.course.model.dao.LectureDao;
import edu.epam.course.model.entity.Course;
import edu.epam.course.model.entity.Lecture;
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
 * The type Lecture dao.
 */
public class LectureDaoImpl implements LectureDao {
    /**
     * The constant logger.
     */
    private static final Logger logger = LogManager.getLogger(LectureDaoImpl.class);
    /**
     * The constant find lecture by id.
     */
    private static final String FIND_ALL_LECTURE_BY_ID = "SELECT lecture_id, lecture, course_id, course_name  FROM course.lectures INNER JOIN course.courses ON fk_lecture_x_course_id = course_id WHERE course_id = ?";
    /**
     * The constant add lecture.
     */
    static final String ADD_LECTURE = "INSERT INTO `lectures` (`lecture`, `fk_lecture_x_course_id`) VALUES (?, ?)";
    /**
     * The constant delete lecture.
     */
    private static final String DELETE_LECTURE = "DELETE FROM course.lectures WHERE lecture_id = ?";
    /**
     * The constant update lecture.
     */
    private static final String UPDATE_LECTURE = "UPDATE course.lectures SET lecture = ? WHERE lecture_id = ?";
    /**
     * The constant find lecture by id.
     */
    private static final String FIND_LECTURE_BY_ID_AND_COURSE_ID = "SELECT lecture_id, lecture, course_id, course_name, enrollment_active FROM course.lectures INNER JOIN course.courses ON fk_lecture_x_course_id = course_id WHERE lecture_id = ? AND course_id = ?";

    @Override
    public boolean add(Lecture lecture) throws DaoException {
        boolean isAdd;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_LECTURE)) {
            preparedStatement.setString(1, lecture.getLecture());
            preparedStatement.setLong(2, lecture.getCourse().getId());

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
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LECTURE)) {
            preparedStatement.setLong(1, id);

            isDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isDelete;
    }

    @Override
    public List<Lecture> findAllLectureByCourseId(Long id) throws DaoException {
        List<Lecture> lectures = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_LECTURE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Course course = Course.builder()
                        .setId(resultSet.getLong(3))
                        .setName(resultSet.getString(4))
                        .build();
                Lecture lecture = Lecture.builder()
                        .setId(resultSet.getLong(1))
                        .setLecture(resultSet.getString(2))
                        .setCourse(course)
                        .build();
                lectures.add(lecture);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return lectures;
    }

    @Override
    public boolean update(Lecture lecture) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LECTURE)) {
            preparedStatement.setString(1, lecture.getLecture());
            preparedStatement.setLong(2, lecture.getId());

            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return isUpdate;
    }

    @Override
    public Optional<Lecture> findLectureByIdAndCourseId(Long lectureId, Long courseId) throws DaoException {
        Optional<Lecture> lectureOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_LECTURE_BY_ID_AND_COURSE_ID)) {
            preparedStatement.setLong(1, lectureId);
            preparedStatement.setLong(2, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Course course = Course.builder()
                        .setId(resultSet.getLong(3))
                        .setName(resultSet.getString(4))
                        .setEnrollmentActive(resultSet.getBoolean(5))
                        .build();
                Lecture lecture = Lecture.builder()
                        .setId(resultSet.getLong(1))
                        .setLecture(resultSet.getString(2))
                        .setCourse(course)
                        .build();
                lectureOptional = Optional.of(lecture);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return lectureOptional;
    }

    @Override
    public Optional<Lecture> findById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Lecture> findAll() throws DaoException {
        throw new UnsupportedOperationException();
    }

}
