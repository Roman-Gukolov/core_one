package com.epam.spring.intro.interfaces;

import com.epam.spring.intro.entity.Employee;
import com.epam.spring.intro.entity.Position;
import com.epam.spring.intro.entity.Skill;
import com.epam.spring.intro.exceptions.ServiceException;

import java.util.List;

public interface PositionService {

    /**
     * Создать позицию
     * @param name Название
     * @param employees сотрудники для позиции
     * @param skills требуемые скилы
     * @throws ServiceException в случае ошибок
     */
    public void createPosition(String name, List<Employee> employees, List<Skill> skills) throws ServiceException;

    /**
     * Найти позицию по Id
     * @param id id позиции
     * @return позиция
     * @throws ServiceException в случае ошибок
     */
    public Position getPositionById(int id) throws ServiceException;

    /**
     * Найти позицию по названию
     * @param name название
     * @return позиция
     * @throws ServiceException в случае ошибок
     */
    public Position getPositionByName(String name) throws ServiceException;

    /**
     * Изменить позицию
     * @param id id позиции
     * @param name название
     * @param employees сотрудники
     * @param skills требуемые скилы для позиции
     * @throws ServiceException в случае ошибок
     */
    public void updatePosition(int id, String name, List<Employee> employees, List<Skill> skills) throws ServiceException;

    /**
     * Удалить позицию
     * @param id id позицию
     * @throws ServiceException в случае ошибок
     */
    public void deletePosition(int id) throws ServiceException;

    /**
     * Получить все позиции
     * @return список позиций
     */
    public List<Position> getPositions();

    /**
     * Установить рейтинг непопулярности для скила в позиции
     */
    public void setUnpopularSkillRate(Position position, Integer rate);
}
