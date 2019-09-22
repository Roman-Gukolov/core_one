package com.epam.JavaCoreOne.transportUtil;

import com.epam.JavaCoreOne.common.BaseTransport;
import com.epam.JavaCoreOne.common.CommonBus;
import com.epam.JavaCoreOne.common.CommonTaxi;
import com.epam.JavaCoreOne.common.CommonUnderground;
import com.epam.JavaCoreOne.entities.busEntity.LargeBus;
import com.epam.JavaCoreOne.entities.busEntity.MiddleBus;
import com.epam.JavaCoreOne.entities.busEntity.SmallBus;
import com.epam.JavaCoreOne.entities.taxiEntity.LargeTaxi;
import com.epam.JavaCoreOne.entities.taxiEntity.MiddleTaxi;
import com.epam.JavaCoreOne.entities.taxiEntity.SmallTaxi;
import com.epam.JavaCoreOne.entities.undergroundEntity.LargeUnderground;
import com.epam.JavaCoreOne.entities.undergroundEntity.MiddleUnderground;
import com.epam.JavaCoreOne.entities.undergroundEntity.SmallUnderground;
import com.epam.JavaCoreOne.exceprionRepository.IncorrectInputData;
import com.epam.JavaCoreOne.transportRepository.TransportRepository;

/**
 * Утильный класс для работы с транспортом
 */
public class TransportUtil {

    /** Тип Bus */
    private static final String TYPE_BUS = "Bus";

    /** Тип Taxi */
    private static final String TYPE_TAXI = "Taxi";

    /** Тип Underground */
    private static final String TYPE_UNDERGROUND = "Underground";

    /** Сообщение ошибки при неправильном вводе цены */
    private static final String PRICE_EXCEPTION = "Введена некорректная цена. Цена не может быть меньше 0.";

    /** Сообщение ошибки при неправильном вводе количества мест */
    private static final String SEATS_EXCEPTION = "Введено некорректное количество мест. Количество мест не может быть меньше нуля и больше 100.";

    /** Сообщение ошибки при неправильном вводе расхода топлива */
    private static final String FUEL_EXCEPTION = "Введено некорректное значение расхода топлива. Расход топлива не может быть меньше 0 и больше 100";

    /**
     * Создание автобуса
     * @param price цена
     * @param numberOfSeats количество мест
     * @param fuelConsumption расход топлива
     * @return автобус, если нет ошибок. Null если некорректно введены данные
     */
    public static CommonBus createBus(int price, int numberOfSeats, int fuelConsumption) {
        try {
            validateData(price, numberOfSeats, fuelConsumption);
            if (numberOfSeats <= 10) {
                return new SmallBus(TYPE_BUS, price, numberOfSeats, fuelConsumption);
            } else if (numberOfSeats <= 25) {
                return new MiddleBus(TYPE_BUS, price, numberOfSeats, fuelConsumption);
            } else if (numberOfSeats <= 100) {
                return new LargeBus(TYPE_BUS, price, numberOfSeats, fuelConsumption);
            }
        } catch (IncorrectInputData e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Создание такси
     * @param price цена
     * @param numberOfSeats количество мест
     * @param fuelConsumption расход топлива
     * @return такси, если нет ошибок. Null если некорректно введены данные
     */
    public static CommonTaxi createTaxi(int price, int numberOfSeats, int fuelConsumption) {
        try {
            validateData(price, numberOfSeats, fuelConsumption);
            if (numberOfSeats <= 10) {
                return new SmallTaxi(TYPE_TAXI, price, numberOfSeats, fuelConsumption);
            } else if (numberOfSeats <= 25) {
                return new MiddleTaxi(TYPE_TAXI, price, numberOfSeats, fuelConsumption);
            } else if (numberOfSeats <= 100) {
                return new LargeTaxi(TYPE_TAXI, price, numberOfSeats, fuelConsumption);
            }
        } catch (IncorrectInputData e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Создание подземного поезда
     * @param price цена
     * @param numberOfSeats количество мест
     * @param fuelConsumption расход топлива
     * @return Подземный поезд, если нет ошибок. Null если некорректно введены данные
     */
    public static CommonUnderground createUnderground(int price, int numberOfSeats, int fuelConsumption) {
        try {
            validateData(price, numberOfSeats, fuelConsumption);
            if (numberOfSeats <= 10) {
                return new SmallUnderground(TYPE_UNDERGROUND, price, numberOfSeats, fuelConsumption);
            } else if (numberOfSeats <= 25) {
                return new MiddleUnderground(TYPE_UNDERGROUND, price, numberOfSeats, fuelConsumption);
            } else if (numberOfSeats <= 100) {
                return new LargeUnderground(TYPE_UNDERGROUND, price, numberOfSeats, fuelConsumption);
            }
        } catch (IncorrectInputData e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Печать всех транспортов в парке
     * @param transportList список транспорта
     */
    public static void printTransports(TransportRepository<BaseTransport> transportList) {
        for (int i = 0; i < transportList.size(); i++) {
            System.out.println(transportList.toString(transportList.get(i)));
        }
    }

    /**
     * проверка данных
     * @param price цена
     * @param numberOfSeats количество мест
     * @param fuelConsumption расход топлива
     * @throws IncorrectInputData
     */
    private static void validateData(int price, int numberOfSeats, int fuelConsumption) throws IncorrectInputData {
        if (!isValidPrice(price)) {
            throw new IncorrectInputData(PRICE_EXCEPTION);
        } else if (!isValidNumberOfSeats(numberOfSeats)) {
            throw new IncorrectInputData(SEATS_EXCEPTION);
        } else if (!isValidFuelConsumption(fuelConsumption)) {
            throw new IncorrectInputData(FUEL_EXCEPTION);
        }
    }

    /**
     * Проверка цены
     */
    private static boolean isValidPrice(int price) {
        return price > 0;
    }

    /**
     * Проверка количества мест
     */
    private static boolean isValidNumberOfSeats(int numberOfSeats) {
        return numberOfSeats > 0 && numberOfSeats <= 100;
    }

    /**
     * Проверка расхода топлива
     */
    private static boolean isValidFuelConsumption(int fuelConsumption) {
        return fuelConsumption > 0 && fuelConsumption <= 100;
    }
}
