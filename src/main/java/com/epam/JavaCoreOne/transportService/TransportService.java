package com.epam.JavaCoreOne.transportService;

import com.epam.JavaCoreOne.common.BaseTransport;
import com.epam.JavaCoreOne.exceprionRepository.NullTransportException;
import com.epam.JavaCoreOne.exceprionRepository.RepositoryExceptions;
import com.epam.JavaCoreOne.exceprionRepository.UndefinedTransportIdException;
import com.epam.JavaCoreOne.transportRepository.TransportRepository;
import com.epam.JavaCoreOne.transportUtil.TransportUtil;

import java.util.*;

public class TransportService {
    private TransportRepository<BaseTransport> transportPark = new TransportRepository<>(5);
    private Scanner input;
    private int totalPrice;

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

            switch (command) {
                case "Автобус": {
                    addIToPark(TransportUtil.createBus(price, numberOfSeat, fuelConsumption));
                    break;
                }

                case "Такси": {
                    addIToPark(TransportUtil.createTaxi(price, numberOfSeat, fuelConsumption));
                    break;
                }

                case "Поезд": {
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
        } catch (NullTransportException e) {
            System.out.println("Транспорт добавлен не был:");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Отображение парка
     */
    public void showPark() {
        try {
            TransportUtil.printTransports(transportPark);
        } catch (NullTransportException e) {
            System.out.println("Парк пустой");
        }
    }

    /**
     * Отображение парка
     */
    private void showPark(TransportRepository<BaseTransport> park) {
        try {
            TransportUtil.printTransports(park);
        } catch (NullTransportException e) {
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
            System.out.println("Транспорт удален успешно");
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
        } catch (NullTransportException e) {
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
        } catch (NullTransportException e) {
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
