package com.epam.jdbc.intro.daoImpl;

import com.epam.jdbc.intro.dao.LikesDao;
import com.epam.jdbc.intro.entity.Like;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LikesDaoImpl implements LikesDao {
    private static final Logger logger = Logger.getRootLogger();

    @Override
    public void addLike(Connection connection, Like like) {
        String sql = "CALL add_like(?, ?, ?)";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, like.getPostId());
            cs.setInt(2, like.getUserId());
            cs.setTimestamp(3, new Timestamp(like.getDate().getTime()));
            cs.execute();
            logger.info("Операция выполнена успешно");
        } catch (SQLException e) {
            logger.error("При выполнении запроса произошла ошибка: " + e.getMessage());
        }
    }

    @Override
    public List<Integer> getLikes(Connection connection, int postId) {
        String sql = "CALL get_like_by_post(?)";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, postId);
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
    public void deleteLike(Connection connection, int userId, int postId) {
        String sql = "CALL delete_like_by_user(?, ?)";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, userId);
            cs.setInt(2, postId);
            cs.execute();
            logger.info("Операция выполнена успешно");
        } catch (SQLException e) {
            logger.error("При выполнении запроса произошла ошибка: " + e.getMessage());
        }
    }
}
