package org.example.mysql.impl;

import org.example.ConnectionHolder;
import org.example.DAOException;
import org.example.DBUtils;
import org.example.ServiceException;
import org.example.dao.SwimmerSponsorsDao;
import org.example.entity.SwimmersSponsor;

import java.sql.*;

public class SwimmersSponsorDaoImpl implements SwimmerSponsorsDao {

    private static SwimmersSponsorDaoImpl swimmersSponsorDao;
    final private static String insert = "INSERT INTO sponsors" +
            "(id, swimmer_id, sponsor_id, contract_amount) VALUES (?,?,?,?);";
    final private static String update =
            "UPDATE sponsors SET swimmer_id=?, sponsor_id=?, contract_amount=? WHERE id=?";

    public static void main(String[] args) {

    }

    public SwimmersSponsorDaoImpl() {
    }

    static SwimmerSponsorsDao getInstance() {
        if(swimmersSponsorDao == null) swimmersSponsorDao = new SwimmersSponsorDaoImpl();
        return swimmersSponsorDao;
    }

    @Override
    public Integer create(SwimmersSponsor swimmersSponsor) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(insert);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1, swimmersSponsor.getId());
            preparedStatement.setInt(2, swimmersSponsor.getSwimmer_id());
            preparedStatement.setInt(3, swimmersSponsor.getSponsor_id());
            preparedStatement.setInt(4, swimmersSponsor.getContract_amount());

            connection.commit();
        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        } finally {
            DBUtils.closeStatement(preparedStatement);
        }
        return null;
    }

    @Override
    public SwimmersSponsor read(Integer id) throws DAOException {
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionHolder.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM swimmers_sponsors WHERE id=" + id);
            connection.setAutoCommit(false);
            if(resultSet.next()){
                SwimmersSponsor swimmersSponsor = new SwimmersSponsor();
                swimmersSponsor.setId(resultSet.getInt("id"));
                swimmersSponsor.setSwimmer_id(resultSet.getInt("swimmer_id"));
                swimmersSponsor.setSponsor_id(resultSet.getInt("sponsor_id"));
                swimmersSponsor.setContract_amount(resultSet.getInt("contract_amount"));
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
    public boolean update(SwimmersSponsor swimmersSponsor) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(update);
            connection.setAutoCommit(false);

            preparedStatement.setInt(1, swimmersSponsor.getSwimmer_id());
            preparedStatement.setInt(2, swimmersSponsor.getSponsor_id());
            preparedStatement.setInt(3, swimmersSponsor.getContract_amount());
            preparedStatement.setInt(4, swimmersSponsor.getId());

            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return true;
            }
            connection.commit();
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
            int i = statement.executeUpdate("DELETE FROM swimmers_sponsors WHERE id=" + id);

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
