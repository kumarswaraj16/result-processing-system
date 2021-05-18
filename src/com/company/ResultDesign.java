package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class ResultDesign extends JFrame {
    String programId,acdSession,departmentCode,studentName,fatherName,motherName,enrollmentNumber;
    int rollNo,semester,studentClass,totalCreditHours,totalCreditHoursTillSemester;
    double sgpa,ogpa,totalCreditPoints,totalCreditPointsTillSemester;
    JLabel lblCollegeName,lblProgramId,lblDepartment,lblRoll,lblName,lblFN,lblMN,lblEN,lblClass,lblSemester,lblSession;
    JLabel lblRollValue,lblNameValue,lblFNValue,lblMNValue,lblENValue,lblClassValue,lblSemesterValue,lblSessionValue;
    JLabel lblCourseNumber,lblCourseName,lblCreditHours,lblTh,lblPr,lblMarksObtained,lblThm,lblPrm,lblMtm,lblTm,lblGradePoint,lblCreditPoints;
    Vector<String> courseNumber;
    Vector<String> courseName;
    Vector<Integer> subjectMarks;
    Vector<Integer> theoryMarks;
    Vector<Integer> practicalMarks;
    Vector<Integer> mtMarks;
    Vector<Double> gradePoints;
    Vector<Double> creditPoints;
    Vector<Integer> creditTh;
    Vector<Integer> creditPr;
    Container c;

    ResultDesign(String programId,String acdSession,String departmentCode,String studentName,String fatherName,String motherName,String enrollmentNumber,int rollNo,int semester,int studentClass,Vector<String> courseNumber,Vector<String> courseName,Vector<Integer> subjectMarks,Vector<Integer> theoryMarks,Vector<Integer> practicalMarks,Vector<Integer> mtMarks,Vector<Double> gradePoints,Vector<Double> creditPoints,Vector<Integer> creditTh,Vector<Integer> creditPr,double sgpa,int totalCreditHours,double totalCreditPoints,double ogpa,int totalCreditHoursTillSemester,double totalCreditPointsTillSemester){
        super("Student Result");
        this.programId = programId;
        this.acdSession = acdSession;
        this.departmentCode = departmentCode;
        this.studentName = studentName;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.enrollmentNumber = enrollmentNumber;
        this.rollNo = rollNo;
        this.semester = semester;
        this.studentClass = studentClass;
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.subjectMarks = subjectMarks;
        this.theoryMarks = theoryMarks;
        this.practicalMarks = practicalMarks;
        this.mtMarks = mtMarks;
        this.gradePoints = gradePoints;
        this.creditPoints = creditPoints;
        this.creditTh = creditTh;
        this.creditPr = creditPr;
        this.sgpa = sgpa;
        this.totalCreditHours = totalCreditHours;
        this.totalCreditPoints = totalCreditPoints;
        this.ogpa = ogpa;
        this.totalCreditHoursTillSemester = totalCreditHoursTillSemester;
        this.totalCreditPointsTillSemester = totalCreditPointsTillSemester;

        setBounds(0,0,1500,1000);
        c = getContentPane();
        c.setLayout(null);

        lblCollegeName = new JLabel("COLLEGE OF TECHNOLOGY AND ENGINEERING UDAIPUR");
        lblCollegeName.setFont(new Font("Arial",Font.BOLD,30));
        lblCollegeName.setBounds(300,20,900,40);
        c.add(lblCollegeName);

        lblProgramId = new JLabel("BACHELOR OF TECHNOLOGY ");
        lblProgramId.setFont(new Font("Arial",Font.PLAIN,17));
        lblProgramId.setBounds(450,60,400,40);
        c.add(lblProgramId);

        lblDepartment = new JLabel("(" + "COMPUTER SCIENCE & ENGINEERING" + ")");
        lblDepartment.setFont(new Font("Arial",Font.PLAIN,17));
        lblDepartment.setBounds(700,60,400,40);
        c.add(lblDepartment);

        lblRoll = new JLabel("Roll No. : ");
        lblRoll.setFont(new Font("Arial",Font.PLAIN,15));
        lblRoll.setBounds(350,125,150,20);
        c.add(lblRoll);
        lblRollValue = new JLabel(String.valueOf(rollNo));
        lblRollValue.setFont(new Font("Arial",Font.PLAIN,15));
        lblRollValue.setBounds(500,125,150,20);
        c.add(lblRollValue);

        lblName = new JLabel("Name : ");
        lblName.setFont(new Font("Arial",Font.PLAIN,15));
        lblName.setBounds(350,150,150,20);
        c.add(lblName);
        lblNameValue = new JLabel(studentName);
        lblNameValue.setFont(new Font("Arial",Font.PLAIN,15));
        lblNameValue.setBounds(500,150,500,20);
        c.add(lblNameValue);

        lblFN = new JLabel("Father's Name : ");
        lblFN.setFont(new Font("Arial",Font.PLAIN,15));
        lblFN.setBounds(350,175,150,20);
        c.add(lblFN);
        lblFNValue = new JLabel(fatherName);
        lblFNValue.setFont(new Font("Arial",Font.PLAIN,15));
        lblFNValue.setBounds(500,175,500,20);
        c.add(lblFNValue);

        lblMN = new JLabel("Mother's Name : ");
        lblMN.setFont(new Font("Arial",Font.PLAIN,15));
        lblMN.setBounds(350,200,150,20);
        c.add(lblMN);
        lblMNValue = new JLabel(motherName);
        lblMNValue.setFont(new Font("Arial",Font.PLAIN,15));
        lblMNValue.setBounds(500,200,500,20);
        c.add(lblMNValue);

        lblEN = new JLabel("Enrollment No. : ");
        lblEN.setFont(new Font("Arial",Font.PLAIN,15));
        lblEN.setBounds(850,125,150,20);
        c.add(lblEN);
        lblENValue = new JLabel(enrollmentNumber);
        lblENValue.setFont(new Font("Arial",Font.PLAIN,15));
        lblENValue.setBounds(1050,125,150,20);
        c.add(lblENValue);

        lblClass = new JLabel("Class : ");
        lblClass.setFont(new Font("Arial",Font.PLAIN,15));
        lblClass.setBounds(850,150,150,20);
        c.add(lblClass);
        if(studentClass==1){
            lblClassValue = new JLabel("FIRST YEAR");
        }else if(studentClass==2){
            lblClassValue = new JLabel("SECOND YEAR");
        }else if(studentClass==3){
            lblClassValue = new JLabel("THIRD YEAR");
        }else if(studentClass==4){
            lblClassValue = new JLabel("FOURTH YEAR");
        }
        lblClassValue.setFont(new Font("Arial",Font.PLAIN,15));
        lblClassValue.setBounds(1050,150,150,20);
        c.add(lblClassValue);

        lblSemester = new JLabel("Semester : ");
        lblSemester.setFont(new Font("Arial",Font.PLAIN,15));
        lblSemester.setBounds(850,175,150,20);
        c.add(lblSemester);
        if(semester%2==0){
            lblSemesterValue = new JLabel("SECOND");
        }else{
            lblSemesterValue = new JLabel("FIRST");
        }
        lblSemesterValue.setFont(new Font("Arial",Font.PLAIN,15));
        lblSemesterValue.setBounds(1050,175,150,20);
        c.add(lblSemesterValue);

        lblSession = new JLabel("Session : ");
        lblSession.setFont(new Font("Arial",Font.PLAIN,15));
        lblSession.setBounds(850,200,150,20);
        c.add(lblSession);
        lblSessionValue = new JLabel(acdSession);
        lblSessionValue.setFont(new Font("Arial",Font.PLAIN,15));
        lblSessionValue.setBounds(1050,200,150,20);
        c.add(lblSessionValue);

        lblCourseNumber = new JLabel("Course No.");
        lblCourseNumber.setFont(new Font("Arial",Font.BOLD,17));
        lblCourseNumber.setBounds(10,250,150,40);
        c.add(lblCourseNumber);

        JLabel cn;
        int lblX=15,lblY=300;
        for(String ele:courseNumber){
            cn = new JLabel(ele);
            cn.setFont(new Font("Arial",Font.PLAIN,17));
            cn.setBounds(lblX,lblY,200,40);
            c.add(cn);
            lblY=lblY+45;
        }

        lblCourseName = new JLabel("Title of the Course");
        lblCourseName.setFont(new Font("Arial",Font.BOLD,17));
        lblCourseName.setBounds(250,250,500,40);
        c.add(lblCourseName);

        int lblX1=175,lblY1=300;
        for(String ele:courseName){
            //System.out.println(ele);
            cn = new JLabel(ele);
            cn.setFont(new Font("Arial",Font.PLAIN,17));
            cn.setBounds(lblX1,lblY1,400,40);
            c.add(cn);
            lblY1=lblY1+45;
        }

        lblCreditHours = new JLabel("Credit Hours");
        lblCreditHours.setFont(new Font("Arial",Font.BOLD,17));
        lblCreditHours.setBounds(600,250,200,40);
        c.add(lblCreditHours);
        lblTh = new JLabel("Th");
        lblTh.setFont(new Font("Arial",Font.BOLD,17));
        lblTh.setBounds(612,275,50,40);
        c.add(lblTh);
        lblPr = new JLabel("Pr");
        lblPr.setFont(new Font("Arial",Font.BOLD,17));
        lblPr.setBounds(675,275,50,40);
        c.add(lblPr);

        int lblX2=615,lblY2=300;
        for(int ele:creditTh){
            cn = new JLabel(String.valueOf(ele));
            cn.setFont(new Font("Arial",Font.PLAIN,17));
            cn.setBounds(lblX2,lblY2,400,40);
            c.add(cn);
            lblY2=lblY2+45;
        }

        int lblX3=678,lblY3=300;
        for(int ele:creditPr){
            cn = new JLabel(String.valueOf(ele));
            cn.setFont(new Font("Arial",Font.PLAIN,17));
            cn.setBounds(lblX3,lblY3,400,40);
            c.add(cn);
            lblY3=lblY3+45;
        }

        lblMarksObtained = new JLabel("Marks Obtained");
        lblMarksObtained.setFont(new Font("Arial",Font.BOLD,17));
        lblMarksObtained.setBounds(840,250,300,40);
        c.add(lblMarksObtained);
        lblThm = new JLabel("Th");
        lblThm.setFont(new Font("Arial",Font.BOLD,17));
        lblThm.setBounds(812,275,50,40);
        c.add(lblThm);
        lblPrm = new JLabel("Pr");
        lblPrm.setFont(new Font("Arial",Font.BOLD,17));
        lblPrm.setBounds(862,275,50,40);
        c.add(lblPrm);
        lblMtm = new JLabel("MT");
        lblMtm.setFont(new Font("Arial",Font.BOLD,17));
        lblMtm.setBounds(912,275,50,40);
        c.add(lblMtm);
        lblTm = new JLabel("Total");
        lblTm.setFont(new Font("Arial",Font.BOLD,17));
        lblTm.setBounds(962,275,50,40);
        c.add(lblTm);

        int lblX4=815,lblY4=300;
        for(int ele:theoryMarks){
            if(ele==0){
                cn = new JLabel("--");
            }else{
                cn = new JLabel(String.valueOf(ele));
            }
            cn.setFont(new Font("Arial",Font.PLAIN,17));
            cn.setBounds(lblX4,lblY4,40,40);
            c.add(cn);
            lblY4=lblY4+45;
        }

        int lblX5=865,lblY5=300;
        for(int ele:practicalMarks){
            if(ele==0){
                cn = new JLabel("--");
            }else{
                cn = new JLabel(String.valueOf(ele));
            }
            cn.setFont(new Font("Arial",Font.PLAIN,17));
            cn.setBounds(lblX5,lblY5,40,40);
            c.add(cn);
            lblY5=lblY5+45;
        }

        int lblX6=915,lblY6=300;
        for(int ele:mtMarks){
            if(ele==0){
                cn = new JLabel("--");
            }else{
                cn = new JLabel(String.valueOf(ele));
            }
            cn.setFont(new Font("Arial",Font.PLAIN,17));
            cn.setBounds(lblX6,lblY6,40,40);
            c.add(cn);
            lblY6=lblY6+45;
        }

        int lblX7=975,lblY7=300;
        for(int ele:subjectMarks){
            if(ele==0){
                cn = new JLabel("--");
            }else{
                cn = new JLabel(String.valueOf(ele));
            }
            cn.setFont(new Font("Arial",Font.PLAIN,17));
            cn.setBounds(lblX7,lblY7,40,40);
            c.add(cn);
            lblY7=lblY7+45;
        }

        lblGradePoint = new JLabel("Grade Point");
        lblGradePoint.setFont(new Font("Arial",Font.BOLD,17));
        lblGradePoint.setBounds(1100,250,300,40);
        c.add(lblGradePoint);

        int lblX8=1135,lblY8=300;
        for(double ele:gradePoints){
            cn = new JLabel(String.valueOf(ele));
            cn.setFont(new Font("Arial",Font.PLAIN,17));
            cn.setBounds(lblX8,lblY8,100,40);
            c.add(cn);
            lblY8=lblY8+45;
        }

        lblCreditPoints = new JLabel("Credit Points");
        lblCreditPoints.setFont(new Font("Arial",Font.BOLD,17));
        lblCreditPoints.setBounds(1275,250,300,40);
        c.add(lblCreditPoints);

        int lblX9=1310,lblY9=300;
        int idx=-1;
        if(courseNumber.contains("NSS")){
            idx = courseNumber.indexOf("NSS");
        }else if(courseNumber.contains("NCC")){
            idx = courseNumber.indexOf("NCC");
        }else if(courseNumber.contains("NSO")){
            idx = courseNumber.indexOf("NSO");
        }else if(courseNumber.contains("SCOUT")){
            idx = courseNumber.indexOf("SCOUT");
        }
        int j=0;
        for(double ele:creditPoints){
            if(j==idx){
                cn = new JLabel(ele +"N");
            }else{
                cn = new JLabel(String.valueOf(ele));
            }
            cn.setFont(new Font("Arial",Font.PLAIN,17));
            cn.setBounds(lblX9,lblY9,100,40);
            c.add(cn);
            lblY9=lblY9+45;
            j++;
        }

        JLabel lblTotal = new JLabel("Total (For this Semester) : ");
        lblTotal.setFont(new Font("Arial",Font.BOLD,20));
        lblTotal.setBounds(200,650,400,40);
        c.add(lblTotal);

        JLabel lblTch = new JLabel(String.valueOf(totalCreditHours));
        lblTch.setFont(new Font("Arial",Font.BOLD,20));
        lblTch.setBounds(640,650,100,40);
        c.add(lblTch);

        JLabel lblTcp = new JLabel(String.valueOf(totalCreditPoints));
        lblTcp.setFont(new Font("Arial",Font.BOLD,20));
        lblTcp.setBounds(1310,650,100,40);
        c.add(lblTcp);

        JLabel lblOverAllTotal = new JLabel("Total (Till this Semester) : ");
        lblOverAllTotal.setFont(new Font("Arial",Font.BOLD,20));
        lblOverAllTotal.setBounds(200,700,400,40);
        c.add(lblOverAllTotal);

        JLabel lblOTch = new JLabel(String.valueOf(totalCreditHoursTillSemester));
        lblOTch.setFont(new Font("Arial",Font.BOLD,20));
        lblOTch.setBounds(640,700,100,40);
        c.add(lblOTch);

        totalCreditPointsTillSemester = Double.parseDouble(String.format("%.1f",(totalCreditPointsTillSemester)));
        JLabel lblOTcp = new JLabel(String.valueOf(totalCreditPointsTillSemester));
        lblOTcp.setFont(new Font("Arial",Font.BOLD,20));
        lblOTcp.setBounds(1310,700,100,40);
        c.add(lblOTcp);

        JLabel lblSgpa = new JLabel("SGPA (For this Semester) : ");
        lblSgpa.setFont(new Font("Arial",Font.BOLD,17));
        lblSgpa.setBounds(400,750,400,40);
        c.add(lblSgpa);
        JLabel lblSgpaValue = new JLabel(String.valueOf(sgpa));
        lblSgpaValue.setFont(new Font("Arial",Font.BOLD,17));
        lblSgpaValue.setBounds(630,750,400,40);
        c.add(lblSgpaValue);

        JLabel lblOgpa = new JLabel("OGPA (Till this Semester) : ");
        lblOgpa.setFont(new Font("Arial",Font.BOLD,17));
        lblOgpa.setBounds(800,750,400,40);
        c.add(lblOgpa);
        JLabel lblOgpaValue = new JLabel(String.valueOf(ogpa));
        lblOgpaValue.setFont(new Font("Arial",Font.BOLD,17));
        lblOgpaValue.setBounds(1030,750,400,40);
        c.add(lblOgpaValue);
    }
}