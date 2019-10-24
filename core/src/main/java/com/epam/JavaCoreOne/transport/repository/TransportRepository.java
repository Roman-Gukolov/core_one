package com.epam.JavaCoreOne.transport.repository;

import com.epam.JavaCoreOne.annotation.annotations.ThisCodeSmells;
import com.epam.JavaCoreOne.common.BaseTransport;
import com.epam.JavaCoreOne.exceprion.EmptyTransportException;
import com.epam.JavaCoreOne.exceprion.RepositoryExceptions;
import com.epam.JavaCoreOne.exceprion.UndefinedTransportIdException;
import com.epam.JavaCoreOne.functionalInterface.Converter;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс хранения транспорта
 */
public class TransportRepository<T> {
    private List<T> data;
    @ThisCodeSmells(reviewer = "Petya")
    private int size = 0;

    /** Поле для индексации аннотации */
    @ThisCodeSmells(reviewer = "Petya")
    private String fieldForAnnotation = "string";

    public TransportRepository(int size) {
        this.data = new ArrayList<> (size);
    }

    /**
     * Получить парк авто
     */
    public TransportRepository<BaseTransport> get() throws EmptyTransportException {
        TransportRepository<BaseTransport> result = new TransportRepository<>(size);
        BaseTransport object;
        for (T datum : data) {
            object = (BaseTransport) datum;
            result.add(object);
        }
        return result;
    }
    /**
     * Получить авто по индексу
     */
    public T getById(int i) {
        BaseTransport object;
        for (T item : data) {
            object = (BaseTransport) item;
            if (object.getId() == i) {
                return item;
            }
        }
        return null;
    }

    /**
     * Добавить авто
     */
    public void add(T item) throws EmptyTransportException {
        if (item != null) {
            data.add(item);
            size++;
        } else {
            throw new EmptyTransportException("Некорректно указан транспорт для добавления");
        }
    }

    /**
     * Установить транспорт в заданое место(индекс)
     */
    public void set(int index, T item) throws RepositoryExceptions {
        if (item != null ) {
                BaseTransport object;
                for (int k = 0; k < data.size(); k++) {
                    object = (BaseTransport) data.get(k);
                    if (object.getId() == index) {
                        data.set(k, item);
                        return;
                    }
                }
        } else {
            throw new EmptyTransportException("Некорректно указан транспорт для замены в парке");
        }
    }

    /**
     * REMOVE авто из парка
     */
    public void delete(int i) throws UndefinedTransportIdException {
        if (i <= size) {
            BaseTransport object;
            for (int k = 0; k < data.size(); k++) {
                object = (BaseTransport) data.get(k);
                if (object.getId() == i) {
                    data.remove(k);
                    size--;
                    return;
                }
            }
        } else {
            throw new UndefinedTransportIdException("Ничего не удалено. TRANSPORT не найден.");
        }
    }

    /**
     * Очистить парк
     */
    public void clear() {
        data.clear();
    }

    /**
     * Получить размер парка
     */
    @ThisCodeSmells(reviewer = "Petya")
    public int size() {
        return this.size;
    }

    /**
     * Получить список транспорта по указанному диапазону мест в транспорте
     */
    @SuppressWarnings({"unchecked", "unused"})
    @ThisCodeSmells(reviewer = "Petya")
    public TransportRepository<T> findBySeats(int firstNumberOfSeats, int endNumberOfSeats) throws EmptyTransportException {
        TransportRepository<T> result = new TransportRepository<>(size);
        BaseTransport object;
        for (T datum : data) {
            object = (BaseTransport) datum;
            if (object.getNumberOfSeats() >= firstNumberOfSeats && object.getNumberOfSeats() <= endNumberOfSeats) {
                result.add((T) object);
            }
        }
        return result;
    }

    /**
     * Получить список транспорта по указанному диапазону цены транспорта
     */
    @ThisCodeSmells(reviewer = "Petya")
    @SuppressWarnings({"unchecked", "unused"})
    public TransportRepository<T> findByPrice(int startPrice, int endPrice) throws EmptyTransportException {
        TransportRepository<T> result = new TransportRepository<>(size);
        BaseTransport object;
        for (T datum : data) {
            object = (BaseTransport) datum;
            if (object.getPrice() >= startPrice && object.getPrice() <= endPrice) {
                result.add((T) object);
            }
        }
        return result;
    }

    /**
     * Испотльзование функционального интерфейса. Сонвертация строки в число
     * @return число
     */
    @ThisCodeSmells(reviewer = "Petya")
    public int useInterfaceAnnotation(String line) {
        Integer converted = 0;
        try {
            Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
            converted = converter.convert(line);
            System.out.println(converted);
        } catch (Exception e) {
            System.out.println("Произошла ошибка при конвертации.");
        }
        return converted;
    }

    /**
     * Получить внутреннее состояние транспорта
     */
    @Override
    public String toString() {
        return "data=" + data +
                ", size=" + size +
                '}';
    }
}
