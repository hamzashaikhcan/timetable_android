package com.sambeel.timetable.Classes;

import java.util.Locale;

public class base_url {

    private String url;
    private String BASE_URL = "http://sup-timetable.herokuapp.com/api/api.php";
    private String SERVER = "http://sup-timetable.herokuapp.com/api/api.php?action=";
    private String LOCAL = "http://192.168.1.24/timetable/api/api.php?action=";
    private String EMULATOR = "http://10.0.2.2/timetable/api/api.php?action=";
    public base_url() {
        url = SERVER;
    }

    public String getUrl(){
        return url;
    }
    public String getBaseUrl(){
        return BASE_URL;
    }


}
