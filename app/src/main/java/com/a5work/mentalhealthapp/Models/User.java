package com.a5work.mentalhealthapp.Models;

public class User {
    private String usename;
    private String password;
    private String email;

    public User(String usename, String password, String email) {
        this.usename = usename;
        this.password = password;
        this.email = email;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
