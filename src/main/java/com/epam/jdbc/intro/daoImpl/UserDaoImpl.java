package com.epam.jdbc.intro.daoImpl;

import com.epam.jdbc.intro.dao.UserDao;
import com.epam.jdbc.intro.entity.User;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = Logger.getRootLogger();

    @Override
    public void addUser(Connection connection, User user) {
        String sql = "CALL add_user(?, ?, ?)";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1, user.getName());
            cs.setString(2, user.getSurName());
            cs.setString(3, DateFormatUtils.format(user.getBirthday(), "yyyy-MM-dd HH:mm:ss"));
            cs.execute();
            logger.info("Пользователь добавлен");
        } catch (SQLException e) {
            logger.error("При выполнении запроса произошла ошибка: " + e.getMessage());
        }
    }

    @Override
    public int getUserId(Connection connection, User user) {
        String sql = "CALL get_userid(?, ?, ?)";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1, user.getName());
            cs.setString(2, user.getSurName());
            cs.setString(3, DateFormatUtils.format(user.getBirthday(), "yyyy-MM-dd HH:mm:ss"));

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    logger.info("Найден id пользователя: " + id);
                    return id;
                }
            }
        } catch (SQLException e) {
            logger.error("При выполнении запроса произошла ошибка: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public void deleteUser(Connection connection, int userId) {
        String sql = "CALL delete_user(?)";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, userId);
            cs.execute();
            logger.info("Пользователь удален");
        } catch (SQLException e) {
            logger.error("При выполнении запроса произошла ошибка: " + e.getMessage());
        }
    }

    @Override
    public void updateUser(Connection connection, User user) {
        String sql = "CALL update_user(?, ?, ?, ?)";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, user.getUserId());
            cs.setString(2, user.getName());
            cs.setString(3, user.getSurName());
            cs.setString(4, DateFormatUtils.format(user.getBirthday(), "yyyy-MM-dd HH:mm:ss"));
            cs.execute();
            logger.info("Данные обновлены успешно");
        } catch (SQLException e) {
            logger.error("При выполнении запроса произошла ошибка: " + e.getMessage());
        }
    }

    @Override
    public User getUser(Connection connection, int userId) {
        String sql = "SELECT name, surname, birthdate FROM users where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setUserId(userId);
                    user.setName(rs.getString("name"));
                    user.setSurName(rs.getString("surname"));
                    user.setBirthday(rs.getDate("birthdate"));
                    return user;
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return null;
    }
}
