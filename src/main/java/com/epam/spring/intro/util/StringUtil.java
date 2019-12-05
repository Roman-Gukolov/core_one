package com.epam.spring.intro.util;

public class StringUtil {
    public static final String SUCCESSFUL_MESSAGE = "Операция выполнена успешно.";
    public static final String ERROR_MESSAGE = "Операция выполнена неудачно.";
    public static final String INPUT_ID_MESSAGE = "Введите id";
    public static final String INPUT_ID_POSITION_MESSAGE = "Введите id позиции";
    public static final String INPUT_NAME_MESSAGE = "Введите название";
    public static final String EXIT_MESSAGE = "Возвращаемся назад.";

    public static final String HELP_TEXT = "Добро пожаловать. \r\n"
            + "+1 год работы \"skipYear\" \r\n"
            + "Получить информацию о компании \"info\" \r\n"
            + "Работа с позициями в компании \"position\" \r\n"
            + "Работа с сотрудниками в компании \"employee\" \r\n"
            + "Изменить коэффициенты зп для отдельных позиций \"salary\" \r\n"
            + "Для отображения этой памятки введите \"help\" \r\n"
            + "Для выхода из программы введите \"exit\"";

    public static final String HELP_TEXT_POSITION = "Получить список позиций \"allPosition\" \r\n"
            + "Получить позицию по id \"getById\" \r\n"
            + "Получить позицию по названию \"getByName\" \r\n"
            + "Создать новую позицию \"create\" \r\n"
            + "Изменить позицию \"update\" \r\n"
            + "Удалить позицию \"delete\" \r\n"
            + "Для выхода из программы введите \"exit\" или \"cancel\"";

    public static final String HELP_TEXT_EMPLOYEES = "Получить список сотрудников \"allEmployees\" \r\n"
            + "Получить сотрудника по id \"getById\" \r\n"
            + "Получить сотрудников по уровню \"getByLevel\" \r\n"
            + "Добавить сотрудника \"create\" \r\n"
            + "Удалить позицию \"delete\" \r\n"
            + "Для выхода из программы введите \"exit\" или \"cancel\"";

    public static final String HELP_TEXT_SALARY = "Установить ЗП в зависимости от курса доллара \"linkToDollar\" \r\n"
            + "Установить ЗП в зависимости от количества сотрудников в штате \"linkToStaff\" \r\n"
            + "Для выхода из программы введите \"exit\" или \"cancel\"";
}
