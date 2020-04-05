package org.example.mysql.impl;

import org.example.DBUtils;
import org.example.ServiceException;
import org.example.entity.Country;
import org.example.dao.CountriesDao;

import java.sql.*;

public class CountryDaoImpl implements CountriesDao {

    final private static String insert = "INSERT INTO countries" + "(id, country_name) VALUES (?,?);";
    final private static String update = "UPDATE countries SET country_name=? WHERE id=?";

    public static void main(String[] args) {

    }

    public Country getCountry(int id) {
        try(Connection connection = DBUtils.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM countries WHERE id=" + id);
            connection.setAutoCommit(false);
            if(resultSet.next()){
                Country country = new Country();
                country.setId(resultSet.getInt("id"));
                country.setCountry_name(resultSet.getString("country_name"));
                return country;
            }
            connection.commit();

        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return null;
    }

    public boolean insertCountry(Country country) {
        try(Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insert);){
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,country.getId());
            preparedStatement.setString(2,country.getCountry_name());

            connection.commit();

        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return false;
    }

    public boolean updateCountry(Country country) {
        try(Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(update)){
            connection.setAutoCommit(false);

            preparedStatement.setString(1,country.getCountry_name());
            preparedStatement.setInt(2,country.getId());

            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return true;
            }
            connection.commit();
        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return false;
    }

    public boolean deleteCountry(int id){
        try(Connection connection = DBUtils.getConnection();
            Statement statement = connection.createStatement()){
            int i = statement.executeUpdate("DELETE FROM countries WHERE id=" + id);

            if (i == 1){
                return true;
            }
        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return false;
    }

}
