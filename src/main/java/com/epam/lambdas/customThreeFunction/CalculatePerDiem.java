package com.epam.lambdas.customThreeFunction;


public class CalculatePerDiem implements CurriedBiFunction<Double, Integer, Double> {

    /** Коэффициент */
    private static final Double perDiemRate = 10.15;

    @Override
    public Double apply(Double dollarExchangeRate, Integer amountOfDays) {
        return dollarExchangeRate * amountOfDays * perDiemRate;
    }
}


