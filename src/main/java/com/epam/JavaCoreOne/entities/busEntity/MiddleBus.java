package com.epam.JavaCoreOne.entities.busEntity;

import com.epam.JavaCoreOne.common.BaseTransport;
import com.epam.JavaCoreOne.common.CommonBus;

public class MiddleBus extends CommonBus<MiddleBus> {
    private String type;
    private int price;
    private Integer fuelConsumption;
    private int numberOfSeats;

    public MiddleBus(String type, int price, int numberOfSeats, int fuelConsumption) {
        super(type, price, numberOfSeats, fuelConsumption);
        this.type = type;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
        this.fuelConsumption = fuelConsumption;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public int deepCompare(BaseTransport bus) {
        return (bus != null) ? fuelConsumption.compareTo(bus.getFuelConsumption()) : -1 ;
    }
}
