package com.sambeel.timetable.Classes;

public class ClassHistory {

    private String id;
    private String date;
    private String day;
    private String time;
    private String perClassSalary;


    public ClassHistory(String id, String date, String day, String time, String perClassSalary) {
        this.id = id;
        this.date = date;
        this.day = day;
        this.time = time;
        this.perClassSalary = perClassSalary;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPerClassSalary() {
        return perClassSalary;
    }

    public void setPerClassSalary(String perClassSalary) {
        this.perClassSalary = perClassSalary;
    }
}
