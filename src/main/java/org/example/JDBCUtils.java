package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCUtils {
    private static String userName = "root";
    private static String password = "Jummetmokai2145";
    private static String connectionURL = "jdbc:mysql://localhost:3306/swimmers_schema";

    public static Connection getConnection(){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(connectionURL, userName, password);

        } catch (SQLException e) {
            printSQLException(e);
        }
        return connection;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
