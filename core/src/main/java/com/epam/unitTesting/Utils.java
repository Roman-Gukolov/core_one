package com.epam.unitTesting;

import org.apache.log4j.Logger;

import java.text.Normalizer;

/**
 * Utils
 *
 * @author Roman Gukolov
 */
public class Utils {
    private static final Logger logger = Logger.getLogger(Utils.class);

    /**
     * Сложение строк
     * @param s1 строка 1
     * @param s2 строка 2
     * @return строка, после сложения
     */
    public String concatenateWords(String s1, String s2) {
        String result = s1.concat(s2);
        logger.info("Результат: " + result);
        return result;
    }

    /**
     * вычисление факториала
     * @return результат
     */
    public long computeFactorial(int number) {
        long result = 1;
        int i = 0;
        if (number < 0) {
            throw new IllegalArgumentException("Значение должно быть больше 0");
        }

        while (i < number) {
            i++;
            result = result * i;
        }
        logger.info("Результат: " + result);
        return result;
    }

    /**
     * Нормализация строки
     * @return результат
     */
    public String normalizeWord(String word) {
        return Normalizer.normalize(word, Normalizer.Form.NFD);
    }

}
