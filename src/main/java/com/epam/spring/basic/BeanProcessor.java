package com.epam.spring.basic;

import com.epam.spring.basic.entity.*;
import com.epam.spring.intro.entity.Employee;
import com.epam.spring.intro.interfaces.EmployeeService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

public class BeanProcessor {
    private static final Logger logger = Logger.getLogger(BeanProcessor.class);

    private static ConfigurableApplicationContext context;
    private static EntityA entityA;
    private static EntityB entityB;
    private static EntityC entityC;
    private static EntityD entityD;
    private static EntityE entityE;
    private static EntityF entityF;
    private static Skill popularSkill;
    private static Skill unSkill;
    private static EmployeeService employeeService;

    public static void main(String[] args) {
        init();
        sendMsg();
        destroy();
        if (!checkPopular()) {
            deleteUnpopular();
        }
    }

    private static void init() {
        context = new ClassPathXmlApplicationContext("basic_ctx.xml", "context.xml");
        entityA = context.getBean("beanA", EntityA.class);
        entityB = context.getBean("beanB", EntityB.class);
        entityC = context.getBean("beanC", EntityC.class);
        entityD = context.getBean("beanD", EntityD.class);
        entityE = context.getBean("beanE", EntityE.class);
        entityF = context.getBean("beanF", EntityF.class);
        popularSkill = context.getBean("popularSkill", Skill.class);
        unSkill = context.getBean("unpopularSkill", Skill.class);
        employeeService = context.getBean("employeeService", EmployeeService.class);
    }

    private static void destroy() {
        context.close();
    }

    private static void sendMsg() {
        entityE.printMessage("message");
    }

    private static boolean checkPopular() {
        List<Employee> employees = employeeService.getEmployees().stream().filter(employee -> employee.getLevel() <= unSkill.getLevel()).collect(Collectors.toList());
        return CollectionUtils.isEmpty(employees);
    }

    private static void deleteUnpopular() {
        employeeService
                .getEmployees()
                .stream()
                .filter(employee -> employee.getLevel() <= unSkill.getLevel())
                .forEach(employee -> {
                    logger.info(employee.getName() + " has unpopular skill level - " + employee.getLevel());
                    employee.setSalary(10000);
                });
    }
}
