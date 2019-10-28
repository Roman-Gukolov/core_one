package com.epam.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Утильный класс для подключения к БД
 */
public class Util {
    private String driverName;
    private String url;
    private String user;
    private String password;

    private void readConfig() {
        Properties prop = new Properties();
        InputStream input = null;
        String path = "jdbc\\src\\resources\\config.properties";
        try {
            input = new FileInputStream(path);
            prop.load(input);
            this.driverName = prop.getProperty("driverName");
            this.url = prop.getProperty("url");
            this.user = prop.getProperty("user");
            this.password = prop.getProperty("password");
        } catch (
                IOException ex) {
            System.out.println("Error reading file config");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println("Error reading file config");
                }
            }
        }
    }

    public String getDriverName() {
        readConfig();
        return driverName;
    }

    public String getUrl() {
        readConfig();
        return url;
    }

    public String getUser() {
        readConfig();
        return user;
    }

    public String getPassword() {
        readConfig();
        return password;
    }
}
