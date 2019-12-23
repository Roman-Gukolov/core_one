package com.epam.spring.basic.util;

import com.epam.spring.basic.factory.RandomCoeff;
import org.springframework.beans.factory.FactoryBean;

public class ServiceLocator implements FactoryBean<RandomCoeff> {

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
