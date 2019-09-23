package com.epam.JavaCoreOne;

import com.epam.JavaCoreOne.common.CommonTaxi;
import com.epam.JavaCoreOne.common.CommonUnderground;
import com.epam.JavaCoreOne.transportService.TransportService;
import com.epam.JavaCoreOne.transportUtil.TransportUtil;
import com.epam.JavaCoreOne.common.BaseTransport;
import com.epam.JavaCoreOne.common.CommonBus;
import com.epam.JavaCoreOne.transportRepository.TransportRepository;

/**
 * Java Core 1
 * @author Roman_Gukolov@epam.com
 */
public class PublicTransportPark {
    public static void main( String[] args ) {
        TransportService service = new TransportService();
        TransportRepository<BaseTransport> transportList = new TransportRepository<>(5);

        // кейс с обработкой исключений
        CommonBus bus1 = TransportUtil.createBus(-5, 100, 100);
        CommonTaxi taxi1 = TransportUtil.createTaxi(30000, 100, 101);
        CommonUnderground under1 = TransportUtil.createUnderground(50000, 101, 99);

        //нормальное создание транспорта
        try {
            bus1 = TransportUtil.createBus(70000, 5, 20);
            taxi1 = TransportUtil.createTaxi(30000, 100, 80);
            under1 = TransportUtil.createUnderground(50000, 24, 10);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка. Вводимые данные могут содержать только цифры.");
        }

        transportList.add(bus1);
        transportList.add(taxi1);
        transportList.add(under1);
        TransportUtil.printTransports(transportList);

        System.out.println(service.getAllTransportPrice(transportList));
        service.sortPark(transportList);
        TransportUtil.printTransports(transportList);

        TransportUtil.printTransports(transportList.findByPrice(30000, 50000));
        transportList.delete(2);
        TransportUtil.printTransports(transportList.findBySeats(5, 24));

    }
}
