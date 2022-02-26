package com.sambeel.timetable.Classes;

public class Attendance {

    private String id;
    private String day;
    private String att_date;
    private String att_time;
    private String status;

    public Attendance(String id, String day, String att_date, String att_time, String status) {
        this.id = id;
        this.day = day;
        this.att_date = att_date;
        this.att_time = att_time;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getAtt_date() {
        return att_date;
    }

    public void setAtt_date(String att_date) {
        this.att_date = att_date;
    }

    public String getAtt_time() {
        return att_time;
    }

    public void setAtt_time(String att_time) {
        this.att_time = att_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
