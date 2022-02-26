package com.sambeel.timetable.Classes;

public class StudentClasses {

    private String id;
    private String teacher_name;
    private String teacher_zoom;
    private String day;
    private String s_time;
    private String f_time;

    public StudentClasses(String id, String teacher_name, String teacher_zoom, String day, String s_time, String f_time) {
        this.id = id;
        this.teacher_name = teacher_name;
        this.teacher_zoom = teacher_zoom;
        this.day = day;
        this.s_time = s_time;
        this.f_time = f_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_zoom() {
        return teacher_zoom;
    }

    public void setTeacher_zoom(String teacher_zoom) {
        this.teacher_zoom = teacher_zoom;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getS_time() {
        return s_time;
    }

    public void setS_time(String s_time) {
        this.s_time = s_time;
    }

    public String getF_time() {
        return f_time;
    }

    public void setF_time(String f_time) {
        this.f_time = f_time;
    }
}
