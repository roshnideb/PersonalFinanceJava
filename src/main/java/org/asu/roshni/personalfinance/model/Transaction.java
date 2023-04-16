package org.asu.roshni.personalfinance.model;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {
    private int transactionid;
    private int userid;
    private int accountid;
    private int categoryid;
    private double amount;
    private Date date;
    private String name;
    private String comment;

    public Transaction(int transactionid, int userid, int accountid, int categoryid, double amount, Date date, String name, String comment) {
        this.transactionid = transactionid;
        this.userid = userid;
        this.accountid = accountid;
        this.categoryid = categoryid;
        this.amount = amount;
        this.date = date;
        this.name = name;
        this.comment = comment;
    }

    public int getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(int transactionid) {
        this.transactionid = transactionid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
