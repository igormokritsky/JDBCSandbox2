package org.example.mysql.impl;

import org.example.ConnectionHolder;
import org.example.DAOException;
import org.example.DBUtils;
import org.example.ServiceException;
import org.example.dao.SwimCompetsDao;
import org.example.entity.SwimCompet;

import java.sql.*;

public class SwimCompetDaoImpl implements SwimCompetsDao {

    private static SwimCompetDaoImpl swimCompetDao;
    final private static String insert = "INSERT INTO sponsors" + "(id, competition_id, swimmer_id, time) VALUES (?,?,?,?);";
    final private static String update = "UPDATE sponsors SET competition_id=?, swimmer_id=?, time=? WHERE id=?";

    public static void main(String[] args) {

    }

    public SwimCompetDaoImpl() {
    }

    static SwimCompetsDao getInstance() {
        if(swimCompetDao == null) swimCompetDao = new SwimCompetDaoImpl();
        return swimCompetDao;
    }

    @Override
    public Integer create(SwimCompet swimCompet) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(insert);

            connection.setAutoCommit(false);
            preparedStatement.setInt(1,swimCompet.getId());
            preparedStatement.setInt(2,swimCompet.getCompetition_id());
            preparedStatement.setInt(3,swimCompet.getSwimmer_id());
            preparedStatement.setInt(4,swimCompet.getTime());

            connection.commit();
        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        } finally {
            DBUtils.closeStatement(preparedStatement);
        }
        return null;
    }

    @Override
    public SwimCompet read(Integer id) throws DAOException {
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM swim_compet WHERE id=" + id);
            connection.setAutoCommit(false);
            if(resultSet.next()){
                SwimCompet swimCompet = new SwimCompet();
                swimCompet.setId(resultSet.getInt("id"));
                swimCompet.setCompetition_id(resultSet.getInt("competition_id"));
                swimCompet.setSwimmer_id(resultSet.getInt("swimmer_id"));
                swimCompet.setTime(resultSet.getInt("time"));
            }
            connection.commit();

        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        } finally {
            DBUtils.closeStatement(statement);
            DBUtils.closeResultSet(resultSet);
        }
        return null;
    }

    @Override
    public boolean update(SwimCompet swimCompet) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;

        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(update);
            connection.setAutoCommit(false);
            preparedStatement.setInt(1,swimCompet.getCompetition_id());
            preparedStatement.setInt(2,swimCompet.getSwimmer_id());
            preparedStatement.setInt(3,swimCompet.getTime());
            preparedStatement.setInt(4,swimCompet.getId());

            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return true;
            }
            connection.commit();

        }catch (SQLException e) {
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
            int i = statement.executeUpdate("DELETE FROM swim_compet WHERE id=" + id);

            if (i == 1){
                return true;
            }
        } catch (SQLException e){
            throw new ServiceException(e.getMessage(), e);
        } finally {
            DBUtils.closeStatement(statement);
        }
        return false;
    }
}
