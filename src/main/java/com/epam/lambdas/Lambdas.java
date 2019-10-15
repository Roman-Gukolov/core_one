package com.epam.lambdas;

import com.epam.lambdas.entities.Author;
import com.epam.lambdas.entities.Book;
import com.epam.lambdas.entities.Person;
import com.epam.lambdas.handlers.MainCommand;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.epam.lambdas.handlers.LambdasHandler.*;
import static com.epam.lambdas.handlers.MainCommand.HELP_TEXT;

public class Lambdas {
    private final static Logger logger = Logger.getLogger(Lambdas.class);
    private static Scanner input = new Scanner(System.in);

    private static List<Person> persons = new ArrayList<>();
    private static Author[] authors = new Author[3];
    private static Book[] books = new Book[3];

    public static void main(String[] args) {
        init();
        start();
    }

    private static void start() {
        boolean work = true;
        String command;
        logger.info(HELP_TEXT);

        while (work) {
            try {
                logger.info("Введите команду");
                command = input.nextLine();
                switch (MainCommand.valueOf(command)) {
                    case sortPerson: {
                        sort(persons);
                        break;
                    }
                    case funcInterfaces: {
                        runFuncInterfaces();
                        break;
                    }
                    case calculate: {
                        calculate();
                        break;
                    }
                    case authors: {
                        printAuthors(authors);
                        break;
                    }
                    case books: {
                        printBooks(books);
                        break;
                    }
                    case checkPages: {
                        checkPages(books);
                        break;
                    }
                    case minMax: {
                        findMinAndMaxPages(books);
                        break;
                    }
                    case singleAuthors: {
                        findBooksWithSingleAuthors(books);
                        break;
                    }
                    case sortBooks: {
                        sortBooks(books);
                        break;
                    }
                    case titles: {
                        List<String> titles = getListOfTitles(books);
                        titles.forEach(logger::info);
                        break;
                    }
                    case distinctAuthors: {
                        List<Author> authors = getDistinctAuthors(books);
                        logger.info("Уникальные авторы: ");
                        authors.forEach(author ->
                                logger.info("Author{" +
                                        "name='" +author.getName() + '\'' +
                                        ", age=" + author.getAge() +
                                        ", books=" + author.getBooks().stream().map(Book::getTitle).collect(Collectors.toList())
                                        + "]"));
                        break;
                    }
                    case help: {
                        logger.info(HELP_TEXT);
                        break;
                    }
                    case exit: {
                        work = false;
                        break;
                    }
                    default: {
                        logger.info("Неизвестная команда");
                        break;
                    }
                }
            } catch (Exception e) {
                logger.info("Произошла ошибка " + e.getMessage());
            }
        }
    }

    private static void init() {
        persons = createPerson();
        authors = createAuthor();
        books = createBooks();
    }
}
