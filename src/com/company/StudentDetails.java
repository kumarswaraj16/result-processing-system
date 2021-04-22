package com.company;

public class StudentDetails {
    final private String programId;
    final private String enrollNo;
    final private String studentName;
    final private int currentSemester;
    final private String motherName;
    final private String fatherName;
    final private String departmentCode;
    final private int admissionYear;

    StudentDetails(String programId,String enrollNo,String studentName,int currentSemester,String motherName,String fatherName,String departmentCode,int admissionYear){
        this.programId = programId;
        this.enrollNo = enrollNo;
        this.studentName = studentName;
        this.currentSemester = currentSemester;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.departmentCode = departmentCode;
        this.admissionYear = admissionYear;
    }

    public String getProgramId() {
        return programId;
    }

    public String getEnrollNo() {
        return enrollNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getCurrentSemester() {
        return currentSemester;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public int getAdmissionYear() {
        return admissionYear;
    }
}
