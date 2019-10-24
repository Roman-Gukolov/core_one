package com.epam.JavaCoreOne.common;

public abstract class CommonUnderground<T extends CommonUnderground<T>> extends BaseTransport<T> {
    public CommonUnderground(int id, String type, int price, int numberOfSeats, int fuelConsumption) {
        super(id, type, price, numberOfSeats, fuelConsumption);
    }
}