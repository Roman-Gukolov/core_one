package com.epam.spring.intro.service;

import com.epam.spring.intro.entity.Employee;
import com.epam.spring.intro.entity.Position;
import com.epam.spring.intro.exceptions.ServiceException;
import com.epam.spring.intro.interfaces.SalaryService;
import org.apache.log4j.Logger;

import java.util.List;

public class SalaryServiceImpl implements SalaryService {
    private static final Logger logger = Logger.getLogger(SalaryService.class);

    private static final int OPTIMAL_COURCE = 45;
    private static final int MIN_COURCE = 0;
    private static final double MAX_COEFFICIENT = 1.35;
    private static final double OPTIMAL_COEFFICIENT = 1;
    private static final double MIN_COEFFICIENT = 0.80;

    @Override
    public void linkSalaryToDollar(Position position, double course) throws ServiceException {
        if (course <= MIN_COURCE) {
            throw new ServiceException("Некорректно указан курс доллара");
        }
        try {
            if (course == OPTIMAL_COURCE) {
                changeSalary(position, OPTIMAL_COEFFICIENT);
            }
            if (course > OPTIMAL_COURCE) {
                changeSalary(position, MAX_COEFFICIENT);
            }
            if (course < OPTIMAL_COURCE) {
                changeSalary(position, MIN_COEFFICIENT);
            }
        } catch (Exception e) {
            String errorMessage = "Не удалось изменить коэффициент зарплаты для сотрудников: " + e.getMessage();
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }

    }

    @Override
    public void linkSalaryToStaff(Position position) throws ServiceException {
        try {
            int staffSize = position.getEmployees().size();
            if (staffSize <= 0) {
                throw new ServiceException("На данной позиции нет действующий сотрудников.");
            }

            if (staffSize > 15) {
                changeSalary(position, MAX_COEFFICIENT);
            }
            if (staffSize < 5) {
                changeSalary(position, MIN_COEFFICIENT);
            } else {
                changeSalary(position, OPTIMAL_COEFFICIENT);
            }
        } catch (Exception e) {
            String errorMessage = "Не удалось изменить коэффициент зарплаты для сотрудников: " + e.getMessage();
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    private void changeSalary(Position position, double coeff) {
        double salary;
        List<Employee> employees = position.getEmployees();
        for (Employee employee : employees) {
            salary = employee.getSalary();
            employee.setSalary(salary * coeff);
        }
    }
}
