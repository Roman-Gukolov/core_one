package com.epam.JavaCoreOne.entities.taxiEntity;

import com.epam.JavaCoreOne.common.BaseTransport;
import com.epam.JavaCoreOne.common.CommonTaxi;

public class LargeTaxi extends CommonTaxi<LargeTaxi> {
    private String type;
    private int price;
    private Integer fuelConsumption;
    private int numberOfSeats;

    public LargeTaxi(String type, int price, int numberOfSeats, int fuelConsumption){
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

    public int deepCompare(BaseTransport taxi) {
        return (taxi != null) ? fuelConsumption.compareTo(taxi.getFuelConsumption()) : -1 ;
    }
}
