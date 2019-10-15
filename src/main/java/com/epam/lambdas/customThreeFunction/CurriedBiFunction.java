package com.epam.lambdas.customThreeFunction;

import java.util.function.BiFunction;
import java.util.function.Function;

@FunctionalInterface
interface CurriedBiFunction<T, U, R> extends BiFunction<T, U, R> {
    default Function<U, R> curryFirstArgument(T t) {
        return u -> apply(t, u);
    }

    default Function<T, R> currySecondArgument(U u) {
        return t -> apply(t, u);
    }
}