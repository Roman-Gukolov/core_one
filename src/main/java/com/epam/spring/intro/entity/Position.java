package com.epam.spring.intro.entity;

import java.io.Serializable;
import java.util.List;

public class Position implements Serializable {
    private static final long serialVersionUID = -1227061126627971845L;

    private int id;

    private String name;

    private List<Employee> employees;

    private List<Skill> skills;

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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
