package org.example;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionSandbox {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String userName = "root";
        String password = "Jummetmokai2145";
        String connectionURL = "jdbc:mysql://localhost:3306/swimmers_schema";
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(connectionURL, userName, password);

            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatementCoach = connection.prepareStatement
                    ("UPDATE coaches SET name=?, awards=? WHERE id=?")) {
                preparedStatementCoach.setString(1, "Hasso");
                preparedStatementCoach.setString(2, "Halal Coach 2019");
                preparedStatementCoach.setInt(3, 1);
                preparedStatementCoach.executeUpdate();
            } catch (SQLException e) {
                connection.rollback();
                DBUtils.printSQLException(e);
            }

            try (PreparedStatement preparedStatementCountry = connection.prepareStatement
                    ("UPDATE counties SET county_name=? WHERE id=?")) {

                preparedStatementCountry.setString(1, "Kazachstan");
                preparedStatementCountry.setInt(2, 2);
                preparedStatementCountry.executeUpdate();
            } catch (SQLException e) {
                connection.rollback();
                DBUtils.printSQLException(e);
            }

            connection.commit();


    }

}
