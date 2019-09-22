package com.epam.JavaCoreOne.common;

public abstract class CommonBus<T extends CommonBus<T>> extends BaseTransport<T> {
    public CommonBus(String type, int price, int numberOfSeats, int fuelConsumption){
        super(type, price, numberOfSeats, fuelConsumption);
    }
}
