package com.company;

import java.sql.*;
import java.util.ArrayList;


class Note{
    int noteId;
    String note;

    Note(int noteId, String note){
        this.noteId = noteId;
        this.note = note;
    }
}

public class Sql {
    private final String url = "jdbc:mysql://localhost:3306/master";
    private final String username = "root";
    private final String password = "root";

    protected static Connection connection;

    /**
     * connect database
     */
    public Sql(){
        try {
            connection =  DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    /**
     * add user (username and password) in database (table User)
     * @param UserName
     * @param Password
     * @throws SQLException
     */
    public static void add_user( String UserName, String Password) throws SQLException {
        String sql = "INSERT INTO User(UserName,Pasword) values(?,?);";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, UserName);
        statement.setString(2, Password);
        statement.executeUpdate();

        statement.close();
    }

    /**
     * search form User table and return user id
     * @param UserName
     * @param Password
     * @return String user id
     */
    @SuppressWarnings("unused")
	public static int get_user_id( String UserName, String Password){
        int ID = -1;

        try {
            String sql = "SELECT Personid FROM User WHERE (UserName=? AND Pasword=?);";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, UserName);
            statement.setString(2, Password);
            ResultSet result = statement.executeQuery();

            result.next();
            ID = result.getInt("Personid");

        }catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        return ID;
    }


    /**
     * delete user
     * @param Personid
     * @throws SQLException
     */
    public static void delete_user( int Personid) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM User WHERE Personid=?;");
        statement.setInt(1, Personid);
        statement.executeUpdate();
    }


    /**
     * add note and return id of note
     * @param Note
     * @param Personid
     * @return ID of note
     * @throws SQLException
     */
    public static int add_note( String Note, int Personid) throws SQLException {
        String sql = "INSERT INTO Note (Note, Personid) values(?,?);";

        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, Note);
        statement.setInt(2, Personid);
        statement.executeUpdate();

        ResultSet Result = statement.getGeneratedKeys();
        int ID = -1;
        if (Result.next()){
            ID = Result.getInt(1);
        }

        statement.close();
        return ID;
    }


    /**
     * get note from user where returns the object loaded noteId and note
     * @param Personid
     * @return ArrayList<Note>
     * @throws SQLException
     */
    public static ArrayList<Note> get_note(int Personid) throws SQLException {
        String sql = "SELECT Noteid,Note FROM Note where Personid=?;";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,Personid);
        ResultSet result = statement.executeQuery();


        ArrayList<Note> note = new ArrayList<>();
        while (result.next())
            note.add( new Note(result.getInt("Noteid"), result.getString("Note")));


        return note;
    }

    public static void update(String UPDATE, int Noteid) throws SQLException {
        String sql = "UPDATE Note SET Note=? WHERE Noteid=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, UPDATE);
        statement.setInt(2, Noteid);
        statement.executeUpdate();
    }

    /**
     * delete note
     * @param Noteid
     * @throws SQLException
     */
    public static void delete_note( int Noteid) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Note WHERE Noteid=?;");
        statement.setInt(1, Noteid);
        statement.executeUpdate();
    }

}
