package com.epam.JavaCoreOne.entities.undergroundEntity;

import com.epam.JavaCoreOne.common.BaseTransport;
import com.epam.JavaCoreOne.common.CommonUnderground;

public class MiddleUnderground extends CommonUnderground<MiddleUnderground> {
    private String type;
    private int price;
    private Integer fuelConsumption;
    private int numberOfSeats;

    public MiddleUnderground(String type, int price, int numberOfSeats, int fuelConsumption) {
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

    public int deepCompare(BaseTransport under) {
        return (under != null) ? fuelConsumption.compareTo(under.getFuelConsumption()) : -1 ;
    }
}
