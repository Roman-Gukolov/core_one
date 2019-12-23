package com.epam.spring.basic.factory;

import org.springframework.beans.factory.FactoryBean;

public class CoeffFactory implements FactoryBean<RandomCoeff> {

    @Override
    public RandomCoeff getObject() throws Exception {
        return new RandomCoeff();
    }

    @Override
    public Class<?> getObjectType() {
        return RandomCoeff.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
