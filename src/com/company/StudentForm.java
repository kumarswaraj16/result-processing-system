package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Vector;

public class StudentForm extends JFrame {
    JLabel lblSearchStudent,lblProgramId,lblEnrollNo,lblStudentName,lblCurrentSemester,lblMotherName,lblFatherName,lblDepartmentCode,lblAdmissionYear;
    JTextField txtSearchStudent,txtProgramId,txtEnrollNo,txtStudentName,txtCurrentSemester,txtMotherName,txtFatherName,txtDepartmentCode,txtAdmissionYear;
    JButton add,update,show,search,reset,enterMarks,showResult,addResult;
    JTable jTable;
    Container c;

    StudentForm(){
        super("Student Details Form");
        setBounds(0,0,1500,1000);
        c = getContentPane();
        c.setLayout(null);
        lblSearchStudent = new JLabel("Search Student : ");
        lblSearchStudent.setBounds(470,80,200,40);
        lblSearchStudent.setFont(new Font("Arial",Font.BOLD,20));
        c.add(lblSearchStudent);
        txtSearchStudent = new JTextField();
        txtSearchStudent.setBounds(650,80,250,40);
        c.add(txtSearchStudent);
        search = new JButton("Search");
        search.setFont(new Font("Arial",Font.BOLD,17));
        search.setBounds(920,80,100,40);
        c.add(search);
        lblProgramId = new JLabel("Prog. Id : ");
        lblProgramId.setFont(new Font("Arial",Font.PLAIN,15));
        lblProgramId.setBounds(300,200,200,40);
        c.add(lblProgramId);
        lblEnrollNo = new JLabel("Enrollment Number : ");
        lblEnrollNo.setBounds(800,200,200,40);
        lblEnrollNo.setFont(new Font("Arial",Font.PLAIN,15));
        c.add(lblEnrollNo);
        txtProgramId = new JTextField();
        txtProgramId.setBounds(415,200,200,40);
        c.add(txtProgramId);
        txtEnrollNo = new JTextField();
        txtEnrollNo.setBounds(950,200,200,40);
        c.add(txtEnrollNo);
        lblStudentName = new JLabel("Name : ");
        lblStudentName.setFont(new Font("Arial",Font.PLAIN,15));
        lblStudentName.setBounds(300,300,200,40);
        c.add(lblStudentName);
        lblCurrentSemester = new JLabel("Semester : ");
        lblCurrentSemester.setFont(new Font("Arial",Font.PLAIN,15));
        lblCurrentSemester.setBounds(800,300,200,40);
        c.add(lblCurrentSemester);
        txtStudentName = new JTextField();
        txtStudentName.setBounds(415,300,200,40);
        c.add(txtStudentName);
        txtCurrentSemester = new JTextField();
        txtCurrentSemester.setBounds(950,300,200,40);
        c.add(txtCurrentSemester);
        lblMotherName = new JLabel("Mother's Name : ");
        lblMotherName.setFont(new Font("Arial",Font.PLAIN,15));
        lblMotherName.setBounds(300,400,200,40);
        c.add(lblMotherName);
        lblFatherName = new JLabel("Father's Name : ");
        lblFatherName.setFont(new Font("Arial",Font.PLAIN,15));
        lblFatherName.setBounds(800,400,200,40);
        c.add(lblFatherName);
        txtMotherName = new JTextField();
        txtMotherName.setBounds(415,400,200,40);
        c.add(txtMotherName);
        txtFatherName = new JTextField();
        txtFatherName.setBounds(950,400,200,40);
        c.add(txtFatherName);
        lblDepartmentCode = new JLabel("Department : ");
        lblDepartmentCode.setFont(new Font("Arial",Font.PLAIN,15));
        lblDepartmentCode.setBounds(300,500,200,40);
        c.add(lblDepartmentCode);
        lblAdmissionYear = new JLabel("Admission Year : ");
        lblAdmissionYear.setFont(new Font("Arial",Font.PLAIN,15));
        lblAdmissionYear.setBounds(800,500,200,40);
        c.add(lblAdmissionYear);
        txtDepartmentCode = new JTextField();
        txtDepartmentCode.setBounds(415,500,200,40);
        c.add(txtDepartmentCode);
        txtAdmissionYear = new JTextField();
        txtAdmissionYear.setBounds(950,500,200,40);
        c.add(txtAdmissionYear);
        add = new JButton("ADD");
        add.setFont(new Font("Arial",Font.BOLD,17));
        add.setBounds(350,650,200,40);
        c.add(add);
        update = new JButton("UPDATE");
        update.setFont(new Font("Arial",Font.BOLD,17));
        update.setBounds(650,650,200,40);
        c.add(update);
        show = new JButton("SHOW");
        show.setFont(new Font("Arial",Font.BOLD,17));
        show.setBounds(950,650,200,40);
        c.add(show);
        enterMarks = new JButton("Enter/Update Marks  ➡");
        enterMarks.setFont(new Font("Arial",Font.BOLD,17));
        enterMarks.setBounds(1170,720,250,40);
        c.add(enterMarks);
        reset = new JButton("Reset Details");
        reset.setFont(new Font("Arial",Font.BOLD,17));
        reset.setBounds(15,720,200,40);
        c.add(reset);

        showResult = new JButton("Search Result");
        showResult.setFont(new Font("Arial",Font.BOLD,17));
        showResult.setBounds(700,720,300,40);
        c.add(showResult);
        addResult = new JButton("Add/Update Result");
        addResult.setFont(new Font("Arial",Font.BOLD,17));
        addResult.setBounds(400,720,300,40);
        c.add(addResult);

        addResult.addActionListener(e -> {
            ResultForm rf = new ResultForm();
            rf.setVisible(true);
            rf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        });

        showResult.addActionListener(e -> {
            StudentResult sr = new StudentResult();
            sr.setVisible(true);
            sr.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        });

        reset.addActionListener(e -> {
            txtProgramId.setText(null);
            txtEnrollNo.setText(null);
            txtStudentName.setText(null);
            txtCurrentSemester.setText(null);
            txtMotherName.setText(null);
            txtFatherName.setText(null);
            txtDepartmentCode.setText(null);
            txtAdmissionYear.setText(null);
        });

        enterMarks.addActionListener(e -> {
            try{
                Vector<Integer> thv = new Vector<>();
                Vector<Integer> prv = new Vector<>();
                Vector<Integer> mtv = new Vector<>();
                String programId = txtProgramId.getText();
                String enrollmentNumber = txtEnrollNo.getText();
                String departmentCode = txtDepartmentCode.getText();
                int semester = Integer.parseInt(txtCurrentSemester.getText());
                int admissionYear = Integer.parseInt(txtAdmissionYear.getText());

                PreparedStatement stmt = Main.con.prepareStatement("select * from marks where enroll_no=? and semester=? and dept_code=?");
                stmt.setString(1,enrollmentNumber);
                stmt.setInt(2,semester);
                stmt.setString(3,departmentCode);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                    int theory,practical,mt;
                    theory = rs.getInt(7);
                    practical = rs.getInt(8);
                    mt = rs.getInt(9);
                    thv.add(theory);
                    prv.add(practical);
                    mtv.add(mt);
                }

                MarksForm mf = new MarksForm(programId,enrollmentNumber,departmentCode,semester,admissionYear,thv,prv,mtv);
                mf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                mf.setBounds(0,0,1500,1000);
                mf.setVisible(true);

            }catch (NumberFormatException ne){
                JOptionPane.showMessageDialog(null, "To Enter Marks first fill the student's form!");
            }catch (SQLException se){
                se.printStackTrace();
            }
        });

