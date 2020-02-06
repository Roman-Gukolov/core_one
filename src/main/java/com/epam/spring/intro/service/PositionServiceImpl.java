package com.epam.spring.intro.service;

import com.epam.spring.intro.entity.Employee;
import com.epam.spring.intro.entity.Position;
import com.epam.spring.intro.entity.Skill;
import com.epam.spring.intro.exceptions.ServiceException;
import com.epam.spring.intro.interfaces.PositionService;
import com.epam.spring.intro.util.StringMessages;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class PositionServiceImpl implements PositionService {
    private static final Logger logger = Logger.getLogger(PositionService.class);
    private static int positionsCount = 1;

    private List<Position> positions;

    //ужаснейший костыль, при асинхронной работе будет работать некорректно.
    private Integer unpopularSkillRate;

    @Override
    public void createPosition(String name, List<Employee> employees, List<Skill> skills) throws ServiceException {
        List<Position> findedPosition = positions.stream()
                .filter(p -> p.getName().equals(name)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(findedPosition)) {
            throw new ServiceException("Позиция с таким имененм уже существует");
        }

        try {
            positionsCount++;
            Position position = new Position();
            position.setId(positionsCount);
            position.setName(name);
            position.setEmployees(employees);

            if (unpopularSkillRate != null) {
                logger.info("Если у сотрудника непопулярный скилл, его зарплата будет снижена.");
                employees
                        .stream()
                        .filter(employee -> employee.getSkill().getRate() <= unpopularSkillRate)
                        .forEach(employee -> employee.setSalary(employee.getSalary() * 0.1));
            }

            position.setSkills(skills);
            positions.add(position);
            logger.info(StringMessages.SUCCESSFUL_MESSAGE);
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
    public void updatePosition(int id, String name, List<Employee> employees, List<Skill> skills)
            throws ServiceException {
        try {
            for (Position p : positions) {
                for (Employee employee : employees) {
                    if (p.getEmployees().contains(employee)) {
                        throw new ServiceException("Ошибка добавления сотрудников: данный сотрудник уже назначен " +
                                "на другую позицию - "
                                + "id" + employee.getId() +"имя: " + employee.getName());
                    }
                }
            }
            Position position = positions.get(id);
            position.setName(name);
            position.setEmployees(employees);
            position.setSkills(skills);

            if (unpopularSkillRate != null) {
                logger.info("Если у сотрудника непопулярный скилл, его зарплата будет снижена.");
                employees
                        .stream()
                        .filter(employee -> employee.getSkill().getRate() <= unpopularSkillRate)
                        .forEach(employee -> employee.setSalary(employee.getSalary() * 0.1));
            }

            logger.info(StringMessages.SUCCESSFUL_MESSAGE);
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
            logger.info(StringMessages.SUCCESSFUL_MESSAGE);
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

    //Здесь должна быть логика удаления/добавления непопулярных/популярных скилов, но для этого должен быть отдельный сервис по работе с скилами в позициях.
    @Override
    public void setUnpopularSkillRate(Position position, Integer rate) {
        if (unpopularSkillRate < rate) {
            unpopularSkillRate = rate;
            logger.info("Рейтинг популярности скила был изменен.");
            List<Skill> unpopularSkills = position.getSkills().stream()
                    .filter(skill -> skill.getRate() <= unpopularSkillRate).collect(Collectors.toList());
            position.getSkills().removeAll(unpopularSkills);
        } else if (unpopularSkillRate > rate) {
            unpopularSkillRate = rate;
            logger.info("Рейтинг популярности скила был изменен.");
           //Здесь должна быть логика добавления популярных скилов, но этим не должен заниматься сервис по работе с позициями.
        } else {
            logger.info("Рейтинг изменен не был.");
        }
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
