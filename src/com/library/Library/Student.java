package com.library.Library;

import java.time.LocalDate;

public class Student extends MemberRecord {
    private String studentId;
    private String department;

    public Student(String address, String name, int maxBookLimit, int noBooksIssued, LocalDate dateOfMembership, String type, long memberId, String phoneNo, String studentId, String department) {
        super(address, name, maxBookLimit, noBooksIssued, dateOfMembership, type, memberId, phoneNo);
        this.studentId = studentId;
        this.department = department;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return super.toString() + ", Student{" +
                "studentId='" + studentId + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
