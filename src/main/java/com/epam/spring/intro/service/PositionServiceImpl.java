package com.epam.spring.intro.service;

import com.epam.spring.intro.entity.Employee;
import com.epam.spring.intro.entity.Position;
import com.epam.spring.intro.exceptions.ServiceException;
import com.epam.spring.intro.interfaces.PositionService;
import com.epam.spring.intro.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PositionServiceImpl implements PositionService {
    private static final Logger logger = Logger.getLogger(PositionService.class);
    private static int positionsCount = 1;

    private List<Position> positions;

    @Override
    public void createPosition(String name) throws ServiceException {
        List<Position> findedPosition = positions.stream().filter(p -> p.getName().equals(name)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(findedPosition)) {
            throw new ServiceException("Позиция с таким имененм уже существует");
        }

        try {
            positionsCount++;
            Position position = new Position();
            position.setId(positionsCount);
            position.setName(name);
            position.setEmployees(Collections.emptyList());
            positions.add(position);
            logger.info(StringUtil.SUCCESSFUL_MESSAGE);
        } catch (Exception e) {
            String errorMessage = "Ошиюка создания позиции: " + e.getMessage();
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Position getPositionById(int id) throws ServiceException {
        try {
            return positions.get(id);
        } catch (Exception e) {
            String errorMessage = "Ошиюка поиска позиции: " + e.getMessage();
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public void updatePosition(int id, String name, List<Employee> employees) throws ServiceException {
        try {
            for (Position p : positions) {
                for (Employee employee : employees) {
                    if (p.getEmployees().contains(employee)) {
                        throw new ServiceException("Ошибка добавления сотрудников: данный сотрудник уже назначен на другую позицию - "
                                + "id" + employee.getId() +"имя: " + employee.getName());
                    }
                }
            }
            Position position = positions.get(id);
            position.setName(name);
            position.setEmployees(employees);
            logger.info(StringUtil.SUCCESSFUL_MESSAGE);
        } catch (Exception e) {
            String errorMessage = "Ошиюка изменения позиции: " + e.getMessage();
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public void deletePosition(int id) throws ServiceException {
        try {
            positions.remove(id);
            positionsCount--;
            logger.info(StringUtil.SUCCESSFUL_MESSAGE);
        } catch (Exception e) {
            String errorMessage = "Ошиюка удаления позиции: " + e.getMessage();
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Position getPositionByName(String name) throws ServiceException {
        try {
            return positions.stream().filter(p -> name.equalsIgnoreCase(p.getName())).findFirst().get();
        } catch (Exception e) {
            String errorMessage = "Ошиюка поиска позиции: " + e.getMessage();
            logger.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
