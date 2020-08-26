package com.epam.jdbc.basics;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class JdbcBasics {

    private static final Logger logger = Logger.getLogger(JdbcBasics.class);
    private static SqlProcessor sqlProcessor;
    private static final int MY_THREADS = 10;

    public static void main(String[] args) {
        init();

//        concurrent connection demo(without filling rows)
//        createTablesConcurrently();

        createTables();
    }

    private static void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        sqlProcessor = context.getBean("sqlProcessor", SqlProcessor.class);
    }

    private static void createTables() {
        try {
            sqlProcessor.createTables();
            processRows();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private static void createTablesConcurrently() {
        ExecutorService executor = Executors.newFixedThreadPool(MY_THREADS);
        for (int i = 0; i < MY_THREADS; i++) {
            Runnable runnable = () -> {
                try {
                    sqlProcessor.createTables();
                } catch (Exception e) {
                    logger.error(e);
                }
            };
            executor.execute(runnable);
        }

        executor.shutdown();

//        Wait until all threads are finish
        while (!executor.isTerminated()) {
        }
        logger.info("Finished all threads");
    }

    private static void processRows() {
        try {
            Scanner input = new Scanner(System.in);
            logger.info("Введите название таблицы");
            String tableName = input.nextLine();
            logger.info("Введите размер массива для формарования вариантов количества строк");
            int size = input.nextInt();

            int[] countRows = new int[size];
            for (int i = 0; i < size; i++) {
                countRows[i] = RandomUtils.nextInt(1, 7);
            }

            logger.info("Создан массив: " + ArrayUtils.toString(countRows));
            logger.info("Укажите индекс выбранного значения количества строк");
            int index = input.nextInt();

            sqlProcessor.createRows(tableName, countRows[index]);
        } catch (Exception e) {
            String message = "Произошла ошибка при попытке заполнения таблицы.";
            logger.error(message, e);
        }
    }
}
