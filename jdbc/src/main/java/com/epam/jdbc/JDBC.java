package com.epam.jdbc;

public class JDBC {

    public static void main(String[] args) {
        ConnectionPool connection = new ConnectionPool();
        new Client(connection);
    }
}
