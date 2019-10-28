package com.epam.jdbc.Entity;

import java.sql.Date;

/**
 * Сущность Библиотека для БД
 *
 * @author Roman Gukolov
 */
public class Library {

    /** Id */
    private long id;

    /** Номер полки */
    private long shelfNumber;

    /** Название книги */
    private String bookName;

    /** Автор */
    private String author;

    /** Дата издания */
    private Date dateRelease;

    public Library(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(long shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(Date dateRelease) {
        this.dateRelease = dateRelease;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", shelfNumber=" + shelfNumber +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", dateRelease=" + dateRelease +
                '}';
    }
}
