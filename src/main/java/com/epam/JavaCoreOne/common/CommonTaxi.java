package com.epam.JavaCoreOne.common;

public abstract class CommonTaxi<T extends CommonTaxi<T>> extends BaseTransport<T> {
    public CommonTaxi(String type, int price, int numberOfSeats, int fuelConsumption){
        super(type, price, numberOfSeats, fuelConsumption);
    }
}
