package com.sambeel.timetable.Classes;

public class Timetable {

    private String id;
    private String class_name;
    private String time;

    public Timetable(String id, String class_name, String time) {
        this.id = id;
        this.class_name = class_name;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
