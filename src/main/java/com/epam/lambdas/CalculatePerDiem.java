package com.epam.lambdas;

import java.util.function.BiFunction;
import java.util.function.Function;

public class CalculatePerDiem implements CurriedBiFunction<Double, Integer, Double> {

    private static final Double perDiemRate = 10.15;

    @Override
    public Double apply(Double dollarExchangeRate, Integer amountOfDays) {
        return dollarExchangeRate * amountOfDays * perDiemRate;
    }
}

@FunctionalInterface
interface CurriedBiFunction<T, U, R> extends BiFunction<T, U, R> {
    default Function<U, R> curryFirstArgument(T t) {
        return u -> apply(t, u);
    }

    default Function<T, R> currySecondArgument(U u) {
        return t -> apply(t, u);
    }
}
