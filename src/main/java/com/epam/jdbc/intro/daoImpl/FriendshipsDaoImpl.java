package com.epam.jdbc.intro.daoImpl;

import com.epam.jdbc.intro.dao.FriendshipsDao;
import com.epam.jdbc.intro.entity.Friendships;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FriendshipsDaoImpl implements FriendshipsDao {
    private static final Logger logger = Logger.getRootLogger();

    @Override
    public void addFriendship(Connection connection, Friendships friendships) {
        String sql = "CALL add_friendship(?, ?, ?)";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, friendships.getUserIdFirst());
            cs.setInt(2, friendships.getUserIdSecond());
            cs.setTimestamp(3, new Timestamp(friendships.getDate().getTime()));
            cs.execute();
            logger.info("Операция выполнена успешно");
        } catch (SQLException e) {
            logger.error("При выполнении запроса произошла ошибка: " + e.getMessage());
        }
    }

    @Override
    public List<Integer> getFriendships(Connection connection, int userId) {
        String sql = "CALL get_friendships_by_userid1(?)";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, userId);
            try (ResultSet rs = cs.executeQuery()) {
                List<Integer> friendshipsIds = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    logger.info("Найден id пользователя: " + id);
                    friendshipsIds.add(id);
                }
                return friendshipsIds;
            }
        } catch (SQLException e) {
            logger.error("При выполнении запроса произошла ошибка: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteFriendships(Connection connection, int userIdFirst, int userIdSecond) {
        String sql = "CALL delete_friendships_by_userid(?, ?)";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, userIdFirst);
            cs.setInt(2, userIdSecond);
            cs.execute();
            logger.info("Операция выполнена успешно");
        } catch (SQLException e) {
            logger.error("При выполнении запроса произошла ошибка: " + e.getMessage());
        }
    }

    @Override
    public void printAllSp(Connection connection) {
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SHOW PROCEDURE STATUS WHERE Db = jdbcintro")) {

            while (rs.next()) {
                logger.info(rs.getString("Name"));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
