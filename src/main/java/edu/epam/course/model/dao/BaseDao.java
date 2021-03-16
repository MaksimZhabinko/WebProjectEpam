package edu.epam.course.model.dao;

import edu.epam.course.exception.DaoException;
import edu.epam.course.model.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public interface BaseDao <K, T extends Entity>{
    Logger logger = LogManager.getLogger(BaseDao.class);
    List<T> findAll() throws DaoException;
    Optional<T> findEntityById(K id) throws DaoException;
    boolean add(T t) throws DaoException;
    boolean deleteById(K id) throws DaoException;

    default void close(Statement statement){
        try {
            if(statement!=null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error(e);
            // todo нудно throw?
        }
    }

    default void close(Connection connection){
        try {
            if(connection!=null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
