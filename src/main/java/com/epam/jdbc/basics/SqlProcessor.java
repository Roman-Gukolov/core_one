package com.epam.jdbc.basics;

import com.epam.jdbc.basics.logic.TablesCreator;
import com.epam.jdbc.basics.mapper.TableNamesRowMapper;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SqlProcessor {
    private static final Logger logger = Logger.getLogger(SqlProcessor.class);

    private JdbcTemplate jdbcTemplate;
    private TablesCreator tablesCreator = new TablesCreator();

    public SqlProcessor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTables() throws Exception {
        try {
            int tablesCount = tablesCreator.getRandomCount();

            for (int i = 1; i <= tablesCount; i++) {
                String query = tablesCreator.generateTableQuery();
                executeSql(query);
            }

            logger.info("created tables:");
            logger.info(tablesCreator.getTables());
        } catch (Exception e) {
            String message = "Произошла ошибка при выполнении sql запроса";
            logger.error(message, e);
            throw new Exception(message, e);
        }
    }

    public void createRows(String tableName, int rowsCount) throws Exception {
        try {
            for (int i = 0; i < rowsCount; i++) {
                String query = tablesCreator.generateRandomRows(tableName);
                executeSql(query);
            }
        } catch (Exception e) {
            String message = "Произошла ошибка при выполнении sql запроса";
            logger.error(message, e);
            throw new Exception(message, e);
        }
    }

    public List<String> getAllTables(String databaseName) throws Exception {
        try {
            String sql = String.format("SELECT TABLE_NAME FROM information_schema.tables where table_type = "+
                    "'BASE TABLE' and table_schema='%s'", databaseName);

            logger.info(String.format("Execute sql query [%s]", sql));
            return jdbcTemplate.query(sql, new TableNamesRowMapper());
        } catch (Exception e) {
            String message = "Произошла ошибка при выполнении sql запроса";
            logger.error(message, e);
            throw new Exception(message, e);
        }
    }

    public void copyTable(String tableName, String baseFrom, String baseTo) throws Exception {
        try {
            String createSchemaSql = String.format("CREATE SCHEMA IF NOT EXISTS `%s`", baseTo);
            executeSql(createSchemaSql);

            String createNewTableSql = String.format("CREATE TABLE IF NOT EXISTS `%s`.`%s` like `%s`.`%s`", baseTo, tableName, baseFrom,
                    tableName);
            executeSql(createNewTableSql);

            String fillingTableSql = String.format("insert into `%s`.`%s` select * from `%s`.`%s`", baseTo, tableName,
                    baseFrom, tableName);
            executeSql(fillingTableSql);
        } catch (Exception e) {
            String message = "Произошла ошибка при выполнении sql запроса";
            logger.error(message, e);
            throw new Exception(message, e);
        }
    }

    private void executeSql(String sql) {
        logger.info(String.format("Execute sql query [%s]", sql));
        jdbcTemplate.execute(sql);
    }

}
