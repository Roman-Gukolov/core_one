package com.epam.jdbc.intro.dao;

import com.epam.jdbc.intro.entity.Post;

import java.sql.Connection;

public interface PostDao {

    /**
     * Добавление пост.
     */
    public void addPost(Connection connection, Post post);

    /**
     * Получить id поста.
     */
    public int getPostId(Connection connection, Post post);

    /**
     * Получить данные поста.
     */
    public Post getPost(Connection connection, int postId);

    /**
     * Удалить пост.
     */
    public void deletePost(Connection connection, int postId);

    /**
     * Обновить пост.
     */
    public void updatePost(Connection connection, Post post);
}
