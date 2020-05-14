package com.epam.jdbc.intro.dao;

import com.epam.jdbc.intro.entity.User;

import java.sql.Connection;

public interface UserDao {

    /**
     * Добавление пользователя.
     */
    public void addUser(Connection connection, User user);

    /**
     * Получить id пользователя.
     */
    public int getUserId(Connection connection, User user);

    /**
     * Получить данные пользователя.
     */
    public User getUser(Connection connection, int userId);

    /**
     * Удалить пользователя.
     */
    public void deleteUser(Connection connection, int userId);

    /**
     * Обновить данные пользователя.
     */
    public void updateUser(Connection connection, User user);
}
