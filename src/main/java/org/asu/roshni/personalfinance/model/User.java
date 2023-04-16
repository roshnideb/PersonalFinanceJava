package org.asu.roshni.personalfinance.model;

public class User {

    private int userid;
    private String password;
    private String fname;
    private String lname;
    private String email;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(int userid, String password, String fname, String lname, String email) {
        this.userid = userid;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }
}
