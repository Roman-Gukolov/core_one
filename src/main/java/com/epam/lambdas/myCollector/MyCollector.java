package com.epam.lambdas.myCollector;

import com.epam.lambdas.entities.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MyCollector implements Collector<A, ArrayList<A>, ArrayList<A>> {

    @Override
    public Supplier<ArrayList<A>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<ArrayList<A>, A> accumulator() {
        return ArrayList::add;
    }

    @Override
    public BinaryOperator<ArrayList<A>> combiner() {
        return (l, r) -> { l.addAll(r); return l; };
    }

    @Override
    public Function<ArrayList<A>, ArrayList<A>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }

}
