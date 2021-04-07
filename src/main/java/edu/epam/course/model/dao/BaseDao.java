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

/**
 * The interface base dao.
 *
 * @param <K> the type parameter
 * @param <T> the type parameter
 */
public interface BaseDao <K, T extends Entity>{
    /**
     * The constant logger.
     */
    Logger logger = LogManager.getLogger(BaseDao.class);


    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<T> findAll() throws DaoException;


    /**
     * Find entity by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<T> findEntityById(K id) throws DaoException;


    /**
     * Create boolean.
     *
     * @param t the t
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(T t) throws DaoException;


    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean deleteById(K id) throws DaoException;

    /**
     * Close.
     *
     * @param statement the statement
     */
    default void close(Statement statement){
        try {
            if(statement!=null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    /**
     * Close.
     *
     * @param connection the connection
     */
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

    /**
     * RollBack.
     *
     * @param connection the connection
     */
    default void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e ){
            logger.error("rollback error");
        }
    }
}
