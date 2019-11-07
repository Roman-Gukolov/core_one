package com.epam.memory.classloading.entities;

import com.epam.memory.classloading.entities.common.Animal;
import org.apache.log4j.Logger;

/**
 * Сущность Cat
 */
public class Cat extends Animal {
    private static final Logger logger = Logger.getLogger(Cat.class);

    public void play() {
        logger.info("cat playing...");
    }

    public void voice() {
        logger.info("cat voice...");
    }
}
