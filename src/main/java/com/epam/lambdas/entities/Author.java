package com.epam.lambdas.entities;

import java.util.List;

public class Author {
    private String name;
    private short age;
    private List<Book> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

//    @Override
//    public String toString() {
//        return "Author{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                ", books=" + books +
//                '}';
//    }
}
