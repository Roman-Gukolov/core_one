package com.epam.spring.intro;

import com.epam.spring.intro.entity.Company;
import com.epam.spring.intro.entity.Employee;
import com.epam.spring.intro.exceptions.ServiceException;
import com.epam.spring.intro.interfaces.EmployeeService;
import com.epam.spring.intro.interfaces.PositionService;
import com.epam.spring.intro.interfaces.SalaryService;
import com.epam.spring.intro.util.MainCommand;
import com.epam.spring.intro.util.StringUtil;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.epam.spring.intro.util.StringUtil.*;

public class CompanyProcessor {
    private static final Logger logger = Logger.getLogger(CompanyProcessor.class);
    private static Scanner input = new Scanner(System.in);
    private static Gson json = new Gson();

    private static EmployeeService employeeService;
    private static PositionService positionService;
    private static SalaryService salaryService;
    private static Company company;

    public static void main(String[] args) {
        init();
        work();
    }

    private static void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        employeeService = context.getBean("employeeService", EmployeeService.class);
        positionService = context.getBean("positionService", PositionService.class);
        salaryService = context.getBean("salaryService", SalaryService.class);
        company = context.getBean("company", Company.class);
    }

    private static void work() {
        boolean work = true;
        String command;
        logger.info(StringUtil.HELP_TEXT);

        while (work) {
            try {
                logger.info("Введите команду");
                command = input.nextLine();
                switch (MainCommand.valueOf(command)) {
                    case skipYear: {
                        company.setLifeTime(company.getLifeTime() + 1);
                        company.setSuccessfullyProjects(-2);
                        company.setSalaryLastYear(calculateSalaryForYear());
                        company.setTotalSalary(company.getTotalSalary() + calculateSalaryForYear());
                        logger.info(toString(company));
                        break;
                    }
                    case info: {
                        logger.info(toString(company));
                        break;
                    }
                    case position: {
                        workPositionService();
                        break;
                    }
                    case employee: {
                        workEmployeesService();
                        break;
                    }
                    case salary: {
                        workSalaryService();
                        break;
                    }
                    case help: {
                        logger.info(StringUtil.HELP_TEXT);
                        break;
                    }
                    case exit: {
                        work = false;
                        break;
                    }
                    default: {
                        logger.info("Неизвестная команда");
                        break;
                    }
                }
            } catch (Exception e) {
                logger.info("Произошла ошибка: не удалось обработать операцию");
            }
        }
    }

    private static void workPositionService() throws ServiceException {
        try {
            logger.info(StringUtil.HELP_TEXT_POSITION);
            String command = input.nextLine();
            switch (MainCommand.valueOf(command)) {
                case allPosition: {
                    logger.info(toString(positionService.getPositions()));
                    break;
                }
                case getById: {
                    logger.info(INPUT_ID_MESSAGE);
                    int id = input.nextInt();
                    logger.info(toString(positionService.getPositionById(id)));
                    break;
                }
                case getByName: {
                    logger.info(INPUT_NAME_MESSAGE);
                    String name = input.nextLine();
                    logger.info(toString(positionService.getPositionByName(name)));
                    break;
                }
                case create: {
                    logger.info(INPUT_NAME_MESSAGE);
                    String name = input.nextLine();
                    positionService.createPosition(name);
                    break;
                }
                case update: {
                    logger.info(INPUT_ID_MESSAGE);
                    int id = input.nextInt();
                    logger.info(INPUT_NAME_MESSAGE);
                    String name = input.nextLine();
                    logger.info("Введите id сотрудников через запятую.");
                    String idEmployees = input.nextLine();
                    List<Employee> employees = parseId(idEmployees);
                    positionService.updatePosition(id, name, employees);
                    break;
                }
                case delete: {
                    logger.info(INPUT_ID_POSITION_MESSAGE);
                    int id = input.nextInt();
                    positionService.deletePosition(id);
                    break;
                }
                case cancel:
                default: {
                    logger.info(EXIT_MESSAGE);
                    break;
                }
            }
        } catch (Exception e) {
            String errorMessage = "Ошибка: " + e.getMessage();
            logger.info(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    private static void workEmployeesService() throws ServiceException {
        try {
            logger.info(StringUtil.HELP_TEXT_EMPLOYEES);
            String command = input.nextLine();
            switch (MainCommand.valueOf(command)) {
                case allEmployees: {
                    logger.info(toString(employeeService.getEmployees()));
                    break;
                }
                case getById: {
                    logger.info(INPUT_ID_MESSAGE);
                    int id = input.nextInt();
                    logger.info(toString(employeeService.getById(id)));
                    break;
                }
                case getByLevel: {
                    logger.info(INPUT_NAME_MESSAGE);
                    int level = input.nextInt();
                    logger.info(toString(employeeService.getByLevel(level)));
                    break;
                }
                case create: {
                    logger.info("Введите имя");
                    String name = input.nextLine();
                    logger.info("Введите возраст");
                    int age = input.nextInt();
                    logger.info("Введите уровень");
                    int level = input.nextInt();
                    logger.info("Введите зарплату");
                    int salary = input.nextInt();
                    employeeService.addEmployee(name, age, level, salary);
                    break;
                }
                case delete: {
                    logger.info("Введите id сотрудника");
                    int id = input.nextInt();
                    employeeService.deleteEmployee(id);
                    break;
                }
                case cancel:
                default: {
                    logger.info(EXIT_MESSAGE);
                    break;
                }
            }
        } catch (Exception e) {
            String errorMessage = "Ошибка: " + e.getMessage();
            logger.info(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    private static void workSalaryService() throws ServiceException {
        try {
            logger.info(StringUtil.HELP_TEXT_SALARY);
            String command = input.nextLine();
            switch (MainCommand.valueOf(command)) {
                case linkToDollar: {
                    logger.info(INPUT_ID_POSITION_MESSAGE);
                    int id = input.nextInt();
                    logger.info("Введите курс доллара");
                    double course = input.nextDouble();
                    salaryService.linkSalaryToDollar(positionService.getPositionById(id), course);
                    break;
                }
                case linkToStaff: {
                    logger.info(INPUT_ID_POSITION_MESSAGE);
                    int id = input.nextInt();
                    salaryService.linkSalaryToStaff(positionService.getPositionById(id));
                    break;
                }
                case cancel:
                default: {
                    logger.info(EXIT_MESSAGE);
                    break;
                }
            }
        } catch (Exception e) {
            String errorMessage = "Ошибка: " + e.getMessage();
            logger.info(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    private static List<Employee> parseId(String ids) throws ServiceException {
        try {
            ids = ids.replaceAll(" ", "");
            String[] employeesId = ids.split(",");
            List<Employee> employees = new ArrayList<>();
            for (String id : employeesId) {
                employees.add(employeeService.getById(Integer.valueOf(id)));
            }
            return employees;
        } catch (Exception e) {
            String errorMessage = "Ошибка получения сотрудников по указанным id" + e.getMessage();
            logger.info(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    private static double calculateSalaryForYear() {
        List<Employee> employees = employeeService.getEmployees();
        double count = 0;
        for (Employee employee : employees) {
            count = count + employee.getSalary();
        }
        return count;
    }

    private static String toString(Object object) {
        return json.toJson(object);
    }
}
