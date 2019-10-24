package com.epam.JavaCoreOne.annotation.handlers;

import com.epam.JavaCoreOne.annotation.annotations.ThisCodeSmells;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CodeSmellsHandler {
    private StringBuilder output= new StringBuilder();
    private int lengthByClasses = 0;
    private int lengthByMethods = 0;
    private int lengthByFields = 0;
    private Method[] allMethods;
    private Field[] allFields;
    private Class<?> annotation;

    public CodeSmellsHandler(){
    }

    /**
     * Обработка аннотаций ThisCodeSmells
     */
    public void inspectorAnnotation(Class<?> annotation) {
        this.annotation = annotation;
        this.allMethods = this.annotation.getDeclaredMethods();
        this.allFields = this.annotation.getDeclaredFields();

        output.append("Поиск аннотаций в ").append(this.annotation.getSimpleName()).append("\r\n");
        output.append("--------------------------------------------------------------------\r\n");

       checkClass();
       checkMethods();
       checkFields();

        output.append("Количество аннотаций над классом - ").append(lengthByClasses)
                .append(", \r\nКоличество аннотаций над методом - ").append(lengthByMethods)
                .append(", \r\nКоличество аннотаций над полями - ").append(lengthByFields).append("\r\n");

        output.append("-----------------------------Конец----------------------------------\r\n");

        System.out.println(output);
    }

    private void checkClass() {
        countClass();
    }

    private void checkMethods() {
        for (Method item : this.allMethods) {
            countMethod(item);
        }
    }

    private void checkFields() {
        for (Field item : this.allFields) {
            countField(item);
        }
    }

    private void countClass() {
        if (this.annotation.isAnnotationPresent(ThisCodeSmells.class)) {
            ThisCodeSmells annotationInfo = this.annotation.getAnnotation(ThisCodeSmells.class);
            this.lengthByClasses++;
            this.output.append("Аннотация присутствует над классом: ").append(annotation.getSimpleName())
                    .append(", Проголосовавшее лицо: ").append(annotationInfo.reviewer()).append("\r\n");
        }
    }

    private void countMethod(Method item) {
        if (item.isAnnotationPresent(ThisCodeSmells.class)) {
            ThisCodeSmells annotationInfo = item.getAnnotation(ThisCodeSmells.class);
            this.lengthByMethods++;
            this.output.append("Аннотация присутствует над методом: ").append(item.getName())
                    .append(", Проголосовавшее лицо: ").append(annotationInfo.reviewer()).append("\r\n");
        }
    }

    private void countField(Field item) {
        if (item.isAnnotationPresent(ThisCodeSmells.class)) {
            ThisCodeSmells annotationInfo = item.getAnnotation(ThisCodeSmells.class);
            this.lengthByFields++;
            this.output.append("Аннотация присутствует над полем: ").append(item.getName())
                    .append(", Проголосовавшее лицо: ").append(annotationInfo.reviewer()).append("\r\n");
        }
    }
}
