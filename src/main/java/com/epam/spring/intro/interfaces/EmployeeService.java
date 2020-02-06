package com.epam.spring.intro.interfaces;

import com.epam.spring.intro.entity.Skill;
import com.epam.spring.intro.entity.Employee;
import com.epam.spring.intro.exceptions.ServiceException;

import java.util.List;

public interface EmployeeService {

    /**
     * Добавить сотрудника.
     * @param name Имя
     * @param age Возраст
     * @param skill Скилл
     * @param salary зарплата
     * @throws ServiceException в случае ошибок
     */
    public void addEmployee(String name, int age, Skill skill, int salary) throws ServiceException;

    /**
     * Удалить сотрудника
     * @param id id сотрудника
     * @throws ServiceException в случае ошибок
     */
    public void deleteEmployee(int id) throws ServiceException;

    /**
     * Найти по id
     * @param id id
     * @return сотрудник
     * @throws ServiceException в случае ошибок
     */
    public Employee getById(int id) throws ServiceException;

    /**
     * Найти по уровню скилла
     * @param level уровень скилла
     * @return Найденные сотрудники
     */
    public List<Employee> getByLevel(int level);

    /**
     * Получить список всех сотрудников
     * @return все сотрудники
     */
    public List<Employee> getEmployees();
}
