package com.epam.JavaCoreOne.common;

public abstract class BaseTransport<T extends BaseTransport<T>> implements Comparable<BaseTransport> {
    protected int id;
    protected String type;
    protected int price;
    protected int numberOfSeats;
    protected Integer fuelConsumption;

    public BaseTransport(int id, String type, int price, int numberOfSeats, int fuelConsumption) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
        this.fuelConsumption = fuelConsumption;
    }

    public abstract int getId();

    public abstract String getType();

    public abstract int getPrice();

    public abstract int getFuelConsumption();

    public abstract int getNumberOfSeats();

    public abstract int deepCompare(BaseTransport transport);

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
                "id=" + id +
                ", type=" + getClass().getSimpleName() +
                ", price=" + price +
                ", fuelConsumption=" + fuelConsumption +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}
