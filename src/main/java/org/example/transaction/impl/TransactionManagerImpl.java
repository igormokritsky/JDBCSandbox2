package org.example.transaction.impl;

import org.apache.log4j.Logger;
import org.example.DAOException;
import org.example.ConnectionHolder;
import org.example.DBUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManagerImpl implements TransactionManager {
    private static final Logger LOG = Logger.getLogger(TransactionManagerImpl.class);

    private DataSource dataSource;

    public TransactionManagerImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public <T> T executeTransaction(TransactionalOperation<T> operation) throws DAOException {
        Connection connection;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new DAOException("Can't get database connection", e);
        }
        T result;
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            ConnectionHolder.setConnection(connection);
            result = operation.execute();
            connection.commit();
        } catch (SQLException e) {
            DBUtils.rollback(connection);
            LOG.error("Rollback transaction.");
            throw new DAOException(e.getMessage(), e);
        } finally {
            ConnectionHolder.setConnection(null);
            DBUtils.closeConnection(connection);
        }
        return result;
    }
}
