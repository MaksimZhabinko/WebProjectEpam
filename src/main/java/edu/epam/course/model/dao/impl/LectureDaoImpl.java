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

public class LectureDaoImpl implements LectureDao {
    private static final Logger logger = LogManager.getLogger(LectureDaoImpl.class);
    private static final String FIND_LECTURE_BY_ID = "SELECT lecture_id, lecture, course_id, course_name  FROM course.lectures INNER JOIN course.courses ON fk_lecture_x_course_id = course_id WHERE course_id = ?";

    @Override
    public List<Lecture> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Lecture> findEntityById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean add(Lecture lecture) throws DaoException {
        return false;
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        return false;
    }

    @Override
    public List<Lecture> findAllById(Long id) throws DaoException {
        List<Lecture> lectures = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_LECTURE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lecture lecture = new Lecture();
                Course course = new Course();
                lecture.setId(resultSet.getLong(1));
                lecture.setLecture(resultSet.getString(2));
                course.setId(resultSet.getLong(3));
                course.setName(resultSet.getString(4));
                lecture.setCourse(course);
                lectures.add(lecture);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return lectures;
    }
}
