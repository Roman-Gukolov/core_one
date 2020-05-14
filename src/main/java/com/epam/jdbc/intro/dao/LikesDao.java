package com.epam.jdbc.intro.dao;

import com.epam.jdbc.intro.entity.Like;

import java.sql.Connection;
import java.util.List;

public interface LikesDao {

    public void addLike(Connection connection, Like like);

    public List<Integer> getLikes(Connection connection, int postId);

    public void deleteLike(Connection connection, int userId, int postId);
}
