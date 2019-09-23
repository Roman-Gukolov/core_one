package com.epam.JavaCoreOne.common;

public abstract class CommonBus<T extends CommonBus<T>> extends BaseTransport<T> {
    public CommonBus(int id, String type, int price, int numberOfSeats, int fuelConsumption) {
        super(id, type, price, numberOfSeats, fuelConsumption);
    }
}
