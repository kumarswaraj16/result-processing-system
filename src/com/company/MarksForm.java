package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class MarksForm extends JFrame {
    String programId,enrollmentNumber,departmentCode;
    int semester,admissionYear;
    Vector<Integer> thv;
    Vector<Integer> prv;
    Vector<Integer> mtv;
    Container c;
    JButton submit,update;
    MarksForm(String programId,String enrollmentNumber,String departmentCode,int semester,int admissionYear,Vector<Integer> thv,Vector<Integer> prv,Vector<Integer> mtv){
        super("Student Marks' Form");
        this.programId = programId;
        this.enrollmentNumber = enrollmentNumber;
        this.departmentCode = departmentCode;
        this.semester = semester;
        this.admissionYear = admissionYear;
        this.thv = thv;
        this.prv = prv;
        this.mtv = mtv;
        c = getContentPane();
        c.setLayout(null);
        Vector<String> courseNumber = new Vector<>();
        Vector<String> courseName = new Vector<>();
        Vector<JTextField> theoryMarks = new Vector<>();
        Vector<JTextField> practicalMarks = new Vector<>();
        Vector<JTextField> midtermMarks = new Vector<>();
        Vector<Integer> creditTh = new Vector<>();
        Vector<Integer> creditPr = new Vector<>();
        Vector<Integer> maxMT = new Vector<>();
        try {
            PreparedStatement stmt = Main.con.prepareStatement("select * from schemeCourse where schemeCourse.semester = ? and schemeCourse.dept_code = ?");
            stmt.setInt(1,semester);
            stmt.setString(2,departmentCode);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                courseNumber.add(rs.getString(2));
                courseName.add(rs.getString(3));
                creditTh.add(rs.getInt(7));
                creditPr.add(rs.getInt(8));
                maxMT.add(rs.getInt(9));
            }
            int lblX=200,lblY=80,txtX=370,txtY=120,i=0,thX=300,thY=120;
            JTextField txtTheoryMarks,txtPracticalMarks,txtMidTermMarks;
            JLabel lblCourseDetails,lblTh,lblPr,lblMt;
            for(String cn:courseName){
                lblCourseDetails = new JLabel(cn + " (" + courseNumber.get(i) + ") : ");
                lblCourseDetails.setFont(new Font("Arial",Font.BOLD,15));
                lblCourseDetails.setBounds(lblX,lblY,500,40);
                c.add(lblCourseDetails);
                lblTh = new JLabel("Theory : ");
                lblTh.setFont(new Font("Arial",Font.PLAIN,15));
                lblTh.setBounds(thX,thY,100,40);
                c.add(lblTh);
                txtTheoryMarks = new JTextField();
                txtTheoryMarks.setBounds(txtX,txtY,200,40);
                if(creditTh.get(i)==0){
                    txtTheoryMarks.setText("0");
                    txtTheoryMarks.setEnabled(false);
                }
                theoryMarks.add(txtTheoryMarks);
                c.add(txtTheoryMarks);
                lblPr = new JLabel("Practical : ");
                lblPr.setFont(new Font("Arial",Font.PLAIN,15));
                lblPr.setBounds(thX+400,thY,100,40);
                c.add(lblPr);
                txtPracticalMarks = new JTextField();
                txtPracticalMarks.setBounds(txtX+400,txtY,200,40);
                if(creditPr.get(i)==0){
                    txtPracticalMarks.setText("0");
                    txtPracticalMarks.setEnabled(false);
                }
                practicalMarks.add(txtPracticalMarks);
                c.add(txtPracticalMarks);
                lblMt = new JLabel("MT : ");
                lblMt.setFont(new Font("Arial",Font.PLAIN,15));
                lblMt.setBounds(thX+800,thY,100,40);
                c.add(lblMt);
                txtMidTermMarks = new JTextField();
                txtMidTermMarks.setBounds(txtX+770,txtY,200,40);
                if(maxMT.get(i)==0){
                    txtMidTermMarks.setText("0");
                    txtMidTermMarks.setEnabled(false);
                }
                midtermMarks.add(txtMidTermMarks);
                c.add(txtMidTermMarks);
                txtY=txtY+80;
                lblY=lblY+80;
                thY=thY+80;
                i++;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        submit = new JButton("SUBMIT MARKS");
        submit.setFont(new Font("Arial",Font.BOLD,15));
        submit.setBounds(1240,10,200,40);
        c.add(submit);

        update = new JButton("UPDATE");
        update.setFont(new Font("Arial",Font.BOLD,15));
        update.setBounds(10,10,200,40);
        c.add(update);

        if(thv.size()>0 && prv.size()>0 && mtv.size()>0){
            int idx1=0,idx2=0,idx3=0;
            for(int marks:thv){
                theoryMarks.get(idx1).setText(String.valueOf(marks));
                idx1++;
            }
            for(int marks:prv){
                practicalMarks.get(idx2).setText(String.valueOf(marks));
                idx2++;
            }
            for(int marks:mtv){
                midtermMarks.get(idx3).setText(String.valueOf(marks));
                idx3++;
            }
            submit.setEnabled(false);
        }else{
            update.setEnabled(false);
        }

        submit.addActionListener(e -> {
            int temp = (semester+1)/2;
            String acd_session = (admissionYear + temp - 1) +"-"+ (admissionYear + temp);
            int totalMarks;
            Vector<Integer> subjectMarks = new Vector<>();
            Vector<Double> gradePoints = new Vector<>();
            Vector<Double> creditPoints = new Vector<>();
            int n = courseName.size();
            for(int i=0;i<n;i++){
                int theory,practical,mt;
                if(creditTh.get(i)!=0){
                    theory = Integer.parseInt(theoryMarks.get(i).getText());
                }else{
                    theory = 0;
                }
                if(creditPr.get(i)!=0){
                    practical = Integer.parseInt(practicalMarks.get(i).getText());
                }else{
                    practical = 0;
                }
                if(maxMT.get(i)!=0){
                    mt = Integer.parseInt(midtermMarks.get(i).getText());
                }else{
                    mt = 0;
                }
                totalMarks = theory+practical+mt;
                subjectMarks.add(totalMarks);
                double grade = (totalMarks/10.0);
                double credit = Double.parseDouble(String.format("%.1f",grade*(creditTh.get(i)+creditPr.get(i))));
                gradePoints.add((totalMarks/10.0));
                creditPoints.add(credit);
            }
            int i=0,j=courseNumber.size();
            for(String cn:courseNumber){
                try{
                    PreparedStatement stmt = Main.con.prepareStatement("insert into marks values(?,?,?,?,?,?,?,?,?,?,?,?)");
                    stmt.setString(1,programId);
                    stmt.setString(2,cn);
                    stmt.setString(3,enrollmentNumber);
                    stmt.setInt(4,semester);
                    stmt.setString(5,departmentCode);
                    stmt.setString(6,acd_session);
                    stmt.setInt(7,Integer.parseInt(theoryMarks.get(i).getText()));
                    stmt.setInt(8,Integer.parseInt(practicalMarks.get(i).getText()));
                    stmt.setInt(9,Integer.parseInt(midtermMarks.get(i).getText()));
                    stmt.setInt(10,subjectMarks.get(i));
                    stmt.setDouble(11,creditPoints.get(i));
                    stmt.setDouble(12,gradePoints.get(i));

                    stmt.executeUpdate();
                }catch (SQLException e2){
                    e2.printStackTrace();
                }
                i++;
            }
            if(j==i){
                JOptionPane.showMessageDialog(null,"Marks inserted Successfully");
                int sz=theoryMarks.size();
                for(int ele=0;ele<sz;ele++){
                    theoryMarks.get(ele).setText(null);
                    practicalMarks.get(ele).setText(null);
                    midtermMarks.get(ele).setText(null);
                }
            }
        });

        update.addActionListener(e -> {
            int totalMarks;
            Vector<Integer> subjectMarks = new Vector<>();
            Vector<Double> gradePoints = new Vector<>();
            Vector<Double> creditPoints = new Vector<>();
            int n = courseName.size();
            for(int i=0;i<n;i++) {
                int theory, practical, mt;
                if (creditTh.get(i) != 0) {
                    theory = Integer.parseInt(theoryMarks.get(i).getText());
                } else {
                    theory = 0;
                }
                if (creditPr.get(i) != 0) {
                    practical = Integer.parseInt(practicalMarks.get(i).getText());
                } else {
                    practical = 0;
                }
                if (maxMT.get(i) != 0) {
                    mt = Integer.parseInt(midtermMarks.get(i).getText());
                } else {
                    mt = 0;
                }
                totalMarks = theory + practical + mt;
                subjectMarks.add(totalMarks);
                double grade = (totalMarks / 10.0);
                double credit = Double.parseDouble(String.format("%.1f", grade * (creditTh.get(i) + creditPr.get(i))));
                gradePoints.add((totalMarks / 10.0));
                creditPoints.add(credit);
            }
            int i=0,j=courseNumber.size();
            try{
                for(String cn:courseNumber){
                    PreparedStatement stmt = Main.con.prepareStatement("update marks set marks_theory=?,marks_prac=?,marks_mt=?,total_marks=?,credit_points=?,grade_point=? where course_no=? and enroll_no=? and semester=? and dept_code=?");
                    stmt.setInt(1,Integer.parseInt(theoryMarks.get(i).getText()));
                    stmt.setInt(2,Integer.parseInt(practicalMarks.get(i).getText()));
                    stmt.setInt(3,Integer.parseInt(midtermMarks.get(i).getText()));
                    stmt.setInt(4,subjectMarks.get(i));
                    stmt.setDouble(5,creditPoints.get(i));
                    stmt.setDouble(6,gradePoints.get(i));
                    stmt.setString(7,cn);
                    stmt.setString(8,enrollmentNumber);
                    stmt.setInt(9,semester);
                    stmt.setString(10,departmentCode);

                    stmt.executeUpdate();

                    i++;
                }
            }catch (SQLException e3){
                e3.printStackTrace();
            }
            if(j==i){
                JOptionPane.showMessageDialog(null,"Marks Updated Successfully");
                int sz=theoryMarks.size();
                for(int ele=0;ele<sz;ele++){
                    theoryMarks.get(ele).setText(null);
                    practicalMarks.get(ele).setText(null);
                    midtermMarks.get(ele).setText(null);
                }
            }
        });
    }
}
