package com.epam.jdbc.intro.daoImpl;

import com.epam.jdbc.intro.dao.PostDao;
import com.epam.jdbc.intro.entity.Post;
import com.epam.jdbc.intro.entity.User;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

import java.sql.*;

public class PostDaoImpl implements PostDao {
    private static final Logger logger = Logger.getRootLogger();

    @Override
    public void addPost(Connection connection, Post post) {
        String sql = "CALL add_post(?, ?, ?)";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, post.getUserId());
            cs.setString(2, post.getText());
            cs.setTimestamp(3, new Timestamp(post.getDate().getTime()));
            cs.execute();
            logger.info("Пост добавлен");
        } catch (SQLException e) {
            logger.error("При выполнении запроса произошла ошибка: " + e.getMessage());
        }
    }

    @Override
    public int getPostId(Connection connection, Post post) {
        String sql = "CALL get_postid(?, ?, ?)";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, post.getUserId());
            cs.setString(2, post.getText());
            cs.setTimestamp(3, new Timestamp(post.getDate().getTime()));

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    logger.info("Найден id поста: " + id);
                    return id;
                }
            }
        } catch (SQLException e) {
            logger.error("При выполнении запроса произошла ошибка: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public void deletePost(Connection connection, int postId) {
        String sql = "CALL delete_post(?)";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, postId);
            cs.execute();
            logger.info("Пост удален");
        } catch (SQLException e) {
            logger.error("При выполнении запроса произошла ошибка: " + e.getMessage());
        }
    }

    @Override
    public void updatePost(Connection connection, Post post) {
        String sql = "CALL update_post(?, ?, ?, ?)";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, post.getId());
            cs.setInt(2, post.getUserId());
            cs.setString(3, post.getText());
            cs.setTimestamp(4, new Timestamp(post.getDate().getTime()));
            cs.execute();
            logger.info("Данные обновлены успешно");
        } catch (SQLException e) {
            logger.error("При выполнении запроса произошла ошибка: " + e.getMessage());
        }
    }

    @Override
    public Post getPost(Connection connection, int postId) {
        String sql = "SELECT userid, text, timestamp FROM posts where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, postId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Post post = new Post();
                    post.setId(postId);
                    post.setUserId(rs.getInt("userid"));
                    post.setText(rs.getString("text"));
                    post.setDate(rs.getDate("timestamp"));
                    return post;
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return null;
    }
}
