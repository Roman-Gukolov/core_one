package com.epam.jdbc.intro.dao;

import com.epam.jdbc.intro.entity.Friendships;

import java.sql.Connection;
import java.util.List;

public interface FriendshipsDao {

    public void addFriendship(Connection connection, Friendships friendships);

    public List<Integer> getFriendships(Connection connection, int userId);

    public void deleteFriendships(Connection connection, int userIdFirst, int userIdSecond);

    public void printAllSp(Connection connection);
}
