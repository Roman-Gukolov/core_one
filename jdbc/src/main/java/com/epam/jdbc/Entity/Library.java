package com.epam.jdbc.Entity;

import java.sql.Date;

public class Library {

    private long id;
    private long shelfNumber;
    private String bookName;
    private String author;
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
