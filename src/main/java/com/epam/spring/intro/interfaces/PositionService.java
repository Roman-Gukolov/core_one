package com.epam.spring.intro.interfaces;

import com.epam.spring.intro.entity.Employee;
import com.epam.spring.intro.entity.Position;
import com.epam.spring.intro.exceptions.ServiceException;

import java.util.List;

public interface PositionService {

    public void createPosition(String name) throws ServiceException;

    public Position getPositionById(int id) throws ServiceException;

    public Position getPositionByName(String name) throws ServiceException;

    public void updatePosition(int id, String name, List<Employee> employees) throws ServiceException;

    public void deletePosition(int id) throws ServiceException;

    public List<Position> getPositions();
}
