package org.example;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

public class StatementsSandbox {
    static final String userName = "root";
    static final String password = "Jummetmokai2145";
    static final String connectionURL = "jdbc:mysql://localhost:3306/swimmers_schema";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(connectionURL, userName, password)) {

//        Statement statement = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;

//        try(Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
//                    ResultSet.CONCUR_READ_ONLY)) {
//             statement = connection.createStatement(
//                    ResultSet.TYPE_FORWARD_ONLY,
//                    ResultSet.CONCUR_READ_ONLY);

//            String useDB = "USE swimmers_schema";
            String SQL = "SELECT * FROM swimmers";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
//                preparedStatement.execute(useDB);

                try (ResultSet resultSet = preparedStatement.executeQuery(SQL)) {


                resultSet.first();
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String weight = resultSet.getString("weight");


                System.out.println("name: " + name);
                System.out.println("age: " + age);
                System.out.println("weight: " + weight);
                System.out.println("====================");


                SQL = "UPDATE swimmers SET age=?, weight=? WHERE name=?";
                preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.setInt(1, 25);
                preparedStatement.setInt(2, 80);
                preparedStatement.setString(3, "David");
                System.out.println("Rows impacted: " + preparedStatement.executeUpdate());


                SQL = "SELECT * FROM swimmers";
                try(ResultSet resultSet1 = preparedStatement.executeQuery(SQL)) {
//                    resultSet = preparedStatement.executeQuery(SQL);

                    resultSet1.first();
                    name = resultSet1.getString("name");
                    age = resultSet1.getInt("age");
                    weight = resultSet1.getString("weight");


                    System.out.println("name: " + name);
                    System.out.println("age: " + age);
                    System.out.println("weight: " + weight);
                    System.out.println("====================");

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
