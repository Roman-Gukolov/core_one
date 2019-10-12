package com.epam.lambdas;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class Lambdas {
    private final static Logger logger = Logger.getLogger(Lambdas.class);

    private static Person firstPeople = new Person();
    private static Person secondPeople = new Person();
    private static Person lastPeople = new Person();
    private static List<Person> persons = new ArrayList<>();

    public static void main(String[] args) {
        createPerson();
        sort();
        runFuncInterfaces();
        calculate();
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

    private static void sort() {
        logger.info("Сортировка по имени: ");
        persons.stream()
                .sorted(Comparator.comparing(Person::getName))
                .forEach(logger::info);

        logger.info("Сортировка по возрасту: ");
        persons.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .forEach(logger::info);
    }

    private static void runFuncInterfaces() {
        Predicate<Integer> isPositive = x -> x > 0;
        BinaryOperator<Integer> multiply = (x, y) -> x * y;
        UnaryOperator<Integer> square = x -> x * x;
        Function<Integer, String> convert = x -> x + " dollars";
        Consumer<Integer> printer = x -> logger.debug("5. printer: " + x + " dollars");
        Supplier<Person> personSupplier = Person::new;

        logger.debug("run functional interfaces");
        logger.debug("1. isPositive \"5\" = " + isPositive.test(5));
        logger.debug("2. multiply 5*5 = " + multiply.apply(5, 5));
        logger.debug("3. square 7 = " + square.apply(7));
        logger.debug("4. convert 500 = " + convert.apply(500));
        printer.accept(123);
        Person tempPerson = personSupplier.get();
        tempPerson.setAge(200);
        tempPerson.setName("This person got from supplier");
        logger.debug("6. personSupplier: " + tempPerson);
    }

    private static void calculate() {
        CalculatePerDiem calculatePerDiem = new CalculatePerDiem();
        Function<Integer, Double> curriedByFirstArgument = calculatePerDiem.curryFirstArgument(57.16);

        logger.info(curriedByFirstArgument.apply(5));
        logger.info(curriedByFirstArgument.apply(3));
        logger.info(curriedByFirstArgument.apply(10));

        Function<Double, Double> curriedBySecondArgument = calculatePerDiem.currySecondArgument(10);
        logger.info(curriedBySecondArgument.apply(56.12));
        logger.info(curriedBySecondArgument.apply(61.63));
    }
}
