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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CourseDetailsDaoImpl implements CourseDetailsDao {
    private static final Logger logger = LogManager.getLogger(CourseDetailsDaoImpl.class);
    private static final String FIND_COURSE_DETAILS_BY_COURSE_ID = "SELECT course_detail_id, number_of_hours, description, is_test, start_course, end_course, start_of_class, cost, course_id, course_name, teacher_id, name, surname, photo FROM course.course_details INNER JOIN course.courses ON fk_course_id = course_id INNER JOIN course.teachers ON fk_teacher_name_id = teacher_id WHERE course_id = ?";

    @Override
    public Optional<CourseDetails> findEntityById(Long id) throws DaoException {
        Optional<CourseDetails> courseDetailsOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_COURSE_DETAILS_BY_COURSE_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CourseDetails courseDetails = new CourseDetails();
                Course course = new Course();
                Teacher teacher = new Teacher();
                courseDetails.setId(resultSet.getLong(1));
                courseDetails.setHours(resultSet.getInt(2));
                courseDetails.setDescription(resultSet.getString(3));
                courseDetails.setTest(resultSet.getBoolean(4));
                courseDetails.setStartCourse(resultSet.getDate(5).toLocalDate());
                courseDetails.setEndCourse(resultSet.getDate(6).toLocalDate());
                courseDetails.setStartOfClass(resultSet.getTime(7).toLocalTime());
                courseDetails.setCost(resultSet.getBigDecimal(8));
                course.setId(resultSet.getLong(9));
                course.setName(resultSet.getString(10));
                teacher.setId(resultSet.getLong(11));
                teacher.setName(resultSet.getString(12));
                teacher.setSurname(resultSet.getString(13));
                teacher.setPhoto(resultSet.getString(14));
                courseDetails.setCourse(course);
                courseDetails.setTeacher(teacher);
                courseDetailsOptional = Optional.ofNullable(courseDetails);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return courseDetailsOptional;
    }

    @Override
    public List<CourseDetails> findAll() throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(CourseDetails courseDetails) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }
}
