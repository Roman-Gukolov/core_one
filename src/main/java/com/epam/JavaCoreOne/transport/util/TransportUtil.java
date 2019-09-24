package com.epam.JavaCoreOne.transport.util;

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
import com.epam.JavaCoreOne.exceprion.EmptyTransportException;
import com.epam.JavaCoreOne.exceprion.IncorrectInputDataException;
import com.epam.JavaCoreOne.transport.repository.TransportRepository;

/**
 * Утильный класс для работы с транспортом
 */
public class TransportUtil {

    /** Порядоквый номер трансопрта */
    public static int COUNT_TRANSPORT = 0;

    public static final String HELP_TEXT = "Добро пожаловать. \r\n"
            + "Для создания автобуса введите \"Автобус\" \r\n"
            + "Для создания такси введите \"Такси\" \r\n"
            + "Для создания подземного поезда введите \"Поезд\" \r\n"
            + "Для просмотра всего парка транспорта введите \"Транспорт\" \r\n"
            + "Для поиска транспорта введите \"Найти\" \r\n"
            + "Для удаления транспорта введите \"Удалить\" \r\n"
            + "Для сортировки парка по расходу топлива введите \"Сортировать\" \r\n"
            + "Для просмотра общей стоимости всего парка введите \"Стоимость\" \r\n"
            + "Для поиска транспорта по указанному диапазону цен введите \"findPrices\" \r\n"
            + "Для поиска транспорта по куказанному диапазону мест введите \"findSeats\" \r\n"
            + "Для отображения этой памятки введите \"help\" \r\n"
            + "Для выхода из программы введите \"exit\"";

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
                COUNT_TRANSPORT++;
                return new SmallBus(COUNT_TRANSPORT, SmallBus.class.getSimpleName(), price,
                        numberOfSeats, fuelConsumption);
            } else if (numberOfSeats <= 25) {
                COUNT_TRANSPORT++;
                return new MiddleBus(COUNT_TRANSPORT, MiddleBus.class.getSimpleName(), price,
                        numberOfSeats, fuelConsumption);
            } else if (numberOfSeats <= 100) {
                COUNT_TRANSPORT++;
                return new LargeBus(COUNT_TRANSPORT, LargeBus.class.getSimpleName(), price,
                        numberOfSeats, fuelConsumption);
            }
        } catch (IncorrectInputDataException e) {
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
                COUNT_TRANSPORT++;
                return new SmallTaxi(COUNT_TRANSPORT, SmallTaxi.class.getSimpleName(), price,
                        numberOfSeats, fuelConsumption);
            } else if (numberOfSeats <= 25) {
                COUNT_TRANSPORT++;
                return new MiddleTaxi(COUNT_TRANSPORT, MiddleTaxi.class.getSimpleName(), price,
                        numberOfSeats, fuelConsumption);
            } else if (numberOfSeats <= 100) {
                COUNT_TRANSPORT++;
                return new LargeTaxi(COUNT_TRANSPORT, LargeTaxi.class.getSimpleName(), price,
                        numberOfSeats, fuelConsumption);
            }
        } catch (IncorrectInputDataException e) {
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
                COUNT_TRANSPORT++;
                return new SmallUnderground(COUNT_TRANSPORT, SmallUnderground.class.getSimpleName(), price,
                        numberOfSeats, fuelConsumption);
            } else if (numberOfSeats <= 25) {
                COUNT_TRANSPORT++;
                return new MiddleUnderground(COUNT_TRANSPORT, MiddleUnderground.class.getSimpleName(), price,
                        numberOfSeats, fuelConsumption);
            } else if (numberOfSeats <= 100) {
                COUNT_TRANSPORT++;
                return new LargeUnderground(COUNT_TRANSPORT, LargeUnderground.class.getSimpleName(), price,
                        numberOfSeats, fuelConsumption);
            }
        } catch (IncorrectInputDataException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Печать всех транспортов в парке
     * @param transportList список транспорта
     */
    public static void printTransports(TransportRepository<BaseTransport> transportList) throws EmptyTransportException {
        if (transportList != null) {
                String transport = (transportList.get()).toString();
                if (!transport.equals("")) {
                    System.out.println(transport);
                }
        } else {
            throw new EmptyTransportException("Нечего показывать. Парк пустой");
        }
    }

    /**
     * проверка данных
     * @param price цена
     * @param numberOfSeats количество мест
     * @param fuelConsumption расход топлива
     */
    private static void validateData(int price, int numberOfSeats, int fuelConsumption) throws IncorrectInputDataException {
        if (!isValidPrice(price)) {
            throw new IncorrectInputDataException(PRICE_EXCEPTION);
        } else if (!isValidNumberOfSeats(numberOfSeats)) {
            throw new IncorrectInputDataException(SEATS_EXCEPTION);
        } else if (!isValidFuelConsumption(fuelConsumption)) {
            throw new IncorrectInputDataException(FUEL_EXCEPTION);
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
