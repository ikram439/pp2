package com.example.pp.model;

public class Leave {
    int id;
    int employee_id;
    String leave_type;
    String start_date;
    String end_date;
    String leave_status;

    public Leave() {
    }

    public Leave(int id, int employee_id, String leave_type, String start_date, String end_date, String leave_status) {
        this.id = id;
        this.employee_id = employee_id;
        this.leave_type = leave_type;
        this.start_date = start_date;
        this.end_date = end_date;
        this.leave_status = leave_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getLeave_type() {
        return leave_type;
    }

    public void setLeave_type(String leave_type) {
        this.leave_type = leave_type;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getLeave_status() {
        return leave_status;
    }

    public void setLeave_status(String leave_status) {
        this.leave_status = leave_status;
    }
}
