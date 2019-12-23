package com.epam.spring.basic.entity;

import org.apache.log4j.Logger;

public class EntityE {
    private static final Logger logger = Logger.getLogger(EntityE.class);

    public void printMessage(String message) {
        logger.info("It's default method print message. Message: " + message);
    }
}
