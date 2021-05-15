package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ResultForm extends JFrame {
    JTextField txtProgramId,txtEnrollmentNumber,txtSemester,txtRollNumber;
    JLabel lblProgramId,lblEnrollmentNumber,lblSemester,lblRollNumber;
    JButton submit,reset,update;
    Container c;

    ResultForm(){
        super("Result Form");
        setBounds(450,100,500,600);
        c = getContentPane();
        c.setLayout(null);
        lblProgramId = new JLabel("Prog Id : ");
        lblProgramId.setFont(new Font("Arial",Font.PLAIN,17));
        lblProgramId.setBounds(80,100,150,40);
        c.add(lblProgramId);
        txtProgramId = new JTextField();
        txtProgramId.setBounds(200,100,200,40);
        c.add(txtProgramId);
        lblEnrollmentNumber = new JLabel("Enrollment No. : ");
        lblEnrollmentNumber.setFont(new Font("Arial",Font.PLAIN,17));
        lblEnrollmentNumber.setBounds(80,170,150,40);
        c.add(lblEnrollmentNumber);
        txtEnrollmentNumber = new JTextField();
        txtEnrollmentNumber.setBounds(200,170,200,40);
        c.add(txtEnrollmentNumber);
        lblSemester = new JLabel("Semester : ");
        lblSemester.setFont(new Font("Arial",Font.PLAIN,17));
        lblSemester.setBounds(80,240,150,40);
        c.add(lblSemester);
        txtSemester = new JTextField();
        txtSemester.setBounds(200,240,200,40);
        c.add(txtSemester);
        lblRollNumber = new JLabel("Roll No. : ");
        lblRollNumber.setFont(new Font("Arial",Font.PLAIN,17));
        lblRollNumber.setBounds(80,310,150,40);
        c.add(lblRollNumber);
        txtRollNumber = new JTextField();
        txtRollNumber.setBounds(200,310,200,40);
        c.add(txtRollNumber);
        submit = new JButton("Submit");
        submit.setFont(new Font("Arial",Font.BOLD,17));
        submit.setBounds(70,450,150,40);
        c.add(submit);
        update = new JButton("Update");
        update.setFont(new Font("Arial",Font.BOLD,17));
        update.setBounds(260,450,150,40);
        c.add(update);
        reset = new JButton("Reset Data");
        reset.setFont(new Font("Arial",Font.BOLD,17));
        reset.setBounds(140,510,200,40);
        c.add(reset);

        submit.addActionListener(e -> {

            if(txtProgramId.getText().equals("") || txtEnrollmentNumber.getText().equals("") || txtRollNumber.getText().equals("") || txtSemester.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Some fields are empty!");
                return;
            }

            String programId = txtProgramId.getText();
            String enrollmentNumber = txtEnrollmentNumber.getText();
            int semester = Integer.parseInt(txtSemester.getText());
            int rollNo = Integer.parseInt(txtRollNumber.getText());
            String acdSession=null,departmentCode=null;
            boolean flag = true;

            try{
                PreparedStatement stmt3 = Main.con.prepareStatement("select * from result where enroll_no=? and semester=? and roll_no=?");
                stmt3.setString(1,enrollmentNumber);
                stmt3.setInt(2,semester);
                stmt3.setInt(3,rollNo);
                ResultSet rs3 = stmt3.executeQuery();
                if(rs3.next()){
                    JOptionPane.showMessageDialog(null, "Result Already Exists!");
                    return;
                }
            }catch (SQLException sqe){
                sqe.printStackTrace();
            }

            Vector<String> courseNumber = new Vector<>();
            Vector<Integer> subjectMarks = new Vector<>();
            Vector<Integer> theoryMarks = new Vector<>();
            Vector<Integer> practicalMarks = new Vector<>();
            Vector<Integer> mtMarks = new Vector<>();
            Vector<Double> gradePoints = new Vector<>();
            Vector<Double> creditPoints = new Vector<>();
            Vector<Integer> creditTh = new Vector<>();
            Vector<Integer> creditPr = new Vector<>();

            try{
                PreparedStatement stmt = Main.con.prepareStatement("select * from marks where enroll_no = ? and semester = ?");
                stmt.setString(1,enrollmentNumber);
                stmt.setInt(2,semester);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    if(flag){
                        acdSession = rs.getString(6);
                        departmentCode = rs.getString(5);
                        flag = false;
                    }
                    courseNumber.add(rs.getString(2));
                    theoryMarks.add(rs.getInt(7));
                    practicalMarks.add(rs.getInt(8));
                    mtMarks.add(rs.getInt(9));
                    subjectMarks.add(rs.getInt(10));
                    creditPoints.add(rs.getDouble(11));
                    gradePoints.add(rs.getDouble(12));
                }

                PreparedStatement stmt1 = Main.con.prepareStatement("select * from schemeCourse where dept_code = ? and semester = ?");
                stmt1.setString(1,departmentCode);
                stmt1.setInt(2,semester);
                ResultSet rs1 = stmt1.executeQuery();
                while(rs1.next()){
                    creditTh.add(rs1.getInt(7));
                    creditPr.add(rs1.getInt(8));
                }

                int totalCreditsHours = 0;
                for(int i=0;i<creditTh.size();i++){
                    totalCreditsHours = totalCreditsHours + (creditTh.get(i)+creditPr.get(i));
                }

                double totalCreditPoints = 0;
                for(int i=0;i<creditPoints.size();i++){
                    totalCreditPoints = totalCreditPoints + creditPoints.get(i);
                }

                int idx;
                if(courseNumber.contains("NSS")){
                    idx = courseNumber.indexOf("NSS");
                    totalCreditsHours = totalCreditsHours-creditPr.get(idx)-creditTh.get(idx);
                    totalCreditPoints = totalCreditPoints-creditPoints.get(idx);
                }
                if(courseNumber.contains("NCC")){
                    idx = courseNumber.indexOf("NCC");
                    totalCreditsHours = totalCreditsHours-creditPr.get(idx)-creditTh.get(idx);
                    totalCreditPoints = totalCreditPoints-creditPoints.get(idx);
                }
                if(courseNumber.contains("NSO")){
                    idx = courseNumber.indexOf("NSO");
                    totalCreditsHours = totalCreditsHours-creditPr.get(idx)-creditTh.get(idx);
                    totalCreditPoints = totalCreditPoints-creditPoints.get(idx);
                }
                if(courseNumber.contains("SCOUT")){
                    idx = courseNumber.indexOf("SCOUT");
                    totalCreditsHours = totalCreditsHours-creditPr.get(idx)-creditTh.get(idx);
                    totalCreditPoints = totalCreditPoints-creditPoints.get(idx);
                }

                double sgpa = Double.parseDouble(String.format("%.2f",(totalCreditPoints/totalCreditsHours)));
                totalCreditPoints = Double.parseDouble(String.format("%.1f",(totalCreditPoints)));
                //for checking propose
                System.out.println("Total Credit Points : " + totalCreditPoints);
                System.out.println("Total Credit Hours : " + totalCreditsHours);
                System.out.println("SGPA : " + sgpa);

                if(totalCreditPoints==0.0){
                    JOptionPane.showMessageDialog(null, "Please fill the Marks First of this Student for that Semester!");
                    return;
                }

                //setting sgpa value, total_credit_points_till_current_semester and total_credit_hours_till_current_semester equal to zero;
                //when i will have data , I will update the result table accordingly!

                PreparedStatement stmt2 = Main.con.prepareStatement("insert into result values(?,?,?,?,?,?,?,?,?,?)");
                stmt2.setString(1,programId);
                stmt2.setString(2,enrollmentNumber);
                stmt2.setInt(3,semester);
                stmt2.setInt(4,0);
                stmt2.setDouble(5,sgpa);
                stmt2.setInt(6,rollNo);
                stmt2.setDouble(7,0);
                stmt2.setInt(8,0);
                stmt2.setInt(9,totalCreditsHours);
                stmt2.setDouble(10,totalCreditPoints);

                stmt2.executeUpdate();

                JOptionPane.showMessageDialog(null,"Result inserted Successfully");

            }catch (SQLException sq){
                sq.printStackTrace();
            }catch (NumberFormatException ne){
                JOptionPane.showMessageDialog(null, "Please fill the details First!");
            }
        });

        update.addActionListener(e -> {
            String programId = txtProgramId.getText();
            String enrollmentNumber = txtEnrollmentNumber.getText();
            int semester = Integer.parseInt(txtSemester.getText());
            int rollNo = Integer.parseInt(txtRollNumber.getText());
            String departmentCode=null;
            boolean flag = true;

            Vector<String> courseNumber = new Vector<>();
            Vector<Integer> subjectMarks = new Vector<>();
            Vector<Integer> theoryMarks = new Vector<>();
            Vector<Integer> practicalMarks = new Vector<>();
            Vector<Integer> mtMarks = new Vector<>();
            Vector<Double> gradePoints = new Vector<>();
            Vector<Double> creditPoints = new Vector<>();
            Vector<Integer> creditTh = new Vector<>();
            Vector<Integer> creditPr = new Vector<>();

            try{
                PreparedStatement stmt = Main.con.prepareStatement("select * from marks where enroll_no = ? and semester = ?");
                stmt.setString(1,enrollmentNumber);
                stmt.setInt(2,semester);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    if(flag){
                        departmentCode = rs.getString(5);
                        flag = false;
                    }
                    courseNumber.add(rs.getString(2));
                    theoryMarks.add(rs.getInt(7));
                    practicalMarks.add(rs.getInt(8));
                    mtMarks.add(rs.getInt(9));
                    subjectMarks.add(rs.getInt(10));
                    creditPoints.add(rs.getDouble(11));
                    gradePoints.add(rs.getDouble(12));
                }

                PreparedStatement stmt1 = Main.con.prepareStatement("select * from schemeCourse where dept_code = ? and semester = ?");
                stmt1.setString(1,departmentCode);
                stmt1.setInt(2,semester);
                ResultSet rs1 = stmt1.executeQuery();
                while(rs1.next()){
                    creditTh.add(rs1.getInt(7));
                    creditPr.add(rs1.getInt(8));
                }

                int totalCreditsHours = 0;
                for(int i=0;i<creditTh.size();i++){
                    totalCreditsHours = totalCreditsHours + (creditTh.get(i)+creditPr.get(i));
                }

                double totalCreditPoints = 0;
                for(int i=0;i<creditPoints.size();i++){
                    totalCreditPoints = totalCreditPoints + creditPoints.get(i);
                }

                int idx;
                if(courseNumber.contains("NSS")){
                    idx = courseNumber.indexOf("NSS");
                    totalCreditsHours = totalCreditsHours-creditPr.get(idx)-creditTh.get(idx);
                    totalCreditPoints = totalCreditPoints-creditPoints.get(idx);
                }
                if(courseNumber.contains("NCC")){
                    idx = courseNumber.indexOf("NCC");
                    totalCreditsHours = totalCreditsHours-creditPr.get(idx)-creditTh.get(idx);
                    totalCreditPoints = totalCreditPoints-creditPoints.get(idx);
                }
                if(courseNumber.contains("NSO")){
                    idx = courseNumber.indexOf("NSO");
                    totalCreditsHours = totalCreditsHours-creditPr.get(idx)-creditTh.get(idx);
                    totalCreditPoints = totalCreditPoints-creditPoints.get(idx);
                }
                if(courseNumber.contains("SCOUT")){
                    idx = courseNumber.indexOf("SCOUT");
                    totalCreditsHours = totalCreditsHours-creditPr.get(idx)-creditTh.get(idx);
                    totalCreditPoints = totalCreditPoints-creditPoints.get(idx);
                }

                double sgpa = Double.parseDouble(String.format("%.2f",(totalCreditPoints/totalCreditsHours)));
                totalCreditPoints = Double.parseDouble(String.format("%.1f",(totalCreditPoints)));
                //for checking propose
                System.out.println("Total Credit Points : " + totalCreditPoints);
                System.out.println("Total Credit Hours : " + totalCreditsHours);
                System.out.println("SGPA : " + sgpa);

                if(totalCreditPoints==0.0){
                    JOptionPane.showMessageDialog(null, "Please fill the Marks First of this Student for that Semester!");
                    return;
                }

                //setting sgpa value, total_credit_points_till_current_semester and total_credit_hours_till_current_semester equal to zero;
                //when i will have data , I will update the result table accordingly!

                PreparedStatement stmt2 = Main.con.prepareStatement("update result set ogpa=?,total_credit_points_till_current_semester=?,total_credit_hours_till_current_semester=?,total_credit_hours=?,total_credit_points=? where enroll_no=? and semester=? and roll_no=?");
                stmt2.setDouble(1,sgpa);
                stmt2.setDouble(2,0);
                stmt2.setInt(3,0);
                stmt2.setInt(4,totalCreditsHours);
                stmt2.setDouble(5,totalCreditPoints);
                stmt2.setString(6,enrollmentNumber);
                stmt2.setInt(7,semester);
                stmt2.setInt(8,rollNo);

                stmt2.executeUpdate();

                JOptionPane.showMessageDialog(null,"Result Updated Successfully");

            }catch (SQLException sq){
                sq.printStackTrace();
            }catch (NumberFormatException ne){
                JOptionPane.showMessageDialog(null, "Please fill the details First!");
            }

        });

        reset.addActionListener(e -> {
            txtProgramId.setText(null);
            txtEnrollmentNumber.setText(null);
            txtSemester.setText(null);
            txtRollNumber.setText(null);
        });
    }
}
