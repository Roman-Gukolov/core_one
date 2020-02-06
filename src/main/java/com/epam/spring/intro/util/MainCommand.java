package com.epam.spring.intro.util;

public enum MainCommand {

    skipYear("skipYear"),
    info("info"),
    allPosition("allPosition"),
    allEmployees("allEmployees"),
    position("position"),
    employee("employee"),
    getById("getById"),
    getByName("getByName"),
    getByLevel("getByLevel"),
    create("create"),
    update("update"),
    delete("delete"),
    cancel("cancel"),
    salary("salary"),
    linkToDollar("linkToDollar"),
    linkToStaff("linkToStaff"),
    setSkillRate("setSkillRate"),
    help("help"),
    exit("exit");

    private final String value;

    MainCommand(final String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
