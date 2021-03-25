package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/master";
        String username = "root";
        String password = "root";

        try {
            Connection connection =  DriverManager.getConnection(url, username, password);
            System.out.println("connected");
        }catch (SQLException e){
            System.out.println("Oops");
            e.printStackTrace();
        }

    }
}
