package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.*;
import org.apache.commons.dbcp.*;

public class ConnectionHolder {

    private static final String userName = "root";
    private static final String password = "Jummetmokai2145";
    private static final String connectionURL =
            "jdbc:mysql://localhost:3306/swimmers_schema?useUnicode=true&serverTimezone=UTC";


    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

//    public static Connection getConnection() {
//        Connection connection = null;
//
//        return connectionHolder.get();
//    }


    public static void setConnection(Connection connection){
        connectionHolder.set(connection);
    }

    private static BasicDataSource basicDataSource = new BasicDataSource();
    static {
        basicDataSource.setUrl(connectionURL);
        basicDataSource.setUsername(userName);
        basicDataSource.setPassword(password);
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(10);
        basicDataSource.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }

    public ConnectionHolder() {
    }
}
