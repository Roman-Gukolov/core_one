package com.epam.jdbc;

import com.epam.jdbc.Entity.Library;

import java.util.List;

public interface Dao {

    List<Library> getAll(ConnectionPool con);

    int getRowCount(ConnectionPool con);

    void add(Library library, ConnectionPool con);

    void update(Library library, ConnectionPool con);

    Library searchById(long id, ConnectionPool con);
    List<Library> searchByBookName(String bookName, ConnectionPool con);
    List<Library> searchByAuthor(String author, ConnectionPool con);
    List<Library> searchByYear(int year, ConnectionPool con);
    List<Library> searchByShelfNumber(long number, ConnectionPool con);
    List<Library> searchByBookNameAndAuthor(String bookName, String author, ConnectionPool con);

    void deleteById(long id, ConnectionPool con);
    void deleteByBookNameAndAuthor(String bookName, String author, ConnectionPool con);
}
