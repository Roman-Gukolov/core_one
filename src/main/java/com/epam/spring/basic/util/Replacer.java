package com.epam.spring.basic.util;

import com.epam.spring.intro.exceptions.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class Replacer implements MethodReplacer {
    private static final Logger logger = Logger.getLogger(Replacer.class);
    private static final String METHOD_NAME = "printMessage";

    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {

        if (isFormatMessageMethod(method)) {
            String msg = (String) objects[0];
            String fullMessage = "this method was replaced. Message: " + msg;
            logger.info(fullMessage);

            return fullMessage;
        } else {
            String errorMessage = "Unable to reimplement method " + method.getName();
            logger.info(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    private boolean isFormatMessageMethod(Method method) {
        if (method.getParameterTypes().length != 1) {
            return false;
        }

        if (!METHOD_NAME.equals(method.getName())) {
            return false;
        }

        if (method.getParameterTypes()[0] != String.class) {
            return false;
        }

        if (StringUtils.isEmpty(method.getParameters()[0].toString())) {
            return false;
        }

        return true;
    }
}
