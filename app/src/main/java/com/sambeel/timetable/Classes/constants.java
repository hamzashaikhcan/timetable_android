package com.sambeel.timetable.Classes;

public class constants {

    private String key;
    private String secret;
    private String jwt;

    public constants() {
        this.key = "iaUM5JwhX02Ljcz6vVxpy5DGCkYPg7UNVRPy";
        this.secret = "SNlv1lRlFqy8ckFWIM3DY08mFKo0q8L3oDDQ";
        this.jwt = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOm51bGwsImlzcyI6IjhPaHdMaEZzUjNpZVd1MlF2eVBWamciLCJleHAiOjE3NjcxNTAwMDAsImlhdCI6MTYyODIyMTI0Mn0.maWptxZBtk92Nb3H7aIFXEkK43R1mYksaXT-cDA3-N0";
    }

    public String getKey() {
        return key;
    }

    public String getSecret() {
        return secret;
    }

    public String getJwt() {
        return jwt;
    }
}
