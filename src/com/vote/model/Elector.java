package com.vote.model;

public class Elector {
    private int id;
    private String name;
    private String sex;
    private int electedNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getElectedNum() {
        return electedNum;
    }

    public void setElectedNum(int electedNum) {
        this.electedNum = electedNum;
    }
}
