package org.example.service.impl;

import org.example.DAOException;
import org.example.ServiceException;
import org.example.dao.SwimCompetsDao;
import org.example.entity.SwimCompet;
import org.example.entity.User;
import org.example.service.CompetResultService;
import org.example.transaction.TransactionManager;
import org.example.transaction.TransactionalOperation;

public class CompetResultServiceImpl implements CompetResultService {

    private final SwimCompetsDao swimCompetsDao;
    private final TransactionManager transactionManager;

    public CompetResultServiceImpl(SwimCompetsDao swimCompetsDao, TransactionManager transactionManager) {
        this.swimCompetsDao = swimCompetsDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public SwimCompet getResult(final int id) {
        SwimCompet swimCompet;
        try{
            swimCompet = transactionManager.executeTransaction(new TransactionalOperation<SwimCompet>() {
                @Override
                public SwimCompet execute() throws DAOException {
                    return swimCompetsDao.getSwimCompet(id);
                }
            });
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return swimCompet;
    }

    @Override
    public boolean createResult(final SwimCompet swimCompet) {
        try {
            transactionManager.executeTransaction(new TransactionalOperation<Object>() {
                @Override
                public Object execute() throws DAOException {
                    return swimCompetsDao.insertSwimCompet(swimCompet);
                }
            });
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean deleteResult(final int id) {
        try {
            transactionManager.executeTransaction(new TransactionalOperation<Object>() {
                @Override
                public Object execute() throws DAOException {
                    return swimCompetsDao.deleteSwimCompet(id);
                }
            });
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return false;
    }
}
