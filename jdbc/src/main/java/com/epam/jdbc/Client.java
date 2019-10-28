package com.epam.jdbc;

import com.epam.jdbc.Entity.Library;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Класс клиента
 */
public class Client {
    private ConnectionPool connection;
    private Scanner inputCommand = new Scanner(System.in);

    public Client(ConnectionPool conPool) {
        this.connection = conPool;
        printHelp();
    }

    /**
     * Старт работы с бд
     */
    public void start() {
        LibraryFunction libraryFunction = new LibraryFunction();
        Library library;
        while (true) {
            System.out.println("Input command");
            String command = inputCommand.nextLine();
            switch (command) {
                case "getAll": {
                    printResult(libraryFunction.getAll(connection));
                    break;
                }
                case "add":
                    try {
                        library = new Library();
                        long id = libraryFunction.getRowCount(connection) + 1;
                        System.out.println("Input new shelf number");
                        long shelf = Long.valueOf(inputCommand.nextLine());
                        System.out.println("Input new book-name");
                        String book = inputCommand.nextLine();
                        System.out.println("Input new author");
                        String author = inputCommand.nextLine();
                        System.out.println("Input new date release(YYYY-MM-DD)");
                        Date date = Date.valueOf(inputCommand.nextLine());
                        library.setId(id);
                        library.setShelfNumber(shelf);
                        library.setBookName(book);
                        library.setAuthor(author);
                        library.setDateRelease(date);
                        libraryFunction.add(library, connection);
                        System.out.println("add successful");
                    } catch (Exception e) {
                        System.out.println("Error creating value: " + e);
                    }
                    break;
                case "update":
                    try {
                        library = new Library();
                        System.out.println("Input id for update");
                        long id = Long.valueOf(inputCommand.nextLine());
                        System.out.println("Input new shelf number");
                        long shelf = Long.valueOf(inputCommand.nextLine());
                        System.out.println("Input new book-name");
                        String book = inputCommand.nextLine();
                        System.out.println("Input new author");
                        String author = inputCommand.nextLine();
                        System.out.println("Input new date release(YYYY-MM-DD)");
                        Date date = Date.valueOf(inputCommand.nextLine());
                        library.setId(id);
                        library.setShelfNumber(shelf);
                        library.setBookName(book);
                        library.setAuthor(author);
                        library.setDateRelease(date);
                        libraryFunction.update(library, connection);
                        System.out.println("Update successful");
                    } catch (Exception e) {
                        System.out.println("Error updating: " + e);
                    }
                    break;
                case "findID": {
                    System.out.println("Input id");
                    long id = Long.valueOf(inputCommand.nextLine());
                    System.out.println(libraryFunction.searchById(id, connection));
                    break;
                }
                case "findBOOK": {
                    System.out.println("Input book-name");
                    String book = inputCommand.nextLine();
                    printResult(libraryFunction.searchByBookName(book, connection));
                    break;
                }
                case "findAuthor": {
                    System.out.println("Input author");
                    String author = inputCommand.nextLine();
                    printResult(libraryFunction.searchByAuthor(author, connection));
                    break;
                }
                case "findYear": {
                    System.out.println("Input year");
                    int year = Integer.valueOf(inputCommand.nextLine());
                    printResult(libraryFunction.searchByYear(year, connection));
                    break;
                }
                case "findShelf": {
                    System.out.println("Input shelf number");
                    long shelf = Long.valueOf(inputCommand.nextLine());
                    printResult(libraryFunction.searchByShelfNumber(shelf, connection));
                    break;
                }
                case "findBookAndAuthor": {
                    System.out.println("Input book-name");
                    String book = inputCommand.nextLine();
                    System.out.println("Input author");
                    String author = inputCommand.nextLine();
                    printResult(libraryFunction.searchByBookNameAndAuthor(book, author, connection));
                    break;
                }
                case "deleteID": {
                    System.out.println("Input id for delete");
                    long id = Long.valueOf(inputCommand.nextLine());
                    libraryFunction.deleteById(id, connection);
                    break;
                }
                case "deleteBookAndAuthor": {
                    System.out.println("Input book-name");
                    String book = inputCommand.nextLine();
                    System.out.println("Input author");
                    String author = inputCommand.nextLine();
                    libraryFunction.deleteByBookNameAndAuthor(book, author, connection);
                    break;
                }
                case "getRowCount": {
                    System.out.println(libraryFunction.getRowCount(connection));
                    break;
                }
                case "help": {
                    printHelp();
                    break;
                }
                case "exit": {
                    System.out.println("Exiting...");
                    return;
                }
                default: {
                    System.out.println("Unknown commands");
                    break;
                }
            }
        }
    }

    private static void printResult(List<Library> libraryList) {
        for (Library a : libraryList) {
            System.out.println(a);
        }
    }

    private void printHelp() {
        System.out.println("List commands:");
        System.out.println("Input \"getRowCount\" for output number of lines");
        System.out.println("Input \"getAll\" for output table.");
        System.out.println("Input \"add\" for insert statement in table");
        System.out.println("Input \"update\" for update statement in table");
        System.out.println("Input \"findID\" for search values by id");
        System.out.println("Input \"findBOOK\" for search values by book-name");
        System.out.println("Input \"findAuthor\" for search values by author");
        System.out.println("Input \"findYear\" for search values by year of publishing");
        System.out.println("Input \"findShelf\" for search values by shelf number");
        System.out.println("Input \"findBookAndAuthor\" for search values by book-name and author");
        System.out.println("Input \"deleteID\" for delete values by id");
        System.out.println("Input \"deleteBookAndAuthor\" for delete values by book-name and author ");
        System.out.println("Input \"help\" for print list commands");
        System.out.println("Input \"exit\" for exit");
    }
}
