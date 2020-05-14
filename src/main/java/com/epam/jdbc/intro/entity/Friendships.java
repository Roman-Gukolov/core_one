package com.epam.jdbc.intro.entity;

import java.util.Date;

public class Friendships {

    private int userIdFirst;

    private int userIdSecond;

    private Date date;

    public int getUserIdFirst() {
        return userIdFirst;
    }

    public void setUserIdFirst(int userIdFirst) {
        this.userIdFirst = userIdFirst;
    }

    public int getUserIdSecond() {
        return userIdSecond;
    }

    public void setUserIdSecond(int userIdSecond) {
        this.userIdSecond = userIdSecond;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
