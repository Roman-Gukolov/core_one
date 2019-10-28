package com.epam.JavaCoreOne;

import com.epam.JavaCoreOne.annotation.annotations.InitThisObject;
import com.epam.JavaCoreOne.annotation.annotations.ProdCode;
import com.epam.JavaCoreOne.transport.service.TransportService;
import com.epam.JavaCoreOne.transport.util.MainCommand;
import com.epam.JavaCoreOne.transport.util.TransportUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

/**
 * Java Core 1
 *
 * @author Roman Gukolov
 */
public class PublicTransportPark {
    private static Scanner input = new Scanner(System.in);
    @InitThisObject(classPath = "com.epam.JavaCoreOne.transport.service.TransportService")
    private static TransportService service;

    @ProdCode
    public static void main( String[] args ) {
        try {
            init();
            start();
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка инициализации");
        }
    }

    private static void start() {
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
                    default: {
                        System.out.println("Неизвестная команда");
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка: не удалось обработать операцию");
            }
        }
    }

    /**
     * Инициализация класса
     * По задаче Java Core II
     */
    private static void init() throws ClassNotFoundException {
        service = (TransportService) tryInit("service", null);
        if (service == null) {
            throw new ClassNotFoundException("Ошибка инициализации сервиса");
        }
    }

    private static Object tryInit(String fieldName, Object param) {
        try {
            Field field = PublicTransportPark.class.getDeclaredField(fieldName);
            if (field.isAnnotationPresent(InitThisObject.class)) {
                InitThisObject annotationInfo = field.getAnnotation(InitThisObject.class);
                Class<?> clazz = Class.forName(annotationInfo.classPath());
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | NoSuchFieldException
                | IllegalAccessException | ClassNotFoundException e) {
            System.out.println("Ошибка инициализации: " + e);
        }
        return null;
    }
}
