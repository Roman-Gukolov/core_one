package com.epam.spring.intro.interfaces;

import com.epam.spring.intro.entity.Position;
import com.epam.spring.intro.exceptions.ServiceException;

public interface SalaryService {

    public void linkSalaryToDollar(Position position, double course) throws ServiceException;

    public void linkSalaryToStaff(Position position) throws ServiceException;
}
