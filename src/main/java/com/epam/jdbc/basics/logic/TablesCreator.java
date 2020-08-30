package com.epam.jdbc.basics.logic;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.math3.util.Precision;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TablesCreator {

    private static final Logger logger = Logger.getLogger(TablesCreator.class);

    private static Map<String, Map<Integer, String>> tables = new HashMap<>();

    public String generateTableQuery() {
        String name = getTableName();
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
    public int getRandomCount() {
        return RandomUtils.nextInt(1, 7);
    }

    public String generateRandomRows(String tableName) throws Exception {
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

    private String getTableName() {
        return "Table" + "_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString().replaceAll("-", "_");
    }

    public Map<String, Map<Integer, String>> getTables() {
        return tables;
    }
}
