package com.epam.jdbc.intro.daoImpl;

import com.epam.jdbc.intro.dao.Dao;
import org.apache.log4j.Logger;

import java.sql.*;

public class DaoImpl implements Dao {
    private static final Logger logger = Logger.getRootLogger();

    @Override
    public void createTable(Connection connection, String tableName) {
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s(id INT NOT NULL AUTO_INCREMENT, name VARCHAR(25), PRIMARY KEY(id) )", tableName);
        logger.info(sql);
        try (Statement st = connection.createStatement()) {
            st.execute(sql);
            logger.info(String.format("Таблица \"%s\" успешно создана.", tableName));
        } catch (SQLException e) {
            logger.error("При создании новой таблицы произошла ошибка: " + e.getMessage());
        }
    }

    @Override
    public void dropTable(Connection connection, String tableName) {
        String sql = String.format("DROP TABLE %s", tableName);
        logger.info(sql);
        try(Statement st = connection.createStatement()) {
            st.execute(sql);
            logger.info(String.format("Таблица \"%s\" успешно удалена.", tableName));
        } catch (SQLException e) {
            logger.error("При удалении таблицы произошла ошибка: " + e.getMessage());
        }
    }

    @Override
    public void printAllTables(Connection connection) {
        String sql = "SELECT TABLE_NAME FROM information_schema.tables where table_type = 'BASE TABLE' and table_schema='jdbcintro'";
        logger.info(sql);
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                logger.info(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            logger.error("При выполнении запроса произошла ошибка: " + e.getMessage());
        }
    }

    @Override
    public void insertValue(Connection connection, String value, String comment) {
        String sql = "insert into table1(value, comment) values (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, value);
            ps.setString(2, comment);
            ps.executeUpdate();
            logger.info("Данные добавлены");
        } catch (SQLException e) {
            logger.error("При выполнении запроса произошла ошибка: " + e.getMessage());
        }
    }

    public void getPopulateResult(Connection connection) {
        String sql = "select users.name, users.surname \n" +
                "from users \n" +
                "where users.id in \n" +
                "(\n" +
                "\tselect posts.userid \n" +
                "    from posts \n" +
                "    where posts.id in \n" +
                "    (\n" +
                "\t\tSELECT count(*) as count \n" +
                "        FROM jdbcintro.likes \n" +
                "        group by postid \n" +
                "        having count > 100\n" +
                "    )\n" +
                ") \n" +
                "and users.id in \n" +
                "(\n" +
                "\tselect userid1 \n" +
                "    from friendships \n" +
                "    group by friendships.userid1 \n" +
                "    having count(*) > 100\n" +
                ") \n" +
                "group by name;";
        logger.info(sql);
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                logger.info(rs.getString("name") + rs.getString("surname"));
            }
        } catch (SQLException e) {
            logger.error("При выполнении запроса произошла ошибка: " + e.getMessage());
        }
    }
}
