package org.example.mysql.impl;


import org.example.ConnectionHolder;
import org.example.DAOException;
import org.example.DBUtils;
import org.example.ServiceException;
import org.example.dao.UsersDao;
import org.example.entity.User;

import java.sql.*;
import java.sql.SQLException;

public class UserDaoImpl implements UsersDao {

    private static UserDaoImpl userDao;

    private static final String insert = "INSERT INTO swimmers" + "(id, username, email, phone, password) VALUES" +
            "(?,?,?,?,?);";
    private static final String update = "UPDATE swimmers SET username=?, email=?, phone=?, password=? WHERE id=?";

    public static void main(String[] args) {

    }

    public UserDaoImpl() {
    }

    static UsersDao getInstance() {
        if(userDao == null) userDao = new UserDaoImpl();
        return userDao;
    }

    @Override
    public Integer create(User user) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(insert);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,user.getId());
            preparedStatement.setString(2,user.getUsername());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setInt(4, user.getPhone());
            preparedStatement.setString(5, user.getPassword());
            connection.commit();


        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        } finally {
            DBUtils.closeStatement(preparedStatement);
        }
        return null;
    }

    @Override
    public User read(Integer id) throws DAOException {
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users WHERE id=" + id);
            if(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getInt("phone"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        } finally {
            DBUtils.closeStatement(statement);
            DBUtils.closeResultSet(resultSet);
        }
        return null;
    }

    @Override
    public boolean update(User user) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setInt(3, user.getPhone());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5,user.getId());

            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return true;
            }

        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        } finally {
            DBUtils.closeStatement(preparedStatement);
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        Connection connection;
        Statement statement = null;
        try{
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            int i = statement.executeUpdate("DELETE FROM users WHERE id=" + id);

            if (i == 1){
                return true;
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        } finally {
            DBUtils.closeStatement(statement);
        }
        return false;
    }
}
