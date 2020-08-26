package com.epam.jdbc.basics;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.math3.util.Precision;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SqlProcessor {
    private static final Logger logger = Logger.getLogger(SqlProcessor.class);

    private JdbcTemplate jdbcTemplate;
    private static Map<String, Map<Integer, String>> tables = new HashMap<>();

    public SqlProcessor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTables() throws Exception {
        try {
            generateRandomTables();
            logger.info("created tables:");
            logger.info(tables);
        } catch (Exception e) {
            String message = "Произошла ошибка при выполнении sql запроса";
            logger.error(message, e);
            throw new Exception(message, e);
        }
    }

    public void createRows(String tableName, int rowsCount) throws Exception {
        try {
            for (int i = 0; i < rowsCount; i++) {
                String query = generateRandomRows(tableName);
                logger.info(String.format("Execute sql query [%s]", query));
                jdbcTemplate.execute(query);
            }
        } catch (Exception e) {
            String message = "Произошла ошибка при выполнении sql запроса";
            logger.error(message, e);
            throw new Exception(message, e);
        }
    }

    private void generateRandomTables() {
        int tablesCount = getRandomCount();

        for (int i = 1; i <= tablesCount; i++) {
            String query = generateTableQuery(getTableName());
            logger.info(String.format("Execute sql query [%s]", query));
            jdbcTemplate.execute(query);
        }
    }

    private String generateRandomRows(String tableName) throws Exception {
        StringBuilder rq = new StringBuilder("INSERT INTO ");
        rq.append(tableName);
        rq.append(" (");

        Map<Integer, String> cols = tables.get(tableName);
        for (int i = 1; i <= cols.size(); i++) {
            rq.append(String.format("col%d, ", i));
        }
        rq.setLength(rq.length() - 2);

        rq.append(") VALUES (");
        for (int i = 1; i <= cols.size(); i++) {
            rq.append(generateRowPart(cols.get(i)));
            rq.append(",");
        }

        rq.setLength(rq.length() - 2);
        rq.append(");");
        return rq.toString();
    }

    private String generateTableQuery(String name) {
        int columnCount = getRandomCount();
        Map<Integer, String> cols = new HashMap<>();
        StringBuilder rq = new StringBuilder("CREATE TABLE ");
        rq.append(name);
        rq.append(" (");

        for (int i = 1; i <= columnCount; i++) {
            String type = getType();
            cols.put(i, type);
            rq.append(generateColumnQueryPart(getColumnName(i), type));
        }
        rq.setLength(rq.length() - 2);
        rq.append(");");

        tables.put(name, cols);
        return rq.toString();
    }
    
    private String generateRowPart(String dataType) throws Exception {
        switch (dataType) {
            case "decimal": {
                return String.valueOf(Precision.round(RandomUtils.nextDouble(1, 1000), 2));
            }
            case "varchar": {
                return "some string";
            }
            case "int": {
                return String.valueOf(RandomUtils.nextInt(1, 1000));
            }
            default: {
                logger.info("Неизвестный тип данных");
                throw new Exception("Неизвестный тип данных");
            }
        }
    }

    private String generateColumnQueryPart(String column, String type) {
        return String.format("%s %s, ", column, type);
    }

    private static int getRandomCount() {
        return RandomUtils.nextInt(1, 7);
    }

    private String getTableName() {
        return "Table" + "_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString().replaceAll("-", "_");
    }

    private String getColumnName(int index) {
        return "col" + index;
    }

    private String getType() {
        switch (RandomUtils.nextInt(1, 3)) {
            case 2: {
                return "decimal";
            }
            case 3: {
                return "varchar";
            }
            default: {
                return "int";
            }
        }
    }
}
