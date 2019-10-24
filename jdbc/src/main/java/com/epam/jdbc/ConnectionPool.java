package com.epam.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayDeque;

public class ConnectionPool {

    private ArrayDeque<Connection> availableConnections = new ArrayDeque<>();
    private ArrayDeque<Connection> usedConnections = new ArrayDeque<>();
    private String url;
    private String user;
    private String password;

    public ConnectionPool()  {
        Util util = new Util();
        this.url = util.getUrl();
        this.user = util.getUser();
        this.password = util.getPassword();
        String driver = util.getDriverName();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Error creating connection pool: " + e);
        }
        for (int i = 0; i < 100; i++) {
            availableConnections.add(getConnection());
        }
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public synchronized Connection useCon() {
        Connection newConn;
        if (availableConnections.size() == 0) {
            System.out.println("available connection not found. Create new...");
            newConn = getConnection();
            System.out.println("create new connection successful");
        } else {
            newConn = availableConnections.poll();
        }
        usedConnections.push(newConn);
        return newConn;
    }

    public synchronized void putUsedCon(Connection connection) throws NullPointerException {
        if (connection != null) {
            if (usedConnections.remove(connection)) {
                availableConnections.push(connection);
            } else {
                throw new NullPointerException("Connection not in the usedConnections array");
            }
        }
    }

    public int getCountAvailableConnections() {
        return availableConnections.size();
    }
}
