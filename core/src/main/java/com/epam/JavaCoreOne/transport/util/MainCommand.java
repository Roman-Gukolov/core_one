package com.epam.JavaCoreOne.transport.util;

public enum MainCommand {
    Автобус("Автобус"),
    Такси("Такси"),
    Поезд("Поезд"),
    Транспорт("Транспорт"),
    Найти("Найти"),
    Удалить("Удалить"),
    Сортировать("Сортировать"),
    Стоимость("Стоимость"),
    findPrices("findPrices"),
    findSeats("findSeats"),
    help("help"),
    exit("exit");

    private final String value;

    MainCommand(final String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}