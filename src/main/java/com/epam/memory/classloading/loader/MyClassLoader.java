package com.epam.memory.classloading.loader;

import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Кастомный загрузчик классов
 */
public class MyClassLoader extends ClassLoader {
    private static final Logger logger = Logger.getLogger(MyClassLoader.class);

    public static final String CAT_CLASS = "com.epam.memory.classloading.entities.Cat";
    public static final String DOG_CLASS = "com.epam.memory.classloading.entities.Dog";
    private static final String DOG_URL = "file:target/classes/com/epam/memory/classloading/entities/Dog.class";
    private static final String CAT_URL = "file:target/classes/com/epam/memory/classloading/entities/Cat.class";

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class loadCat(String name) throws ClassNotFoundException {
        if (name != null) {
            if (!name.equals(CAT_CLASS)) {
                return super.loadClass(CAT_CLASS);
            }

            try {
                URL catUrl = new URL(CAT_URL);
                URLConnection connection = catUrl.openConnection();
                InputStream input = connection.getInputStream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int data = input.read();

                while (data != -1) {
                    buffer.write(data);
                    data = input.read();
                }

                input.close();

                byte[] classData = buffer.toByteArray();

                return defineClass(name, classData, 0, classData.length);

            } catch (Exception e) {
                logger.info(e);
            }
        }
        return null;
    }

    public Class loadDog(String name) throws ClassNotFoundException {
        if (name != null) {
            if (!name.equals(DOG_CLASS)) {
                return super.loadClass(DOG_CLASS);
            }

            try {
                URL catUrl = new URL(DOG_URL);
                URLConnection connection = catUrl.openConnection();
                InputStream input = connection.getInputStream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int data = input.read();

                while (data != -1) {
                    buffer.write(data);
                    data = input.read();
                }

                input.close();

                byte[] classData = buffer.toByteArray();

                return defineClass(DOG_CLASS, classData, 0, classData.length);

            } catch (Exception e) {
                logger.info(e);
            }
        }
        return null;
    }
}
