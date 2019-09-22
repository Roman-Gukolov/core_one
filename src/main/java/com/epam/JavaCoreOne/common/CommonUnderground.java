package com.epam.JavaCoreOne.common;

public abstract class CommonUnderground<T extends CommonUnderground<T>> extends BaseTransport<T> {
    public CommonUnderground(String type, int price, int numberOfSeats, int fuelConsumption) {
        super(type, price, numberOfSeats, fuelConsumption);
    }
}