package com.epam.JavaCoreOne.common;

public abstract class CommonTaxi<T extends CommonTaxi<T>> extends BaseTransport<T> {
    public CommonTaxi(int id, String type, int price, int numberOfSeats, int fuelConsumption) {
        super(id, type, price, numberOfSeats, fuelConsumption);
    }
}
