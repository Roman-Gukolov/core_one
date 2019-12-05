package com.epam.spring.intro.service;

import com.epam.spring.intro.entity.Employee;
import com.epam.spring.intro.exceptions.ServiceException;
import com.epam.spring.intro.interfaces.EmployeeService;
import com.epam.spring.intro.util.StringUtil;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = Logger.getLogger(EmployeeService.class);
    private static int employeeCount = 3;

    private List<Employee> employees;

    @Override
    public void addEmployee(String name, int age, int level, int salary) throws ServiceException {
        try {
            employeeCount++;
            Employee employee = new Employee();
            employee.setId(employeeCount);
            employee.setName(name);
            employee.setAge(age);
            employee.setLevel(level);
            employee.setSalary(salary);
            employees.add(employee);
            logger.info(StringUtil.SUCCESSFUL_MESSAGE);
        } catch (Exception e) {
            String errorMessage = "Ошиюка добавления сотрудника: " + e.getMessage();
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public void deleteEmployee(int id) throws ServiceException {
        try {
            employees.remove(id);
            logger.info(StringUtil.SUCCESSFUL_MESSAGE);
        } catch (Exception e) {
            String errorMessage = "Ошиюка удаления сотрудника: " + e.getMessage();
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Employee getById(int id) throws ServiceException {
        try {
            return employees.get(id);
        } catch (Exception e) {
            String errorMessage = "Сотрудника с таким id не существует: " + e.getMessage();
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public List<Employee> getByLevel(int level) {
        try {
            return employees.stream().filter(e -> e.getLevel() >= level).collect(Collectors.toList());
        } catch (Exception e) {
            String errorMessage = "Ошиюка поиска сотрудника: " + e.getMessage();
            logger.error(errorMessage);
            return Collections.emptyList();
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
