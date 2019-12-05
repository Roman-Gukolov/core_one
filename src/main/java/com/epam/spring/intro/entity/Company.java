package com.epam.spring.intro.entity;

import java.io.Serializable;
import java.util.List;

public class Company implements Serializable{
    private static final long serialVersionUID = 3916043981081250887L;

    private String title;

    private int lifeTime;

    private int successfullyProjects;

    private double totalSalary;

    private double salaryLastYear;

    private List<Position> positions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public int getSuccessfullyProjects() {
        return successfullyProjects;
    }

    public void setSuccessfullyProjects(int successfullyProjects) {
        this.successfullyProjects = successfullyProjects;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public double getSalaryLastYear() {
        return salaryLastYear;
    }

    public void setSalaryLastYear(double salaryLastYear) {
        this.salaryLastYear = salaryLastYear;
    }
}
