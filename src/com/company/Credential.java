package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Credential extends JFrame{
    String emailId;
    char[] password;
    String role;
    JTextField txtMail;
    JPasswordField txtPassword;
    JLabel lblMail,lblPassword,lbl;
    JButton logIn;
    Container c;

    Credential(){
        super("User's Credential");
        setBounds(400,200,600,400);
        c = getContentPane();
        c.setLayout(null);

        lbl = new JLabel("Credential : ");
        lbl.setFont(new Font("Arial",Font.BOLD,20));
        lbl.setBounds(50,25,200,40);
        c.add(lbl);

        lblMail = new JLabel("Email : ");
        lblMail.setFont(new Font("Arial",Font.PLAIN,17));
        lblMail.setBounds(100,100,200,40);
        c.add(lblMail);
        txtMail = new JTextField();
        txtMail.setBounds(200,100,300,40);
        c.add(txtMail);

        lblPassword = new JLabel("Password : ");
        lblPassword.setFont(new Font("Arial",Font.PLAIN,17));
        lblPassword.setBounds(100,175,200,40);
        c.add(lblPassword);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(200,175,300,40);
        txtPassword.setEchoChar('*');
        txtPassword.setFont(new Font("Arial",Font.BOLD,20));
        c.add(txtPassword);

        logIn = new JButton("Login");
        logIn.setFont(new Font("Arial",Font.BOLD,17));
        logIn.setBounds(250,250,100,40);
        c.add(logIn);

        logIn.addActionListener(e -> {
            emailId = txtMail.getText();
            password = txtPassword.getPassword();
            try{
                PreparedStatement stmt = Main.con.prepareStatement("select * from credential where email = ?");
                stmt.setString(1,emailId);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    role = rs.getString(3);
                }
                if(role.equals("admin")){
                    StudentForm sf = new StudentForm();
                    sf.setVisible(true);
                    sf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }else{
                    StudentResult sr = new StudentResult();
                    sr.setVisible(true);
                    sr.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                }
            }catch (SQLException sq){
                sq.printStackTrace();
            }
            txtMail.setText(null);
            txtPassword.setText(null);
        });
    }
}
