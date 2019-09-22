package com.epam.JavaCoreOne.transportService;

import com.epam.JavaCoreOne.common.BaseTransport;
import com.epam.JavaCoreOne.transportRepository.TransportRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransportService {
    private int totalPrice;

    /**
     * Получить полную стоимость автопарка
     * @param transportRepository список транспорта
     * @return полная стоимсоть
     */
    public int getAllTransportPrice(TransportRepository<BaseTransport> transportRepository) {
        for (int i = 0; i < transportRepository.size(); i++) {
            this.totalPrice += transportRepository.get(i).getPrice();
        }
        return this.totalPrice;
    }

    /**
     * Сортировка парка транспорта
     * @param transportRepository парк транспорта
     */
    public void sortPark(TransportRepository<BaseTransport> transportRepository) {
        List<BaseTransport> list = new ArrayList<>();
        for (int i = 0; i < transportRepository.size(); i++) {
            list.add(transportRepository.get(i));
        }
        Collections.sort(list);
        for (int i = 0; i < transportRepository.size(); i++) {
            transportRepository.set(i, list.get(i));
        }
    }
}
