package com.epam.spring.basic.factory;


public class RandomCoeff {
    private double coeff;
    private double constCoeff;

    public RandomCoeff() {
        this.coeff = Math.random() * 0.15 * constCoeff;
    }

    public double getCoeff() {
        return coeff;
    }

    public RandomCoeff getObject() {
        return new RandomCoeff();
    }

    public void setConstCoeff(double constCoeff) {
        this.constCoeff = constCoeff;
    }
}
