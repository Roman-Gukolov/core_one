package com.epam.JavaCoreOne.transportRepository;

import com.epam.JavaCoreOne.common.BaseTransport;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс хранения транспорта
 */
public class TransportRepository<T> {
    private List<T> data;
    private int size = 0;

    public TransportRepository(int size) {
        this.data = new ArrayList<T>(20);
    }

    /**
     * Получить авто по индексу
     */
    public T get(int i) {
        return data.get(i);
    }

    /**
     * Добавить авто
     */
    public void add(T item) {
        if (item != null) {
            data.add(size, item);
            size++;
        }
    }

    /**
     * Установить транспорт в заданое место(индекс)
     */
    public void set(int index, T item) {
        if (item != null && index <= size) {
            data.set(index, item);
        }
    }

    /**
     * Удалить авто из парка
     */
    public void delete(int i) {
        if (i <= size) {
            this.data.remove(i);
            size--;
        }
    }

    /**
     * Получить размер парка
     */
    public int size() {
        return this.size;
    }

    /**
     * Получить внутреннее состояние транспорта
     */
    public String toString(T item) {
        if (item != null) {
            return item.toString();
        } else {
            return "";
        }
    }

    /**
     * Поиск транспорта по параметрам
     */
    public TransportRepository<T> findByParameters(String type,int price, int numberOfSeats, int fuelConsumption) {
        TransportRepository<T> result = new TransportRepository<>(5);
        BaseTransport object;
        for (int i = 0; i < size; i++) {
            object = (BaseTransport) data.get(i);
            if (object.getType().equals(type) && object.getPrice() == price
                    && object.getFuelConsumption() == fuelConsumption && object.getNumberOfSeats() == numberOfSeats){
                result.add((T) object);
            }
        }
        return result;
    }

    /**
     * Получить список транспорта по указанному диапазону мест в транспорте
     */
    public TransportRepository<T> findBySeats(int firstNumberOfSeats, int endNumberOfSeats) {
        TransportRepository<T> result = new TransportRepository<>(5);
        BaseTransport object;
        for (int i = 0; i < size; i++) {
            object = (BaseTransport) data.get(i);
            if (object.getNumberOfSeats() >= firstNumberOfSeats && object.getNumberOfSeats() <= endNumberOfSeats){
                result.add((T) object);
            }
        }
        return result;
    }

    /**
     * Получить список транспорта по указанному диапазону цены транспорта
     */
    public TransportRepository<T> findByPrice(int startPrice, int endPrice) {
        TransportRepository<T> result = new TransportRepository<>(5);
        BaseTransport object;
        for (int i = 0; i < size; i++) {
            object = (BaseTransport) data.get(i);
            if (object.getPrice() >= startPrice && object.getPrice() <= endPrice){
                result.add((T) object);
            }
        }
        return result;
    }
}
