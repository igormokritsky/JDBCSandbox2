package org.example.service.impl;

import org.example.DAOException;
import org.example.dao.UsersDao;
import org.example.service.UserService;
import org.example.transaction.TransactionManager;
import org.example.entity.User;
import org.example.ServiceException;
import org.example.transaction.TransactionalOperation;

import java.sql.SQLException;


public class UserServiceImpl implements UserService {
    private final UsersDao usersDao;
    private final TransactionManager transactionManager;

    public UserServiceImpl(UsersDao usersDao, TransactionManager transactionManager) {
        this.usersDao = usersDao;
        this.transactionManager = transactionManager;
    }


    @Override
    public User getUser(final int id) {
        User user;
        try{
            user = transactionManager.executeTransaction(new TransactionalOperation<User>() {
                @Override
                public User execute() throws DAOException {
                    return usersDao.getUser(id);
                }
            });
        } catch (DAOException e){
            throw new ServiceException(e.getMessage(), e);
        }

        return user;
    }

    @Override
    public boolean createUser(final User user){
        try {
            transactionManager.executeTransaction(new TransactionalOperation<Object>() {
                @Override
                public Object execute() throws DAOException {
                    boolean id = usersDao.insertUser(user);
                    return id;
                }
            });
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean deleteUser(final int id) {
        try {
            transactionManager.executeTransaction(new TransactionalOperation<Object>() {
                @Override
                public Object execute() throws DAOException {
                    boolean delete = usersDao.deleteUser(id);
                    return delete;
                }
            });
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return false;
    }


}
