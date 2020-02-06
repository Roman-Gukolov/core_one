package com.epam.spring.intro.entity;

public class Skill {
    private int id;
    private int level;
    private int rate;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
                "id=" + id +
                "level=" + level +
                ", rate=" + rate +
                ", name=" + name +
                "}";
    }
}
