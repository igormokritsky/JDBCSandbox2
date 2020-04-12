package org.example.mysql.impl;


import org.example.DBUtils;
import org.example.ServiceException;
import org.example.entity.Coach;
import org.example.dao.CoachesDao;

import java.sql.*;
import java.sql.SQLException;


public class CoachDaoImpl implements CoachesDao {

    private static final String insert = "INSERT INTO coaches" + "(id, name, awards, country_id, user_id) VALUES" +
            "(?,?,?,?,?);";

    private static final String update = "UPDATE coaches SET name=?, awards=? WHERE id=?";


    private Connection connection;

    public CoachDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public static void main(String[] args) {

    }

@Override
    public Coach getCoach(int id){
        Connection connection = DBUtils.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM coaches WHERE id=" + id);
            if(resultSet.next()){
                Coach coach = new Coach();
                coach.setId(resultSet.getInt("id"));
                coach.setName(resultSet.getString("name"));
                coach.setAwards(resultSet.getString("awards"));
                return coach;
            }

        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        } finally {
            DBUtils.closeResultSet(resultSet);
            DBUtils.closeStatement(statement);
        }
        return null;
    }

    @Override
    public boolean insertCoach(Coach coach){
        Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(insert);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,coach.getId());
            preparedStatement.setString(2,coach.getName());
            preparedStatement.setString(3,coach.getAwards());
            preparedStatement.setInt(4,coach.getCountry_id());
            preparedStatement.setInt(5,coach.getUser_id());
            preparedStatement.executeUpdate();
            connection.commit();

            } catch (SQLException e) {
                throw new ServiceException(e.getMessage(), e);
            } finally {
                DBUtils.closeStatement(preparedStatement);
            }
        return false;
    }

    @Override
    public boolean updateCoach(Coach coach){
        Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, coach.getName());
            preparedStatement.setString(2,coach.getAwards());
            preparedStatement.setInt(3, coach.getId());
            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return true;
            }

        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        } finally {
            DBUtils.closeStatement(preparedStatement);
        }
        return false;
    }
    @Override
    public boolean deleteCoach(int id) {
        Connection connection = DBUtils.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            int i = statement.executeUpdate("DELETE FROM coaches WHERE id=" + id);

            if (i == 1){
                return true;
            }
        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }finally {
            DBUtils.closeStatement(statement);
        }

        return false;
    }
}
