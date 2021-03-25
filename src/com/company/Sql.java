package com.company;

import java.sql.*;

public class Sql {
    private final String url = "jdbc:mysql://localhost:3306/master";
    private final String username = "root";
    private final String password = "root";

    protected static Connection connection;

    /**
     * connect database
     * @throws SQLException
     */
    public Sql(){
        try {
            connection =  DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    /**
     * add username and password in database (table User)
     * @param UserName
     * @param Password
     * @throws SQLException
     */
    public static void add_user( String UserName, String Password) throws SQLException {
        String sql = "INSERT INTO User(UserName,Pasword) values(?,?);";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, UserName);
        statement.setString(2, Password);

        int rows = statement.executeUpdate();

        if (rows > 0)
            System.out.println("good");
        statement.close();
    }


    /**
     * search form User table and return UserName( result.getString(UserName) )
     * @param UserName
     * @param Password
     * @return ResultSet you have UserName Password
     * @throws SQLException
     */
    public static ResultSet select_user( String UserName, String Password) throws SQLException {
        String sql = "SELECT * FROM User;";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        return result;
    }

    public static ResultSet select_note( String UserName, String Password) throws SQLException {
        String sql = "SELECT * FROM Note;";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        return result;
    }


    /**
     * delete user form table User
     * @param UserName
     * @throws SQLException
     */
    public static void delete_user( String UserName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM User WHERE UserName = " + UserName + ";");
        statement.executeUpdate();
    }

}
