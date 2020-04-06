package org.example.mysql.impl;

import org.example.DBUtils;
import org.example.ServiceException;
import org.example.dao.SwimCompetsDao;
import org.example.entity.SwimCompet;

import java.sql.*;

public class SwimCompetDaoImpl implements SwimCompetsDao {

    final private static String insert = "INSERT INTO sponsors" + "(id, competition_id, swimmer_id, time) VALUES (?,?,?,?);";
    final private static String update = "UPDATE sponsors SET competition_id=?, swimmer_id=?, time=? WHERE id=?";

    public static void main(String[] args) {

    }

    public SwimCompet getSwimCompet(int id) {
        Connection connection = DBUtils.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try{
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

    public boolean insertSwimCompet(SwimCompet swimCompet) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try{
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
        return false;
    }

    public boolean updateSwimCompet(SwimCompet swimCompet) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = null;

        try{
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


    public boolean deleteSwimCompet(int id) {
        Connection connection = DBUtils.getConnection();
        Statement statement = null;
        try{
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
