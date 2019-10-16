package com.epam.lambdas.entities;

public class A {
    private String name;
    private int value1;
    private Double value2;

    public String getName() {
        return name;
    }

    public int getValue1() {
        return value1;
    }

    public Double getValue2() {
        return value2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public void setValue2(Double value2) {
        this.value2 = value2;
    }

    public void stat1(int value) {
        this.value1 = value;
        if (this.value1 > 50) {
            this.value2 = Double.valueOf(value1*11);
            this.name = "stat1";
        } else {
            stat2(value);
        }
    }

    public void stat2(int value) {
        this.value1 = value;
        if (this.value1 <= 50) {
            this.value2 = Double.valueOf(value1/10);
            this.name = "stat2";
        } else {
            stat1(value);
        }
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", value1=" + value1 +
                ", value2=" + value2 +
                '}';
    }
}
