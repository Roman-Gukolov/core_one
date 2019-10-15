package com.epam.lambdas.handlers;

import com.epam.lambdas.customThreeFunction.CalculatePerDiem;
import com.epam.lambdas.entities.Author;
import com.epam.lambdas.entities.Book;
import com.epam.lambdas.entities.Person;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class LambdasHandler {
    private final static Logger logger = Logger.getLogger(LambdasHandler.class);

    private static Person firstPeople = new Person();
    private static Person secondPeople = new Person();
    private static Person lastPeople = new Person();
    private static List<Person> peoples = new ArrayList<>();
    private static Author[] authors = new Author[3];
    private static Book[] books = new Book[3];

    public static List<Person> createPerson() {
        firstPeople.setName("Alex");
        firstPeople.setAge(20);

        secondPeople.setName("Andrey");
        secondPeople.setAge(26);

        lastPeople.setName("Denis");
        lastPeople.setAge(25);

        peoples.add(secondPeople);
        peoples.add(lastPeople);
        peoples.add(firstPeople);

        logger.info("Исходный лист Person: ");
        peoples.forEach(logger::info);

        return peoples;
    }

    public static void sort(List<Person> persons) {
        logger.info("Сортировка по имени: ");
        persons.stream()
                .sorted(Comparator.comparing(Person::getName))
                .forEach(logger::info);

        logger.info("Сортировка по возрасту: ");
        persons.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .forEach(logger::info);
    }

    public static void runFuncInterfaces() {
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

    public static void calculate() {
        CalculatePerDiem calculatePerDiem = new CalculatePerDiem();
        Function<Integer, Double> curriedByFirstArgument = calculatePerDiem.curryFirstArgument(57.16);

        logger.info("Рассчет расходов при расходах за день 57,16");
        logger.info("Расходы за 5 дней - " + curriedByFirstArgument.apply(5));
        logger.info("Расходы за 3 дня - " + curriedByFirstArgument.apply(3));
        logger.info("Расходы за 10 дней - " + curriedByFirstArgument.apply(10));

        Function<Double, Double> curriedBySecondArgument = calculatePerDiem.currySecondArgument(10);

        logger.info("Рассчет расходов за 10 дней");
        logger.info("Расходы при расходах за 1 день = 56,12 - " + curriedBySecondArgument.apply(56.12));
        logger.info("Расходы при расходах за 1 день = 61,63 - " + curriedBySecondArgument.apply(61.63));
    }

    public static Author[] createAuthor() {
        initArrays();
        return authors;
    }

    public static Book[] createBooks() {
        initArrays();
        return books;
    }

    public static void printAuthors(Author[] arrayOfAuthors) {
        Arrays.stream(arrayOfAuthors).forEach(author ->
                logger.info("Author[" +
                        "name='" +author.getName() + '\'' +
                        ", age=" + author.getAge() +
                        ", books=" + author.getBooks().stream().map(Book::getTitle).collect(Collectors.toList())
                        + "]"));
    }

    public static void printBooks(Book[] arrayOfBooks) {
        Arrays.stream(arrayOfBooks).forEach(book ->
                logger.info("Book[" +
                        "title='" +book.getTitle() + '\'' +
                        ", numberOfPage=" + book.getNumberOfPage() +
                        ", authors=" + book.getAuthors().stream().map(Author::getName).collect(Collectors.toList())
                        + "]"));
    }

    public static void checkPages(Book[] arrayOfBooks) {
        logger.info("Книги, в которых боольше 200 страниц: ");
        Arrays.stream(arrayOfBooks)
                .filter(item -> item.getNumberOfPage() > 200)
                .forEach(book ->
                        logger.info("Book[" +
                                "title='" +book.getTitle() + '\'' +
                                ", numberOfPage=" + book.getNumberOfPage() +
                                ", authors=" + book.getAuthors().stream().map(Author::getName).collect(Collectors.toList())
                                + "]"));
    }

    public static void findMinAndMaxPages(Book[] arrayOfBooks) {
        logger.info("Минимум страниц: " + Arrays.stream(arrayOfBooks)
                .map(Book::getNumberOfPage)
                .mapToInt(i -> i).min().getAsInt());

        logger.info("Максимум страниц: " + Arrays.stream(arrayOfBooks)
                .map(Book::getNumberOfPage)
                .mapToInt(i -> i).max().getAsInt());
    }

    public static void findBooksWithSingleAuthors(Book[] arrayOfBooks) {
        Arrays.stream(arrayOfBooks)
                .filter(item -> item.getAuthors().stream().map(Author::getName).count() == 1)
                .parallel()
                .forEach(book ->
                        logger.info("Book[" +
                                "title='" +book.getTitle() + '\'' +
                                ", numberOfPage=" + book.getNumberOfPage() +
                                ", authors=" + book.getAuthors().stream().map(Author::getName).collect(Collectors.toList())));
    }

    public static void sortBooks(Book[] arrayOfBooks) {
        logger.info("Сортировка по количеству страниц");
        Arrays.stream(arrayOfBooks)
                .sorted(Comparator.comparing(Book::getNumberOfPage))
                .forEach(book ->
                        logger.info("Book[" +
                                "title='" +book.getTitle() + '\'' +
                                ", numberOfPage=" + book.getNumberOfPage() +
                                ", authors=" + book.getAuthors().stream().map(Author::getName).collect(Collectors.toList())
                                + "]"));

        logger.info("Сортировка по наименованию книг");
        Arrays.stream(arrayOfBooks)
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(book ->
                        logger.info("Book[" +
                                "title='" +book.getTitle() + '\'' +
                                ", numberOfPage=" + book.getNumberOfPage() +
                                ", authors=" + book.getAuthors().stream().map(Author::getName).collect(Collectors.toList())
                                + "]"));
    }

    public static List<String> getListOfTitles(Book[] arrayOfBooks) {
        logger.info("getting list of titles...");
        return Arrays.stream(arrayOfBooks)
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    public static List<Author> getDistinctAuthors(Book[] arrayOfBooks) {
        logger.info("getting list of authors...");
        return Arrays.stream(arrayOfBooks)
                .map(Book::getAuthors)
                .flatMap(Collection::stream)
                .peek(item -> logger.info(item.getName()))
                .distinct()
                .collect(Collectors.toList());

    }

    private static void initArrays() {
        List<Book> tempFirstBooks = new ArrayList<>();
        List<Author> tempFirstAuthors = new ArrayList<>();
        Book tempFirstBook = new Book();
        List<Book> tempSecondBooks = new ArrayList<>();
        List<Author> tempSecondAuthors = new ArrayList<>();
        Book tempSecondBook = new Book();
        List<Book> tempThirdBooks = new ArrayList<>();
        List<Author> tempThirdAuthors = new ArrayList<>();
        Book tempThirdBook = new Book();

        authors[0] = new Author();
        authors[0].setName("Джастин Ричардс");
        authors[0].setAge((short) 30);
        authors[1] = new Author();
        authors[1].setName("Джордж Манн");
        authors[1].setAge((short) 20);
        authors[2] = new Author();
        authors[2].setName("Пол Финч");
        authors[2].setAge((short) 35);

        books[0] = new Book();
        books[0].setTitle("Сказания Трензалора");
        books[0].setNumberOfPage(350);
        books[1] = new Book();
        books[1].setTitle("Механизмы войны");
        books[1].setNumberOfPage(470);
        books[2] = new Book();
        books[2].setTitle("Paradox Lost");
        books[2].setNumberOfPage(104);

        tempFirstBook.setTitle(books[0].getTitle());
        tempFirstBook.setNumberOfPage(books[0].getNumberOfPage());
        tempFirstAuthors.add(authors[0]);
        tempFirstAuthors.add(authors[1]);
        tempFirstAuthors.add(authors[2]);
        tempFirstBook.setAuthors(tempFirstAuthors);
        tempFirstBooks.add(tempFirstBook);

        tempSecondBook.setTitle(books[1].getTitle());
        tempSecondBook.setNumberOfPage(books[1].getNumberOfPage());
        tempSecondAuthors.add(authors[1]);
        tempSecondBook.setAuthors(tempSecondAuthors);
        tempSecondBooks.add(tempFirstBook);
        tempSecondBooks.add(tempSecondBook);

        tempThirdBook.setTitle(books[2].getTitle());
        tempThirdBook.setNumberOfPage(books[2].getNumberOfPage());
        tempThirdAuthors.add(authors[1]);
        tempThirdAuthors.add(authors[2]);
        tempThirdBook.setAuthors(tempThirdAuthors);
        tempThirdBooks.add(tempFirstBook);
        tempThirdBooks.add(tempThirdBook);

        tempSecondBooks.add(tempThirdBook);

        authors[0].setBooks(tempFirstBooks);
        books[0].setAuthors(tempFirstAuthors);
        authors[1].setBooks(tempSecondBooks);
        books[1].setAuthors(tempSecondAuthors);
        authors[2].setBooks(tempThirdBooks);
        books[2].setAuthors(tempThirdAuthors);
    }
}
