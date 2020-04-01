package org.example.swimmerdao;

import org.example.JDBCUtils;

import java.sql.*;

public class SwimmerDaoImpl implements SwimmersDao {

    public static void main(String[] args) {

    }

    public Swimmer getSwimmer(int id) {

        try(Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery("SELECT * FROM swimmers WHERE id=" + id);
            if(resultSet.next()){
                Swimmer swimmer = new Swimmer();
                swimmer.setId(resultSet.getInt("id"));
                swimmer.setName(resultSet.getString("name"));
                swimmer.setAge(resultSet.getInt("age"));
                swimmer.setSex(resultSet.getString("sex"));
                swimmer.setWeight(resultSet.getInt("weight"));
                swimmer.setHeight(resultSet.getInt("height"));
                return swimmer;
            }

        }catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return null;
    }


    public boolean insertSwimmer(Swimmer swimmer) {
        String insert = "INSERT INTO swimmers" + "(id, name, age, sex, weight, height, user_id, country_id, coach_id) VALUES" +
                "(?,?,?,?,?,?,?,?,?);";
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insert);){
            connection.setAutoCommit(false);
            preparedStatement.setInt(1,swimmer.getId());
            preparedStatement.setString(2,swimmer.getName());
            preparedStatement.setInt(3,swimmer.getAge());
            preparedStatement.setString(4,swimmer.getSex());
            preparedStatement.setInt(5,swimmer.getWeight());
            preparedStatement.setInt(6,swimmer.getHeight());
            preparedStatement.setInt(7,swimmer.getUser_id());
            preparedStatement.setInt(8,swimmer.getCountry_id());
            preparedStatement.setInt(9,swimmer.getCoach_id());

            connection.commit();


        } catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return false;
    }

    public boolean updateSwimmer(Swimmer swimmer) {
        String update = "UPDATE swimmers SET name=?, age=?, sex=?, weight=?, height=? WHERE id=?";
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(update)){

            preparedStatement.setString(1,swimmer.getName());
            preparedStatement.setInt(2,swimmer.getAge());
            preparedStatement.setString(3,swimmer.getSex());
            preparedStatement.setInt(4,swimmer.getWeight());
            preparedStatement.setInt(5,swimmer.getHeight());
            preparedStatement.setInt(6,swimmer.getId());
            int i = preparedStatement.executeUpdate();
            if (i == 1){
                return true;
            }

        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return false;
    }

    public boolean deleteSwimmer(int id){
        try(Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement()){
            int i = statement.executeUpdate("DELETE FROM swimmers WHERE id=" + id);

            if (i == 1){
                return true;
            }
        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }

        return false;
    }

}
