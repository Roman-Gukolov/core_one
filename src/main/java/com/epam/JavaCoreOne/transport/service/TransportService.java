package com.epam.JavaCoreOne.transport.service;

import com.epam.JavaCoreOne.PublicTransportPark;
import com.epam.JavaCoreOne.annotation.annotations.InitThisObject;
import com.epam.JavaCoreOne.annotation.annotations.ThisCodeSmells;
import com.epam.JavaCoreOne.common.BaseTransport;
import com.epam.JavaCoreOne.exceprion.EmptyTransportException;
import com.epam.JavaCoreOne.exceprion.RepositoryExceptions;
import com.epam.JavaCoreOne.exceprion.UndefinedTransportIdException;
import com.epam.JavaCoreOne.transport.repository.TransportRepository;
import com.epam.JavaCoreOne.transport.util.MainCommand;
import com.epam.JavaCoreOne.transport.util.TransportUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@ThisCodeSmells(reviewer = "Petya")
public class TransportService {

    @InitThisObject(classPath = "com.epam.JavaCoreOne.transport.repository.TransportRepository")
    private TransportRepository<BaseTransport> transportPark;
    private Scanner input;
    private int totalPrice;

    public TransportService() {
        try {
            init();
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка инициализации парка");
        }
    }

    private void init() throws ClassNotFoundException {
        transportPark = (TransportRepository<BaseTransport>) tryInit("transportPark", 5);
        if (transportPark == null) {
            throw new ClassNotFoundException("Ошибка инициализации парка");
        }
    }

    private Object tryInit(String fieldName, Object param) {
        try {
            Field field = TransportService.class.getDeclaredField(fieldName);
            if (field.isAnnotationPresent(InitThisObject.class)) {
                InitThisObject annotationInfo = field.getAnnotation(InitThisObject.class);
                Class<?> clazz = Class.forName(annotationInfo.classPath());
                return clazz.getDeclaredConstructor(int.class).newInstance(param);
            }
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | NoSuchFieldException
                | IllegalAccessException | ClassNotFoundException e) {
            System.out.println("Ошибка инициализации: " + e);
        }
        return null;
    }

    /**
     * Создание транспорта и добавление в парк
     */
    public void inputData(String command) {
        try {
            input = new Scanner(System.in);
            System.out.println("Введите стоимость: ");
            int price = input.nextInt();

            System.out.println("Введите количество мест в транспорте: ");
            int numberOfSeat = input.nextInt();

            System.out.println("Введите расход топлива транспорта: ");
            int fuelConsumption = input.nextInt();

            switch (MainCommand.valueOf(command)) {
                case Автобус: {
                    addIToPark(TransportUtil.createBus(price, numberOfSeat, fuelConsumption));
                    break;
                }

                case Такси: {
                    addIToPark(TransportUtil.createTaxi(price, numberOfSeat, fuelConsumption));
                    break;
                }

                case Поезд: {
                    addIToPark(TransportUtil.createUnderground(price, numberOfSeat, fuelConsumption));
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Данные введены некорректно.");
        }
    }

    /**
     * Добавление в парк
     */
    private void addIToPark(BaseTransport item) {
        try {
            transportPark.add(item);
            System.out.println("Новый транспорт добавлен в ваш парк");
        } catch (EmptyTransportException e) {
            System.out.println("TRANSPORT добавлен не был:");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Отображение парка
     */
    public void showPark() {
        try {
            TransportUtil.printTransports(transportPark);
        } catch (EmptyTransportException e) {
            System.out.println("Парк пустой");
        }
    }

    /**
     * Отображение парка
     */
    private void showPark(TransportRepository<BaseTransport> park) {
        try {
            TransportUtil.printTransports(park);
        } catch (EmptyTransportException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Поиск транспорта
     */
    public void findTransport() {
        try {
            input = new Scanner(System.in);
            System.out.println("Введите id транспорта который хотите найти");
            int id = input.nextInt();

            System.out.println(transportPark.getById(id).toString());
        } catch (InputMismatchException e) {
            System.out.println("Некорректный id.");
        }
    }

    /**
     * Удаление транспорта
     */
    public void removeTransport() {
        try {
            input = new Scanner(System.in);
            System.out.println("Введите id транспорта который хотите удалить");
            int id = input.nextInt();

            transportPark.delete(id);
            System.out.println("TRANSPORT удален успешно");
        } catch (UndefinedTransportIdException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Некорректный id.");
        }
    }

    /**
     * Сортировка транспорта
     */
    public void sortPark() {
        this.sortParkTransport();
        showPark();
    }

    /**
     * Сортировка транспорта
     * @deprecated устаревший метод. @see {@link
     * com.epam.JavaCoreOne.transport.service.TransportService#sortPark}
     */
    @Deprecated
    public void sortingPark() {
        this.sortParkTransport();
    }

    /**
     * Получение общей стоимости парка
     */
    public void getParkPrice() {
        System.out.println(this.getAllTransportPrice(transportPark));
    }

    /**
     * Поиск транспорта в парке по указанному диапазону цен
     */
    public void findByRangePrice() {
        try {
            input = new Scanner(System.in);
            System.out.println("Введите минимальную цену транспорта");
            int startPrice = input.nextInt();

            System.out.println("Введите максимальную цену транспорта");
            int endPrice = input.nextInt();

            showPark(transportPark.findByPrice(startPrice, endPrice));
        } catch (EmptyTransportException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Некорректно указана цена.");
        }
    }

    /**
     * Поиск транспорта по указанному диапазону количества мест
     */
    public void findByRangeSeats() {
        try {
            input = new Scanner(System.in);
            System.out.println("Введите минимальное количество мест");
            int starSeats = input.nextInt();

            System.out.println("Введите максимальное количество мест");
            int endSeats = input.nextInt();

            showPark(transportPark.findBySeats(starSeats, endSeats));
        } catch (EmptyTransportException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Некорректно введено количество мест.");
        }
    }

    /**
     * Получить полную стоимость автопарка
     * @param transportRepository список транспорта
     * @return полная стоимсоть
     */
    private int getAllTransportPrice(TransportRepository<BaseTransport> transportRepository) {
        for (int i = 0; i <= TransportUtil.COUNT_TRANSPORT; i++) {
            if (transportRepository.getById(i) != null) {
                this.totalPrice += transportRepository.getById(i).getPrice();
            }
        }
        return this.totalPrice;
    }

    /**
     * Сортировка парка транспорта
     */
    private void sortParkTransport() {
        try {
            List<BaseTransport> list = new ArrayList<>();
            for (int i = 0; i <= TransportUtil.COUNT_TRANSPORT; i++) {
                if (transportPark.getById(i) != null) {
                    list.add(transportPark.getById(i));
                }
            }
            list.sort(Comparator.comparingInt(BaseTransport::getFuelConsumption));

            if (!list.isEmpty()) {
                transportPark.clear();
                for (BaseTransport transport : list) {
                    transportPark.add(transport);
                }
            }
        } catch (RepositoryExceptions e) {
            System.out.println(e.getMessage());
        }
    }
}
