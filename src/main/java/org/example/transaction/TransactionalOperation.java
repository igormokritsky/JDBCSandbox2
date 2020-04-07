package org.example.transaction;

import org.example.DAOException;

public interface TransactionalOperation<T> {
    T execute() throws DAOException;
}
