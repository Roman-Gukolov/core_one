package com.epam.spring.basic;

import com.epam.spring.basic.entity.*;
import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanProcessor {
    private static final Logger logger = Logger.getLogger(BeanProcessor.class);

    private static ConfigurableApplicationContext context;
    private static EntityA entityA;
    private static EntityB entityB;
    private static EntityC entityC;
    private static EntityD entityD;
    private static EntityE entityE;
    private static EntityF entityF;

    public static void main(String[] args) {
        init();
        sendMsg();
        destroy();
    }

    private static void init() {
        context = new ClassPathXmlApplicationContext("basic_ctx.xml", "context.xml");
        entityA = context.getBean("beanA", EntityA.class);
        entityB = context.getBean("beanB", EntityB.class);
        entityC = context.getBean("beanC", EntityC.class);
        entityD = context.getBean("beanD", EntityD.class);
        entityE = context.getBean("beanE", EntityE.class);
        entityF = context.getBean("beanF", EntityF.class);
    }

    private static void destroy() {
        context.close();
    }

    private static void sendMsg() {
        entityE.printMessage("message");
    }
}
