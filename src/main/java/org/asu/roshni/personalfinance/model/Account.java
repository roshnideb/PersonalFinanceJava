package org.asu.roshni.personalfinance.model;

public class Account {

    private int accountid;
    private int userid;
    private String name;
    private double balance;
    private String type;

    public Account(int accountid, int userid, String name, double balance, String type) {
        this.accountid = accountid;
        this.userid = userid;
        this.name = name;
        this.balance = balance;
        this.type = type;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
