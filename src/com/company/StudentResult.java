package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StudentResult extends JFrame {
    JTextField txtEnrollmentNumber,txtSemester;
    JLabel lblEnrollmentNumber,lblSemester,lbl;
    JButton search;
    Container c;

    StudentResult(){
        super("Search Result Form");
        setBounds(200,200,1100,200);
        c = getContentPane();
        c.setLayout(null);
        lbl = new JLabel("Search Your Result : ");
        lbl.setFont(new Font("Arial",Font.BOLD,17));
        lbl.setBounds(10,10,300,40);
        c.add(lbl);
        lblEnrollmentNumber = new JLabel("Enrollment No. : ");
        lblEnrollmentNumber.setFont(new Font("Arial",Font.PLAIN,17));
        lblEnrollmentNumber.setBounds(25,60,150,40);
        c.add(lblEnrollmentNumber);
        txtEnrollmentNumber = new JTextField();
        txtEnrollmentNumber.setBounds(150,60,200,40);
        c.add(txtEnrollmentNumber);
        lblSemester = new JLabel("Semester : ");
        lblSemester.setFont(new Font("Arial",Font.PLAIN,17));
        lblSemester.setBounds(450,60,150,40);
        c.add(lblSemester);
        txtSemester = new JTextField();
        txtSemester.setBounds(535,60,200,40);
        c.add(txtSemester);
        search = new JButton("Search");
        search.setFont(new Font("Arial",Font.BOLD,17));
        search.setBounds(850,60,200,40);
        c.add(search);

        search.addActionListener(e -> {
            String enrollmentNumber = txtEnrollmentNumber.getText();
            int semester = Integer.parseInt(txtSemester.getText());
            String programId = null,acdSession = null,departmentCode = null,studentName = null,fatherName = null,motherName=null;
            int rollNo=0,totalCreditHours=0,totalCreditHoursTillSemester=0,studentClass=(semester+1)/2;
            double sgpa=0.0,ogpa=0.0,totalCreditPoints=0.0,totalCreditPointsTillSemester=0.0;
            boolean flag = true;

            String enrollNo = enrollmentNumber.substring(0,4);
            int admissionYear = Integer.parseInt(enrollNo);

            Vector<String> courseNumber = new Vector<>();
            Vector<String> courseName = new Vector<>();
            Vector<Integer> subjectMarks = new Vector<>();
            Vector<Integer> theoryMarks = new Vector<>();
            Vector<Integer> practicalMarks = new Vector<>();
            Vector<Integer> mtMarks = new Vector<>();
            Vector<Double> gradePoints = new Vector<>();
            Vector<Double> creditPoints = new Vector<>();
            Vector<Integer> creditTh = new Vector<>();
            Vector<Integer> creditPr = new Vector<>();

            try{
                PreparedStatement stmt2 = Main.con.prepareStatement("select * from student where enroll_no = ?");
                stmt2.setString(1,enrollmentNumber);
                ResultSet rs2 = stmt2.executeQuery();
                while(rs2.next()){
                    programId = rs2.getString(1);
                    studentName = rs2.getString(3);
                    motherName = rs2.getString(5);
                    fatherName = rs2.getString(6);
                }

                PreparedStatement stmt = Main.con.prepareStatement("select * from marks where enroll_no = ? and semester = ? order by course_no");
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

                PreparedStatement stmt1 = Main.con.prepareStatement("select * from schemeCourse where dept_code = ? and semester = ? and start_year = ? order by course_no");
                stmt1.setString(1,departmentCode);
                stmt1.setInt(2,semester);
                stmt1.setInt(3,admissionYear);
                ResultSet rs1 = stmt1.executeQuery();
                while(rs1.next()){
                    courseName.add(rs1.getString(3));
                    creditTh.add(rs1.getInt(7));
                    creditPr.add(rs1.getInt(8));
                }

                PreparedStatement stmt3 = Main.con.prepareStatement("select * from result where enroll_no = ? and semester = ?");
                stmt3.setString(1,enrollmentNumber);
                stmt3.setInt(2,semester);
                ResultSet rs3 = stmt3.executeQuery();
                while(rs3.next()){
                    rollNo = rs3.getInt(6);
                    sgpa = rs3.getDouble(5);
                    ogpa = rs3.getDouble(4);
                    totalCreditPoints = rs3.getDouble(10);
                    totalCreditHours = rs3.getInt(9);
                    totalCreditPointsTillSemester = rs3.getDouble(7);
                    totalCreditHoursTillSemester = rs3.getInt(8);
                }

                totalCreditHoursTillSemester = totalCreditHoursTillSemester+totalCreditHours;
                totalCreditPointsTillSemester = totalCreditPointsTillSemester+totalCreditPoints;

                PreparedStatement stmt4 = Main.con.prepareStatement("select semester,total_credit_hours,total_credit_points from result where enroll_no=?");
                stmt4.setString(1,enrollmentNumber);
                ResultSet rs4 = stmt4.executeQuery();
                while(rs4.next()){
                    if(rs4.getInt(1)<semester){
                        totalCreditHoursTillSemester += rs4.getInt(2);
                        totalCreditPointsTillSemester += rs4.getDouble(3);
                    }
                }

                ogpa = Double.parseDouble(String.format("%.2f",(totalCreditPointsTillSemester/totalCreditHoursTillSemester)));

                if(rollNo==0){
                    JOptionPane.showMessageDialog(null, "For this Student, Please Insert the Result First!");
                    return;
                }

                //ResultDesign rd = new ResultDesign(programId,acdSession,departmentCode,studentName,fatherName,motherName,enrollmentNumber,rollNo,semester,studentClass,courseNumber,courseName,subjectMarks,theoryMarks,practicalMarks,mtMarks,gradePoints,creditPoints,creditTh,creditPr,sgpa,totalCreditHours,totalCreditPoints,ogpa,totalCreditHoursTillSemester,totalCreditPointsTillSemester);
                ResultDesign rd = new ResultDesign(programId,acdSession,departmentCode,studentName,fatherName,motherName,enrollmentNumber,rollNo,semester,studentClass,courseNumber,courseName,subjectMarks,theoryMarks,practicalMarks,mtMarks,gradePoints,creditPoints,creditTh,creditPr,sgpa,totalCreditHours,totalCreditPoints,ogpa,totalCreditHoursTillSemester,totalCreditPointsTillSemester);
                rd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                rd.setVisible(true);

            }catch (SQLException s){
                s.printStackTrace();
            }
        });
    }
}
