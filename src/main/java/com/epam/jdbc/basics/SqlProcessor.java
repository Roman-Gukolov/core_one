package com.epam.jdbc.basics;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

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
                logger.info(String.format("Execute sql query [%s]", query));
                jdbcTemplate.execute(query);
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
                logger.info(String.format("Execute sql query [%s]", query));
                jdbcTemplate.execute(query);
            }
        } catch (Exception e) {
            String message = "Произошла ошибка при выполнении sql запроса";
            logger.error(message, e);
            throw new Exception(message, e);
        }
    }
}
