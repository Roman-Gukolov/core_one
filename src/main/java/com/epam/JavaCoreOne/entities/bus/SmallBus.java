package com.epam.JavaCoreOne.entities.bus;

import com.epam.JavaCoreOne.common.BaseTransport;
import com.epam.JavaCoreOne.common.CommonBus;

public class SmallBus extends CommonBus<SmallBus> {
    private int id;
    private String type;
    private int price;
    private Integer fuelConsumption;
    private int numberOfSeats;

    public SmallBus(int id, String type, int price, int numberOfSeats, int fuelConsumption) {
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

    public int deepCompare(BaseTransport bus) {
        return (bus != null) ? Integer.compare(fuelConsumption, bus.getFuelConsumption()) : -1;
    }
}
