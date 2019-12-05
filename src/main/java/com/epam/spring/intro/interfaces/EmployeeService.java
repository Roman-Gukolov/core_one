package com.epam.spring.intro.interfaces;

import com.epam.spring.intro.entity.Employee;
import com.epam.spring.intro.exceptions.ServiceException;

import java.util.List;

public interface EmployeeService {

    public void addEmployee(String name, int age, int level, int salary) throws ServiceException;

    public void deleteEmployee(int id) throws ServiceException ;

    public Employee getById(int id) throws ServiceException ;

    public List<Employee> getByLevel(int level);

    public List<Employee> getEmployees();
}
