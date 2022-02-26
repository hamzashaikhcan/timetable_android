package com.sambeel.timetable.Classes;

public class Teachers {

    private String id;
    private String name;
    private String email;
    private String contact;
    private String zoom_id;

    public Teachers(String id, String name, String email, String contact, String zoom_id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.zoom_id = zoom_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getZoom_id() {
        return zoom_id;
    }

    public void setZoom_id(String zoom_id) {
        this.zoom_id = zoom_id;
    }
}
