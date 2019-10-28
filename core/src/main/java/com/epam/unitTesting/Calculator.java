package com.epam.unitTesting;

import org.apache.log4j.Logger;

/**
 * Simple calculator
 *
 * @author Roman Gukolov
 */
public class Calculator {

    private static final Logger logger = Logger.getLogger(Calculator.class);
    private int first;
    private int second;
    private int[] fibArray;
    private int fibSize;

    /**
     * Сложение
     */
    public Double add(Double value1, Double value2) {
        Double result = value1 + value2;
        logger.info("Результат: " + result);
        return result;
    }

    /**
     * Вычитание
     */
    public Double subtract(Double value1, Double value2) {
        Double result = value1 - value2;
        logger.info("Результат: " + result);
        return result;
    }

    /**
     * Умножение
     */
    public Double multiply(Double value1, Double value2) {
        Double result = value1 * value2;
        logger.info("Результат: " + result);
        return result;
    }

    /**
     * Деление
     */
    public Double division(Double value1, Double value2) {
        if (value2 == 0) {
            throw new IllegalArgumentException("значение не должно быть 0");
        }
        if (value1 == 0) {
            return 0d;
        }

        Double result = value1 / value2;
        logger.info("Результат: " + result);
        return result;
    }

    /**
     * Корень
     */
    public Double root(Double value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        }

        Double result = Math.sqrt(value);
        logger.info("Результат: " + result);
        return result;
    }

    /**
     * Степень
     */
    public Double pow(Double value1, Double value2) {
        Double result = Math.pow(value1, value2);
        logger.info("Результат: " + result);
        return result;
    }

    /**
     * Простое число
     */
    public boolean isPrime(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException();
        }
        if (num == 2) return true;
        if (num % 2 == 0)
            return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0) return false;
        return true;
    }

    /**
     * Ряд чисел фибоначчи до указанного индекса
     * @return числа
     */
    public int[] fib(int count){
        fibArray = new int[count];
        first = 1;
        second = 1;
        fibArray[0] = first;
        fibArray[1] = second;
        fibSize = 2;
        this.calculateFib(count - 2);
        return fibArray;
    }

    private void calculateFib(int count) {
        if (count > 0){
            int n3 = first + second;
            first = second;
            second = n3;
            fibArray[fibSize] = n3;
            fibSize++;
            calculateFib(count - 1);
        }
    }
}
