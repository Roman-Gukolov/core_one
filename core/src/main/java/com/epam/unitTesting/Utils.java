package com.epam.unitTesting;

import java.text.Normalizer;

public class Utils {

    /**
     * Сложение строк
     * @param s1 строка 1
     * @param s2 строка 2
     * @return строка, после сложения
     */
    public String concatenateWords(String s1, String s2) {
        return s1.concat(s2);
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
