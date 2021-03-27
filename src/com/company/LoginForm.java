package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginForm implements ActionListener{
    JFrame stage;
    JPanel panel;
    JLabel userLabel;
    JTextField userName;
    JLabel passwordLabel;
    JTextField password;
    JButton login;
    JButton add;
    JLabel success;

    public LoginForm(){
        stage = new JFrame();
        stage.setTitle("Login");
        stage.setVisible(true);
        stage.setSize(new Dimension(400, 300));
        stage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //stage.setResizable(false);

        panel = new JPanel();
        stage.add(panel);
        panel.setLayout(null);

        userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        userName = new JTextField();
        userName.setBounds(100, 20, 165, 25);
        panel.add(userName);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        password = new JTextField();
        password.setBounds(100, 50, 165, 25);
        panel.add(password);

        login = new JButton("Login");
        login.setBounds(100, 80, 165, 25);
        login.setBackground(new Color(78, 68, 68));
        login.setForeground(new Color(252, 219, 219));
        login.setFocusable(false);
        panel.add(login);
        login.addActionListener((ActionListener) this);

        add = new JButton("add User");
        add.setBounds(100, 110, 165, 25);
        add.setBackground(new Color(78, 68, 68));
        add.setForeground(new Color(252, 219, 219));
        add.setFocusable(false);
        panel.add(add);
        add.addActionListener((ActionListener) this);

        success = new JLabel("");
        success.setBounds(100, 130, 300, 25);
        panel.add(success);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (login.equals(e.getSource())){
            int ID = Sql.get_user_id(userName.getText(), password.getText());
            if (ID != 0){
                stage.dispose();
                new NoteGUI(ID);
            }else
                success.setText("is not exist");

        }
        else if (add.equals(e.getSource())){
            try {
                Sql.add_user(userName.getText(), password.getText());
                success.setText("success");
            } catch (SQLException throwables) {
                //throwables.printStackTrace();
                success.setText("is exist");
            }
        }

    }
}
