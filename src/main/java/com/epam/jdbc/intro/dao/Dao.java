package com.epam.jdbc.intro.dao;

import com.epam.jdbc.intro.entity.User;

import java.sql.Connection;

public interface Dao {

    /**
     * Создание таблицы
     */
    public void createTable(Connection connection, String tableName);

    /**
     * Удаление таблицы
     */
    public void dropTable(Connection connection, String tableName);

    /**
     * Распечатать все таблицы
     */
    public void printAllTables(Connection connection);

    /**
     * Добавить запись в таблицу table1
     */
    public void insertValue(Connection connection, String value, String comment);
}
