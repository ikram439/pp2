package com.example.pp.model;

public class Absence {

    int Id;
    int EmployeeId;
    String AbsenceDate;
    String AbsenceReason;

    public Absence() {
    }

    public Absence(int id, int employeeId, String absenceDate, String absenceReason) {
        Id = id;
        EmployeeId = employeeId;
        AbsenceDate = absenceDate;
        AbsenceReason = absenceReason;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public String getAbsenceDate() {
        return AbsenceDate;
    }

    public void setAbsenceDate(String absenceDate) {
        AbsenceDate = absenceDate;
    }

    public String getAbsenceReason() {
        return AbsenceReason;
    }

    public void setAbsenceReason(String absenceReason) {
        AbsenceReason = absenceReason;
    }
}
