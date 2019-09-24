package com.epam.JavaCoreOne.functionalInterface;

@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}
