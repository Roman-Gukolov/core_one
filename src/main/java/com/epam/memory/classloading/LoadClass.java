package com.epam.memory.classloading;

import com.epam.memory.classloading.entities.common.Animal;
import com.epam.memory.classloading.loader.MyClassLoader;
import org.apache.log4j.Logger;

/**
 * Инициализация загрузки классов через MyClassLoader
 */
public class LoadClass {
    private static final Logger logger = Logger.getLogger(LoadClass.class);

    public static void main(String[] args) throws Exception {
        ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
        MyClassLoader classLoader = new MyClassLoader(parentClassLoader);

        Class objectClass = classLoader.loadCat(MyClassLoader.CAT_CLASS);
        Animal cat = (Animal) objectClass.getDeclaredConstructor().newInstance();
        logger.info("created class: " + cat);

        objectClass = classLoader.loadDog(MyClassLoader.DOG_CLASS);
        Animal dog = (Animal) objectClass.getDeclaredConstructor().newInstance();
        logger.info("created class: " + dog);

        cat.play();
        dog.voice();
    }

}
