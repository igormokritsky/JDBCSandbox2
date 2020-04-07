package org.example.transaction;

import org.example.DAOException;

public interface TransactionManager {
    <T> T executeTransaction(TransactionalOperation<T> operation) throws DAOException;
}
