package org.example.coachdao;


import org.example.JDBCUtils;

import java.sql.*;
import java.sql.SQLException;


public class CoachDaoImpl implements CoachesDao {

    public static void main(String[] args) {
        CoachDaoImpl coachDao = new CoachDaoImpl();


    }


    public Coach getCoach(int id){

        try(Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery("SELECT * FROM coaches WHERE id=" + id);
            if(resultSet.next()){
                Coach coach = new Coach();
                coach.setId(resultSet.getInt("id"));
                coach.setName(resultSet.getString("name"));
                coach.setAwards(resultSet.getString("awards"));
                return coach;

            }

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return null;
    }


    public boolean insertCoach(Coach coach){
        String insert = "INSERT INTO coaches" + "(id, name, awards, country_id, user_id) VALUES" +
                "(?,?,?,?,?);";
        try(Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(insert);){
            connection.setAutoCommit(false);

            preparedStatement.setInt(1,coach.getId());
            preparedStatement.setString(2,coach.getName());
            preparedStatement.setString(3,coach.getAwards());
            preparedStatement.setInt(4,coach.getCountry_id());
            preparedStatement.setInt(5,coach.getUser_id());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return false;
    }


    public boolean updateCoach(Coach coach){
        String update = "UPDATE coaches SET name=?, awards=? WHERE id=?";
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(update)){
            preparedStatement.setString(1, coach.getName());
            preparedStatement.setString(2,coach.getAwards());
            preparedStatement.setInt(3, coach.getId());
            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return true;
            }

        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return false;
    }

    public boolean deleteCoach(int id) {

        try(Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement()){
            int i = statement.executeUpdate("DELETE FROM coaches WHERE id=" + id);

            if (i == 1){
                return true;
            }
        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }

        return false;
    }
}
