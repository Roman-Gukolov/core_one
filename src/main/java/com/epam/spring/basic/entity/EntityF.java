package com.epam.spring.basic.entity;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class EntityF implements DisposableBean, InitializingBean {
    private static final Logger logger = Logger.getLogger(EntityF.class);

    public EntityF() {
        logger.info("Constructor of bean was invoked!");
    }

    @Override
    public void destroy() throws Exception {
        logger.info("Destroy method of bean was invoked!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Initializing method of bean was invoked!");
    }
}
