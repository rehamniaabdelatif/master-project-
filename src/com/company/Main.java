package com.company;


import java.sql.SQLException;

public class Main{

    public static void main(String[] args) throws SQLException {
        new Sql();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm();
            }
        });

    }
}
