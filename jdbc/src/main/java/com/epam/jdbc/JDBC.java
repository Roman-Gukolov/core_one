package com.epam.jdbc;

/**
 * Работа с БД
 *
 * @author Roman Gukolov
 */
public class JDBC {

    public static void main(String[] args) {
        ConnectionPool connection = new ConnectionPool();
        Client client = new Client(connection);
        client.start();
    }
}
