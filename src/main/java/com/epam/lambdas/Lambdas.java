package com.epam.lambdas;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Lambdas {
    private final static Logger logger = Logger.getLogger(Lambdas.class);

    private static Person firstPeople = new Person();
    private static Person secondPeople = new Person();
    private static Person lastPeople = new Person();
    private static List<Person> persons = new ArrayList<>();

    public static void main(String[] args) {
        createPerson();

        logger.info("Сортировка по имени: ");
        persons.stream()
                .sorted(Comparator.comparing(Person::getName))
                .forEach(logger::info);

        logger.info("Сортировка по возрасту: ");
        persons.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .forEach(logger::info);

    }

    private static void createPerson() {
        firstPeople.setName("Alex");
        firstPeople.setAge(20);

        secondPeople.setName("Andrey");
        secondPeople.setAge(26);

        lastPeople.setName("Denis");
        lastPeople.setAge(25);

        persons.add(secondPeople);
        persons.add(lastPeople);
        persons.add(firstPeople);

        logger.info("Исходный список: ");
        persons.forEach(logger::info);
    }
}
