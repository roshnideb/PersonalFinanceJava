package org.asu.roshni.personalfinance.model;

public class Category {

    private int categoryid;
    private int userid;
    private String name;
    private double budget;

    public Category(int categoryid, int userid, String name, double budget) {
        this.categoryid = categoryid;
        this.userid = userid;
        this.name = name;
        this.budget = budget;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
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

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
