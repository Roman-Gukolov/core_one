package com.epam.JavaCoreOne.entities.undergroundEntity;

import com.epam.JavaCoreOne.common.BaseTransport;
import com.epam.JavaCoreOne.common.CommonUnderground;

public class SmallUnderground extends CommonUnderground<SmallUnderground> {
    private int id;
    private String  type;
    private int price;
    private Integer fuelConsumption;
    private int numberOfSeats;

    public SmallUnderground(int id, String type, int price, int numberOfSeats, int fuelConsumption) {
        super(id, type, price, numberOfSeats, fuelConsumption);
        this.id = id;
        this.type = type;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
        this.fuelConsumption = fuelConsumption;
    }

    public int getId() {
        return id;
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
        return (under != null) ? Integer.compare(fuelConsumption, under.getFuelConsumption()) : -1;
    }
}
