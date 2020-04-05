package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtils {
    private static final String userName = "root";
    private static final String password = "Jummetmokai2145";
    private static final String connectionURL = "jdbc:mysql://localhost:3306/swimmers_schema";

    public static Connection getConnection(){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(connectionURL, userName, password);

        } catch (SQLException e) {
            
            throw new ServiceException(e.getMessage(), e);

        }
        return connection;
    }

//    public static void printSQLException(SQLException ex) {
//        for (Throwable e: ex) {
//            if (e instanceof SQLException) {
//                e.printStackTrace(System.err);
//                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
//                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
//                System.err.println("Message: " + e.getMessage());
//                Throwable t = ex.getCause();
//                while (t != null) {
//                    System.out.println("Cause: " + t);
//                    t = t.getCause();
//                }
//            }
//        }
//    }

}
