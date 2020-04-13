package org.example.transaction.impl;

import org.example.DAOException;
public interface TransactionalOperation<T> {
    T execute() throws DAOException;
}
