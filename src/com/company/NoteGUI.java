package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class NoteGUI{
    JFrame stage;
    JPanel panel;
    JTable table;
    JTextField note;
    JButton button;
    JButton delete;
    JButton user;
    JButton update;
    DefaultTableModel model;
    JLabel success;

    ArrayList<Note> noteList;

    Object[] row = new Object[2];

    public NoteGUI(int Personid) {

        stage = new JFrame();
        stage.setTitle("Login");
        stage.setVisible(true);
        stage.setSize(new Dimension(430, 600));
        stage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stage.revalidate();
        //stage.setResizable(false);

        panel = new JPanel();
        stage.add(panel);
        panel.setLayout(null);

        note = new JTextField();
        note.setBounds(10, 20, 400, 25);
        panel.add(note);

        button = new JButton("add Note");
        button.setBounds(10, 50, 400, 25);
        button.setBackground(new Color(78, 68, 68));
        button.setForeground(new Color(252, 219, 219));
        button.setFocusable(false);
        button.addActionListener( new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 try {
                     success.setText("");

                     row[0] = Sql.add_note(note.getText(), Personid);
                     row[1] = note.getText();
                     model.addRow(row);
                     noteList =  Sql.get_note(Personid);
                 } catch (SQLException throwables) {
                     success.setText("is not working");
                 }
             }
         }

        );
        panel.add(button);

        //delete button
        delete = new JButton("delete Note");
        delete.setBounds(10, 75, 400, 25);
        delete.setBackground(new Color(78, 68, 68));
        delete.setForeground(new Color(252, 219, 219));
        delete.setFocusable(false);
        delete.addActionListener(e -> {
            int i = table.getSelectedRow();
            if (i > -1){
                String ID = table.getValueAt(i,0).toString();
                try {
                    Sql.delete_note(Integer.parseInt(ID));
                    model.removeRow(i);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

         });
        panel.add(delete);

/*
        user = new JButton("delete user");
        user.setBounds(10, 470, 400, 25);
        user.setBackground(new Color(78, 68, 68));
        user.setForeground(new Color(252, 219, 219));
        user.setFocusable(false);
        user.addActionListener(e -> {
            try {
                Sql.delete_user(Personid);
                stage.dispose();
                new LoginForm();
            } catch (SQLException throwables) {
                success.setText("not delete use");
            }

        });
        panel.add(user);

*/
        update = new JButton("update note");
        update.setBounds(10, 100, 400, 25);
        update.setBackground(new Color(78, 68, 68));
        update.setForeground(new Color(252, 219, 219));
        update.setFocusable(false);
        update.addActionListener(e -> {
            int i = table.getSelectedRow();
            try {
                String ID = table.getValueAt(i,0).toString();
                Sql.update(note.getText(), Integer.parseInt(ID));
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                addNoteDbIntoTable(Personid);

            } catch (SQLException throwables) {
                success.setText("not update note");
            }

        });
        panel.add(update);
        


        table = new JTable();

        Object[] columns = {"id", "Note"};

        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setBackground(Color.white);
        table.setForeground(Color.black);
        table.setSelectionBackground(Color.BLUE);
        table.setGridColor(Color.CYAN);
        table.setSelectionForeground(Color.LIGHT_GRAY);
        table.setRowHeight(30);
        table.setAutoCreateRowSorter(true);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(10, 160, 400, 300);
        panel.add(pane);

        success = new JLabel();
        success.setBounds(100, 0, 300, 25);
        panel.add(success);

        addNoteDbIntoTable(Personid);
        
        JButton signOut = new JButton("sign out");
        signOut.setBounds(10, 470, 400, 25);
        signOut.setBackground(new Color(78, 68, 68));
        signOut.setForeground(new Color(252, 219, 219));
        signOut.setFocusable(false);
        signOut.addActionListener( e-> {
        	stage.dispose();
        	new LoginForm();
        });
        panel.add(signOut);
        
        

    }

    /**
     * add note from database into the table
     */
    private void addNoteDbIntoTable(int Personid){
        try {
            success.setText("");
            noteList =  Sql.get_note(Personid);
            for (int i = 0; i < noteList.size(); i++) {
                row[0] = noteList.get(i).noteId;
                row[1] = noteList.get(i).note;
                model.addRow(row);
            }
        } catch (SQLException throwables) {
            success.setText("is not working");
        }
    }
}
