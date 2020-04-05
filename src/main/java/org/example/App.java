package org.example;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;


public class App {

    public static void main( String[] args ) throws ClassNotFoundException, SQLException {
        String userName = "root";
        String password = "Jummetmokai2145";
        String connectionURL = "jdbc:mysql://localhost:3306/swimmers_schema";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(connectionURL, userName, password);

//        connection.setAutoCommit(false);

        Statement statement = connection.createStatement(
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY);


        String SQL = "SELECT * FROM swimmers";

        ResultSet resultSet = statement.executeQuery(SQL);
        resultSet.first();
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        String sex = resultSet.getString("sex");

        System.out.println("Record 1");
        System.out.println("id: " + id);
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        System.out.println("sex: " + sex);

        resultSet.next();

        id = resultSet.getInt("id");
        name = resultSet.getString("name");
        age = resultSet.getInt("age");
        sex = resultSet.getString("sex");

        System.out.println("====================");
        System.out.println("Record 2");
        System.out.println("id: " + id);
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        System.out.println("sex: " + sex);


//        SQL = "INSERT INTO users VALUES (9, 'paullanka', 'paul@gmail.com', 138943932, 'paul12039')";
//        statement.executeUpdate(SQL);
//        вывожу то что создал запросом выше


        SQL = "SELECT * FROM users";
        resultSet = statement.executeQuery(SQL);
        resultSet.last();
        String username = resultSet.getString("username");
        int phone = resultSet.getInt("phone");

        System.out.println("====================");
        System.out.println("Record 3");
        System.out.println("Username: " + username);
        System.out.println("Phone: " + phone);
        resultSet.previous();

        username = resultSet.getString("username");
        phone = resultSet.getInt("phone");

        System.out.println("====================");
        System.out.println("Record 4");
        System.out.println("Username: " + username);
        System.out.println("Phone: " + phone);



    }



}

