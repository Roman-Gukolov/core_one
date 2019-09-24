package com.epam.JavaCoreOne;

import com.epam.JavaCoreOne.transport.service.TransportService;
import com.epam.JavaCoreOne.transport.util.MainCommand;
import com.epam.JavaCoreOne.transport.util.TransportUtil;

import java.util.Scanner;

/**
 * Java Core 1
 * @author Roman_Gukolov@epam.com
 */
public class PublicTransportPark {
    private static Scanner input = new Scanner(System.in);
    private static TransportService service = new TransportService();

    public static void main( String[] args ) {
        boolean work = true;
        String command;
        System.out.println(TransportUtil.HELP_TEXT);

        while (work) {
            try {
                System.out.println("Введите команду");
                command = input.nextLine();
                switch (MainCommand.valueOf(command)) {
                    case Автобус:
                    case Такси:
                    case Поезд: {
                        service.inputData(command);
                        break;
                    }
                    case Транспорт: {
                        service.showPark();
                        break;
                    }
                    case Найти: {
                        service.findTransport();
                        break;
                    }
                    case Удалить: {
                        service.removeTransport();
                        break;
                    }
                    case Сортировать: {
                        service.sortPark();
                        break;
                    }
                    case Стоимость: {
                        service.getParkPrice();
                        break;
                    }
                    case findPrices: {
                        service.findByRangePrice();
                        break;
                    }
                    case findSeats: {
                        service.findByRangeSeats();
                        break;
                    }
                    case help: {
                        System.out.println(TransportUtil.HELP_TEXT);
                        break;
                    }
                    case exit: {
                        work = false;
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка: не удалось обработать операцию");
            }
        }
    }
}
