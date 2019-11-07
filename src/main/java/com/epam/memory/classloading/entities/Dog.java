package com.epam.memory.classloading.entities;

import com.epam.memory.classloading.entities.common.Animal;
import org.apache.log4j.Logger;

/**
 * Сущность Dog
 */
public class Dog extends Animal {
    private static final Logger logger = Logger.getLogger(Dog.class);

    public void play() {
        logger.info("dog playing...");
    }

    public void voice() {
        logger.info("dog voice...");
    }
}
