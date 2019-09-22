package com.epam.JavaCoreOne.common;

public abstract class BaseTransport<T extends BaseTransport<T>> implements Comparable<BaseTransport> {
    protected  String type;
    protected int price;
    protected int numberOfSeats;
    protected Integer fuelConsumption;

    public BaseTransport(String type, int price, int numberOfSeats, int fuelConsumption) {
        this.type = type;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
        this.fuelConsumption = fuelConsumption;
    }

    public abstract String getType();

    public abstract int getPrice();

    public abstract int getFuelConsumption();

    public abstract int getNumberOfSeats();

    protected abstract int deepCompare(BaseTransport transport);

    @Override
    public int compareTo(BaseTransport transport) {
        if (transport != null) {
            return deepCompare(transport);
        } else {
            return -1;
        }
    }

    @Override
    public final String toString() {
        return "Transport{" +
                "type=" + getClass().getSimpleName() +
                ", price=" + price +
                ", fuelConsumption=" + fuelConsumption +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}
