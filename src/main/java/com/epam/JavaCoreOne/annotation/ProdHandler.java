package com.epam.JavaCoreOne.annotation;

import com.epam.JavaCoreOne.annotation.annotations.ProdCode;
import com.epam.JavaCoreOne.transport.util.TransportUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ProdHandler {
    private  Method[] allMethods;
    private Class<?> annotation;

    /**
     * Обработка аннотаций @ProdCode
     */
    public void runProdCode(Class<?> annotation) {
        this.allMethods = annotation.getDeclaredMethods();
        this.annotation = annotation;
        checkMethods();
    }

    private void checkMethods() {
        for (Method item : this.allMethods) {
            countMethod(item);
        }
    }

    private void countMethod(Method item) {
        if (item.isAnnotationPresent(ProdCode.class)) {
            try {
                Class<?> clas = Class.forName(annotation.getName());
                Object classObjec = clas.getDeclaredConstructor().newInstance();
                Object[] args = new Object[] { new String[0] };

                if (Arrays.toString(item.getParameterTypes()).equals("[]")) {
                    item.invoke(classObjec);
                    return;
                }
                item.invoke(classObjec, args);
            } catch (ClassNotFoundException | InstantiationException | InvocationTargetException
                    | NoSuchMethodException | IllegalAccessException e) {
                System.out.println("Переданный класс не найден");
            }
        }
    }

    @ProdCode
    private void prodMethod() {
        System.out.println(TransportUtil.HELP_TEXT);
    }
}
