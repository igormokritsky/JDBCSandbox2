package org.example.transaction.impl;

import org.example.DAOException;

public interface TransactionManager {
    <T> T executeTransaction(TransactionalOperation<T> operation) throws DAOException, DAOException;
}
