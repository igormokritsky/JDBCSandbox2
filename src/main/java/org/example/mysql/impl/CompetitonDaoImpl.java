package org.example.mysql.impl;

import org.example.ConnectionHolder;
import org.example.DAOException;
import org.example.DBUtils;
import org.example.ServiceException;
import org.example.entity.Competition;
import org.example.dao.CompetitionsDao;

import java.sql.*;

public class CompetitonDaoImpl implements CompetitionsDao {

    private static CompetitonDaoImpl competitonDao;

    final private static String insert = "INSERT INTO competitions" + "(id, county_id, style_id, distance) VALUES (?,?,?,?);";
    final private static String update = "UPDATE competitions SET county_id=?, style_id=?, distance=? WHERE id=?";

    public static void main(String[] args) {

    }

    public CompetitonDaoImpl() {
    }

    static CompetitionsDao getInstance() {
        if (competitonDao == null) competitonDao = new CompetitonDaoImpl();
        return competitonDao;

    }

    @Override
    public Integer create(Competition competition) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(insert);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,competition.getId());
            preparedStatement.setInt(2,competition.getCountry_id());
            preparedStatement.setInt(3,competition.getStyle_id());
            preparedStatement.setInt(4,competition.getDistance());

            connection.commit();

        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        } finally {
            DBUtils.closeStatement(preparedStatement);
        }
        return null;
    }

    @Override
    public Competition read(Integer id) throws DAOException {
        Connection connection ;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM competitions WHERE id=" + id);
            connection.setAutoCommit(false);
            if(resultSet.next()){
                Competition competition = new Competition();
                competition.setId(resultSet.getInt("id"));
                competition.setCountry_id(resultSet.getInt("country_id"));
                competition.setStyle_id(resultSet.getInt("style_id"));
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
    public boolean update(Competition competition) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(update);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,competition.getCountry_id());
            preparedStatement.setInt(2,competition.getStyle_id());
            preparedStatement.setInt(3,competition.getDistance());
            preparedStatement.setInt(4,competition.getId());

            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return true;
            }
            connection.commit();
        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }finally {
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
            int i = statement.executeUpdate("DELETE FROM competitions WHERE id=" + id);

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
