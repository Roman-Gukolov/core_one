package com.epam.spring.basic.factory;


public class RandomCoeff {
    private double coeff;

    public RandomCoeff() {
        this.coeff = Math.random() * 0.15;
    }

    public double getCoeff() {
        return coeff;
    }

    public RandomCoeff getObject() {
        return new RandomCoeff();
    }
}
