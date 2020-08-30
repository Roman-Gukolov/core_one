package com.epam.jdbc.basics.logic;

import com.epam.jdbc.basics.SqlProcessor;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.Collator;
import java.util.List;
import java.util.Locale;


public class DatabaseCopier {
    private static final Logger logger = Logger.getLogger(DatabaseCopier.class);

    private static SqlProcessor sqlProcessor;
    private static DatabaseCopier databaseCopier;
    private String databaseNameFrom;
    private String databaseNameTo;

    public DatabaseCopier(String databaseFrom, String databaseTo) {
        databaseNameFrom = databaseFrom;
        databaseNameTo = databaseTo;
    }

    public static void main(String[] args) {
        init();
        databaseCopier.copy();
    }

    private static void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        sqlProcessor = context.getBean("sqlProcessor", SqlProcessor.class);
        databaseCopier = context.getBean("databaseCopier", DatabaseCopier.class);
    }

    private void copy() {
        try {
            List<String> copiedTables = sqlProcessor.getAllTables(databaseNameFrom);
            copiedTables.sort(Collator.getInstance(new Locale("en")));

            List<String> baseForCopy = sqlProcessor.getAllTables(databaseNameTo);
            for (String tableName: copiedTables) {
                if (baseForCopy.contains(tableName)) {
                    logger.warn(String.format("Ошибка копирвоания таблицы %s: Таблица с таким названием уже" +
                            "существует.", tableName));
                    continue;
                }
                sqlProcessor.copyTable(tableName, databaseNameFrom, databaseNameTo);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
