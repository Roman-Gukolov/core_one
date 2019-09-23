package com.epam.JavaCoreOne.transportRepository;

import com.epam.JavaCoreOne.common.BaseTransport;
import com.epam.JavaCoreOne.exceprionRepository.NullTransportException;
import com.epam.JavaCoreOne.exceprionRepository.RepositoryExceptions;
import com.epam.JavaCoreOne.exceprionRepository.UndefinedTransportIdException;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс хранения транспорта
 */
public class TransportRepository<T> {
    private List<T> data;
    private int size = 0;

    public TransportRepository(int size) {
        this.data = new ArrayList<> (size);
    }

    /**
     * Получить парк авто
     */
    public TransportRepository<BaseTransport> get() throws NullTransportException {
        TransportRepository<BaseTransport> result = new TransportRepository<>(size);
        BaseTransport object;
        for (int i = 0; i < data.size(); i++) {
            object = (BaseTransport) data.get(i);
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
    public void add(T item) throws NullTransportException {
        if (item != null) {
            data.add(item);
            size++;
        } else {
            throw new NullTransportException("Некорректно указан транспорт для добавления");
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
            throw new NullTransportException("Некорректно указан транспорт для замены в парке");
        }
    }

    /**
     * Удалить авто из парка
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
            throw new UndefinedTransportIdException("Ничего не удалено. Транспорт не найден.");
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
     * Получить список транспорта по указанному диапазону мест в транспорте
     */
    public TransportRepository<T> findBySeats(int firstNumberOfSeats, int endNumberOfSeats) throws NullTransportException {
        TransportRepository<T> result = new TransportRepository<>(size);
        BaseTransport object;
        for (int i = 0; i < data.size(); i++) {
            object = (BaseTransport) data.get(i);
            if (object.getNumberOfSeats() >= firstNumberOfSeats && object.getNumberOfSeats() <= endNumberOfSeats) {
                result.add((T) object);
            }
        }
        return result;
    }

    /**
     * Получить список транспорта по указанному диапазону цены транспорта
     */
    public TransportRepository<T> findByPrice(int startPrice, int endPrice) throws NullTransportException {
        TransportRepository<T> result = new TransportRepository<>(size);
        BaseTransport object;
        for (int i = 0; i < data.size(); i++) {
            object = (BaseTransport) data.get(i);
            if (object.getPrice() >= startPrice && object.getPrice() <= endPrice) {
                result.add((T) object);
            }
        }
        return result;
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
