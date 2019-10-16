package com.epam.lambdas.handlers;

public enum  MainCommand {

    sortPerson("sortPerson"),
    funcInterfaces("funcInterfaces"),
    calculate("calculate"),
    authors("authors"),
    books("books"),
    checkPages("checkPages"),
    minMax("minMax"),
    singleAuthors("singleAuthors"),
    sortBooks("sortBooks"),
    titles("titles"),
    distinctAuthors("distinctAuthors"),
    myCollectors("myCollectors"),
    help("help"),
    exit("exit");

    public static final String HELP_TEXT =
            "Сортировка коллекции Person - \"sortPerson\"" + "\r\n"
            + "Использование функциональных интерфейсов - \"funcInterfaces\"" + "\r\n"
            + "Custom Functional Interface(Расчет расходов по дням/по стоимости) - \"calculate\"" + "\r\n"
            + "Печать массива authors - \"authors\"" + "\r\n"
            + "Печать массива books - \"books\"" + "\r\n"
            + "Найти книги, у которых более 200 страниц - \"checkPages\"" + "\r\n"
            + "Найти максимальное и минимальное количество страниц в книгах - \"minMax\"" + "\r\n"
            + "Найти книги только с одним автором - \"singleAuthors\"" + "\r\n"
            + "Сортировка книг - \"sortBooks\"" + "\r\n"
            + "Получить и вывести list of all titles - \"titles\"" + "\r\n"
            + "Получить уникальных авторов из массива книг - \"distinctAuthors\"" + "\r\n"
            + "collect to List<A> - \"myCollectors\"" + "\r\n"
            + "Подсказка по командам - \"help\"" + "\r\n"
            + "Выход - \"exit\"";

    private final String value;

    MainCommand(final String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
