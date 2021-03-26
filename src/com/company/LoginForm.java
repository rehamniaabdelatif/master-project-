package com.company;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {
    public LoginForm(){

        JPanel title = new JPanel();
        title.setBackground(Color.BLUE);
        title.setBounds( 0, 0, 400, 100);
        JLabel text = new JLabel("LOGIN");

        //text.setVerticalAlignment(JLabel.HORIZONTAL);
        title.add(text);

        JPanel form = new JPanel();
        form.setBackground(Color.LIGHT_GRAY);
        form.setBounds( 0, 100, 400, 400);
        JTextField username = new JTextField();
        username.setPreferredSize(new Dimension(300,40));
        username.setBounds(20,20,300,40);
        form.add(username);
        JTextField password = new JTextField();
        password.setPreferredSize(new Dimension(300,40));
        form.add(password);



        JPanel b = new JPanel();
        b.setBackground(Color.black);
        b.setBounds( 500, 0, 400, 100);
        JButton login = new JButton("login");
        b.add(login);

        setTitle("login form");
        setResizable(false);
        setLayout(null);
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        add(title);
        add(form);
        add(b);
    }
}
