package com.epam.spring.basic.entity;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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

    @PostConstruct
    public void postConstruct() {
        logger.info("postConstruct method of bean was invoked!");
    }

    @PreDestroy
    public void preDestroy() {
        logger.info("preDestroy method of bean was invoked!");
    }
}
