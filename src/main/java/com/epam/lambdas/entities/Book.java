package com.epam.lambdas.entities;

import java.util.List;

public class Book {
    private String title;
    private int numberOfPage;
    private List<Author> authors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

//    @Override
//    public String toString() {
//        return "Book{" +
//                        "title=" +book.getTitle() +
//                ", numberOfPage=" + numberOfPage +
//                ", authors=" + authors +
//                "}";
//    }
}
