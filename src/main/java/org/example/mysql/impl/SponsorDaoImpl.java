package org.example.mysql.impl;

import org.example.ConnectionHolder;
import org.example.DAOException;
import org.example.DBUtils;
import org.example.ServiceException;
import org.example.dao.SponsorsDao;
import org.example.entity.Sponsor;

import java.sql.*;

public class SponsorDaoImpl implements SponsorsDao {

    private static SponsorDaoImpl sponsorDao;
    final private static String insert = "INSERT INTO sponsors" + "(id, name) VALUES (?,?);";
    final private static String update = "UPDATE sponsors SET name=? WHERE id=?";

    public static void main(String[] args) {

    }

    public SponsorDaoImpl() {
    }

    static SponsorsDao getInstance(){
        if(sponsorDao == null) sponsorDao = new SponsorDaoImpl();
        return sponsorDao;
    }

    @Override
    public Integer create(Sponsor sponsor) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(insert);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,sponsor.getId());
            preparedStatement.setString(2,sponsor.getName());

            connection.commit();

        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        } finally {
            DBUtils.closeStatement(preparedStatement);
        }
        return null;
    }

    @Override
    public Sponsor read(Integer id) throws DAOException {
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM sponsors WHERE id=" + id);
            connection.setAutoCommit(false);
            if(resultSet.next()){
                Sponsor sponsor = new Sponsor();
                sponsor.setId(resultSet.getInt("id"));
                sponsor.setName(resultSet.getString("name"));
            }
            connection.commit();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        } finally {
            DBUtils.closeStatement(statement);
            DBUtils.closeResultSet(resultSet);
        }
        return null;
    }

    @Override
    public boolean update(Sponsor sponsor) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(update);
            connection.setAutoCommit(false);

            preparedStatement.setString(1,sponsor.getName());
            preparedStatement.setInt(2,sponsor.getId());

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
            int i = statement.executeUpdate("DELETE FROM sponsors WHERE id=" + id);

            if (i == 1) {
                return true;
            }
        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        } finally {
            DBUtils.closeStatement(statement);
        }
        return false;
    }
}