        add.addActionListener(e -> {
            String programId = txtProgramId.getText();
            String enrollNo = txtEnrollNo.getText();
            String studentName = txtStudentName.getText();
            int currentSemester = Integer.parseInt(txtCurrentSemester.getText());
            String motherName = txtMotherName.getText();
            String fatherName = txtFatherName.getText();
            String departmentCode = txtDepartmentCode.getText();
            int admissionYear = Integer.parseInt(txtAdmissionYear.getText());

            try{
                PreparedStatement stmt = Main.con.prepareStatement("insert into student values(?,?,?,?,?,?,?,?,?)");
                stmt.setString(1,programId);
                stmt.setString(2,enrollNo);
                stmt.setString(3,studentName);
                stmt.setInt(4,currentSemester);
                stmt.setString(5,motherName);
                stmt.setString(6,fatherName);
                stmt.setString(7,departmentCode);
                stmt.setInt(8,admissionYear);
                stmt.setBoolean(9,true);

                stmt.executeUpdate();

                JOptionPane.showMessageDialog(null,"Data inserted Successfully");

            }catch (SQLException e1){
                e1.printStackTrace();
            }

        });

        update.addActionListener(e -> {
            try{
                PreparedStatement stmt = Main.con.prepareStatement("update student set prog_id = ? , enroll_no = ? , student_name = ? , curr_semester = ? , mother_name = ? , father_name = ? , dept_code = ? , start_year = ? , isActive = ? where enroll_no = ?");
                stmt.setString(1,txtProgramId.getText());
                stmt.setString(2,txtEnrollNo.getText());
                stmt.setString(3,txtStudentName.getText());
                stmt.setInt(4,Integer.parseInt(txtCurrentSemester.getText()));
                stmt.setString(5,txtMotherName.getText());
                stmt.setString(6,txtFatherName.getText());
                stmt.setString(7,txtDepartmentCode.getText());
                stmt.setInt(8,Integer.parseInt(txtAdmissionYear.getText()));

                int admissionYear = Integer.parseInt(txtAdmissionYear.getText());
                int currentYear = Year.now().getValue();
                stmt.setBoolean(9, (admissionYear + 4) >= currentYear);

                stmt.setString(10,txtEnrollNo.getText());

                stmt.executeUpdate();
            }catch (NumberFormatException ne) {
                JOptionPane.showMessageDialog(null, "To update the data first open the Table!");
                return;
            }catch(SQLException e3){
                e3.printStackTrace();
            }

            JOptionPane.showMessageDialog(null,"Data Updated Successfully");
            jTable = getTable();
            new StudentTable(jTable,"Student Table");

            txtProgramId.setText(null);
            txtEnrollNo.setText(null);
            txtStudentName.setText(null);
            txtCurrentSemester.setText(null);
            txtMotherName.setText(null);
            txtFatherName.setText(null);
            txtDepartmentCode.setText(null);
            txtAdmissionYear.setText(null);
        });

