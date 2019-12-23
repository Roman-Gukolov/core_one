package com.epam.spring.basic.entity;

public class Skill {
    private int level;
    private int rate;
    private String name;

    public int getLevel() {
        return level;
    }

    public void setLevel(int id) {
        this.level = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "level=" + level +
                ", rate=" + rate +
                ", name=" + name +
                "}";
    }
}
