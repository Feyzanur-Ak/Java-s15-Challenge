package com.library.Library;

import java.time.LocalDate;

public class Faculty extends MemberRecord {
    private String facultyId;
    private String specialization;

    public Faculty(String address, String name, int maxBookLimit, int noBooksIssued, LocalDate dateOfMembership, String type, long memberId, String phoneNo, String facultyId, String specialization) {
        super(address, name, maxBookLimit, noBooksIssued, dateOfMembership, type, memberId, phoneNo);
        this.facultyId = facultyId;
        this.specialization = specialization;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return super.toString() + ", Faculty{" +
                "facultyId='" + facultyId + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