        show.addActionListener(e -> {
            jTable = getTable();
            new StudentTable(jTable,"Student Table");
            jTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row_index = jTable.getSelectedRow();
                    txtProgramId.setText(jTable.getValueAt(row_index,0).toString());
                    txtEnrollNo.setText(jTable.getValueAt(row_index,1).toString());
                    txtStudentName.setText(jTable.getValueAt(row_index,2).toString());
                    txtCurrentSemester.setText(jTable.getValueAt(row_index,3).toString());
                    txtMotherName.setText(jTable.getValueAt(row_index,4).toString());
                    txtFatherName.setText(jTable.getValueAt(row_index,5).toString());
                    txtDepartmentCode.setText(jTable.getValueAt(row_index,6).toString());
                    txtAdmissionYear.setText(jTable.getValueAt(row_index,7).toString());
                }
            });
        });

        search.addActionListener(e -> {
            String enrollmentNumber = txtSearchStudent.getText();
            jTable = getSearchedTable(enrollmentNumber);
            new StudentTable(jTable,"Searched Student");
            txtSearchStudent.setText(null);
            jTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row_index = jTable.getSelectedRow();

                    txtProgramId.setText(jTable.getValueAt(row_index,0).toString());
                    txtEnrollNo.setText(jTable.getValueAt(row_index,1).toString());
                    txtStudentName.setText(jTable.getValueAt(row_index,2).toString());
                    txtCurrentSemester.setText(jTable.getValueAt(row_index,3).toString());
                    txtMotherName.setText(jTable.getValueAt(row_index,4).toString());
                    txtFatherName.setText(jTable.getValueAt(row_index,5).toString());
                    txtDepartmentCode.setText(jTable.getValueAt(row_index,6).toString());
                    txtAdmissionYear.setText(jTable.getValueAt(row_index,7).toString());
                        if(e.getSource()==update){
                            try{
                                PreparedStatement stmt = Main.con.prepareStatement("update student set prog_id = ? , enroll_no = ? , student_name = ? , curr_semester = ? , mother_name = ? , father_name = ? , dept_code = ? , start_year = ? , isActive = ? where enroll_no = ?");
                                stmt.setString(1,txtProgramId.getText());
                                stmt.setString(2,txtEnrollNo.getText());
                                stmt.setString(3,txtStudentName.getText());
                                stmt.setInt(4,Integer.parseInt(txtCurrentSemester.getText()));
                                stmt.setString(5,txtMotherName.getText());
                                stmt.setString(6,txtFatherName.getText());
                                stmt.setString(7,txtDepartmentCode.getText());
                                stmt.setInt(8,Integer.parseInt(txtAdmissionYear.getText()));

                                int admissionYear = Integer.parseInt(txtAdmissionYear.getText());
                                int currentYear = Year.now().getValue();
                                stmt.setBoolean(9, (admissionYear + 4) >= currentYear);

                                stmt.executeUpdate();
                            }catch (NumberFormatException ne){
                                JOptionPane.showMessageDialog(null,"To update the data first open the Table!");
                                return;
                            }catch (SQLException e1){
                                e1.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(null,"Data Updated Successfully");
                            jTable = getTable();
                            new StudentTable(jTable,"Student Table");
                            txtProgramId.setText(null);
                            txtEnrollNo.setText(null);
                            txtStudentName.setText(null);
                            txtCurrentSemester.setText(null);
                            txtMotherName.setText(null);
                            txtFatherName.setText(null);
                            txtDepartmentCode.setText(null);
                            txtAdmissionYear.setText(null);
                        }
                }
            });
        });
    }

    ArrayList<StudentDetails> getStudents(){
        ArrayList<StudentDetails> studentData = new ArrayList<>();
        try{
            PreparedStatement stmt = Main.con.prepareStatement("select * from student");
            ResultSet rs = stmt.executeQuery();
            StudentDetails studentDetails;
            while(rs.next()){
                String programId = rs.getString(1);
                String enrollNo = rs.getString(2);
                String studentName = rs.getString(3);
                int currentSemester = rs.getInt(4);
                String motherName = rs.getString(5);
                String fatherName = rs.getString(6);
                String departmentCode = rs.getString(7);
                int admissionYear = rs.getInt(8);

                studentDetails = new StudentDetails(programId,enrollNo,studentName,currentSemester,motherName,fatherName,departmentCode,admissionYear);

                studentData.add(studentDetails);
            }
        }catch(SQLException e2){
            e2.printStackTrace();
        }
        return studentData;
    }

    JTable getTable(){
        Vector<Object> columnData = new Vector<>();
        columnData.add("Program Id");
        columnData.add("Enrollment No.");
        columnData.add("Name");
        columnData.add("Semester");
        columnData.add("Mother Name");
        columnData.add("Father Name");
        columnData.add("Department");
        columnData.add("Admission Year");
        columnData.add("Active");

        Vector<Vector<Object>> data = new Vector<>();
        ArrayList<StudentDetails> list = getStudents();

        for(StudentDetails studentDetails : list){
            Vector<Object> row = new Vector<>();
            row.add(studentDetails.getProgramId());
            row.add(studentDetails.getEnrollNo());
            row.add(studentDetails.getStudentName());
            row.add(studentDetails.getCurrentSemester());
            row.add(studentDetails.getMotherName());
            row.add(studentDetails.getFatherName());
            row.add(studentDetails.getDepartmentCode());
            row.add(studentDetails.getAdmissionYear());
            row.add(true);
            data.add(row);
        }

        jTable = new JTable(data,columnData);
        return jTable;
    }

    ArrayList<StudentDetails> getSearchedStudents(String search_enrollment_number){
        ArrayList<StudentDetails> studentData = new ArrayList<>();
        try{
            PreparedStatement stmt = Main.con.prepareStatement("select * from student where enroll_no = ?");
            stmt.setString(1,search_enrollment_number);
            ResultSet rs = stmt.executeQuery();
            StudentDetails studentDetails;
            while(rs.next()){
                String programId = rs.getString(1);
                String enrollNo = rs.getString(2);
                String studentName = rs.getString(3);
                int currentSemester = rs.getInt(4);
                String motherName = rs.getString(5);
                String fatherName = rs.getString(6);
                String departmentCode = rs.getString(7);
                int admissionYear = rs.getInt(8);
                studentDetails = new StudentDetails(programId,enrollNo,studentName,currentSemester,motherName,fatherName,departmentCode,admissionYear);
                studentData.add(studentDetails);
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return studentData;
    }

    JTable getSearchedTable(String enrollmentNumber){
        Vector<Object> columnData = new Vector<>();
        columnData.add("Program Id");
        columnData.add("Enrollment No.");
        columnData.add("Name");
        columnData.add("Semester");
        columnData.add("Mother Name");
        columnData.add("Father Name");
        columnData.add("Department");
        columnData.add("Admission Year");
        columnData.add("Active");
        Vector<Vector<Object>> data = new Vector<>();
        ArrayList<StudentDetails> list = getSearchedStudents(enrollmentNumber);
        for (StudentDetails studentDetails : list) {
            Vector<Object> row = new Vector<>();
            row.add(studentDetails.getProgramId());
            row.add(studentDetails.getEnrollNo());
            row.add(studentDetails.getStudentName());
            row.add(studentDetails.getCurrentSemester());
            row.add(studentDetails.getMotherName());
            row.add(studentDetails.getFatherName());
            row.add(studentDetails.getDepartmentCode());
            row.add(studentDetails.getAdmissionYear());
            row.add(true);
            data.add(row);
        }
        jTable = new JTable(data,columnData);
        return jTable;
    }

}
