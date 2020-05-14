package com.epam.jdbc.intro.util;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlUtils {
    private static final Logger logger = Logger.getRootLogger();

    public static final String CREATE_SCHEMA = "CREATE SCHEMA IF NOT EXISTS jdbcintro";

    public static final String CREATE_FRIENDSHIPS = "CREATE TABLE IF NOT EXISTS friendships (\n" +
            "  userid1 int NOT NULL,\n" +
            "  userid2 int NOT NULL,\n" +
            "  timestamp timestamp NULL DEFAULT NULL\n" +
            ")";

    public static final String CREATE_LIKES = "CREATE TABLE IF NOT EXISTS likes (\n" +
            "  postid int NOT NULL,\n" +
            "  userid int NOT NULL,\n" +
            "  timestamp timestamp NOT NULL\n" +
            ")";

    public static final String CREATE_POSTS = "CREATE TABLE IF NOT EXISTS posts (\n" +
            "  id int unsigned NOT NULL AUTO_INCREMENT,\n" +
            "  userid int DEFAULT NULL,\n" +
            "  text varchar(100) DEFAULT NULL,\n" +
            "  timestamp timestamp NULL DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ")";

    public static final String CREATE_USERS = "CREATE TABLE IF NOT EXISTS users (\n" +
            "  id int NOT NULL AUTO_INCREMENT,\n" +
            "  name varchar(45) DEFAULT NULL,\n" +
            "  surname varchar(45) DEFAULT NULL,\n" +
            "  birthdate date DEFAULT NULL,\n" +
            "  PRIMARY KEY (id),\n" +
            "  UNIQUE KEY id_UNIQUE (id)\n" +
            ")";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS table1 (\n" +
            "  id int NOT NULL AUTO_INCREMENT,\n" +
            "  value varchar(45) DEFAULT NULL,\n" +
            "  comment varchar(45) DEFAULT NULL,\n" +
            "  PRIMARY KEY (id),\n" +
            "  UNIQUE KEY id_UNIQUE (id)\n" +
            ")";


    public static final String ADD_FRIENDSHIP_SP = "CREATE PROCEDURE add_friendship (IN userid1 int, IN userid2 int, IN timestamp timestamp)\n" +
            "begin\n" +
            "\tinsert into friendships(userid1, userid2, timestamp) values (userid1, userid2, timestamp);\n" +
            "end";

    public static final String GET_FRIENDSHIP_SP = "CREATE PROCEDURE get_friendships_by_userid1(IN userid1 int)\n" +
            "begin\n" +
            "\tselect friendships.userid2 as id from friendships where friendships.userid1 = userid1;\n" +
            "end";

    public static final String DELETE_FRIENDSHIP_SP = "CREATE PROCEDURE delete_friendships_by_userid(IN userid1 int, IN userid2 int)\n" +
            "begin\n" +
            "\tdelete from friendships where friendships.userid1 = userid1 and friendships.userid2 = userid2;\n" +
            "end";

    public static final String ADD_LIKE_SP = "CREATE PROCEDURE add_like(IN postid int, IN userid int, IN timestamp timestamp)\n" +
            "begin\n" +
            "\tinsert into likes(postid, userid, timestamp) values (postid, userid, timestamp);\n" +
            "end";

    public static final String GET_LIKES_SP = "CREATE PROCEDURE get_like_by_post(IN postid int)\n" +
            "begin\n" +
            "\tselect likes.userid as id from likes where likes.postid = postid;\n" +
            "end";

    public static final String DELETE_LIKE_SP = "CREATE PROCEDURE delete_like_by_user(in userid int, in postid int)\n" +
            "begin\n" +
            "\tdelete from likes where likes.userid = userid and likes.postid = postid;\n" +
            "end";

    public static final String ADD_POST_SP = "CREATE PROCEDURE add_post(IN userid int, IN text varchar(100), IN timestamp timestamp)\n" +
            "begin\n" +
            "\tinsert into posts(userid, text, timestamp) values (userid, text, timestamp);\n" +
            "end";

    public static final String GET_POST_ID_SP = "CREATE PROCEDURE get_postid(in userid int, in text varchar(100), in timestamp timestamp)\n" +
            "begin\n" +
            "\tselect posts.id as id from posts where posts.userid = userid and posts.text = text and posts.timestamp = timestamp;\n" +
            "end";

    public static final String GET_POST_SP = "CREATE PROCEDURE get_post(in postid int)\n" +
            "begin\n" +
            "\tselect posts.userid, posts.text, posts.timestamp from posts where posts.id = postid;\n" +
            "end";

    public static final String DELETE_POST_SP = "CREATE PROCEDURE delete_post(IN id int)\n" +
            "begin\n" +
            "\tdelete from posts where posts.id = id;\n" +
            "end";

    public static final String UPDATE_POST_SP = "CREATE PROCEDURE update_post(IN id int, IN userid int, IN text varchar(100), IN timestamp timestamp)\n" +
            "begin\n" +
            "\tupdate posts set posts.userid = userid, posts.text = text, posts.timestamp = timestamp where posts.id = id;\n" +
            "end";

    public static final String ADD_USER_SP = "CREATE PROCEDURE add_user(IN name varchar(45), IN surname varchar(45), IN birthdate varchar(100))\n" +
            "begin\n" +
            "\tinsert into users(name, surname, birthdate) values (name, surname, convert(birthdate, date));\n" +
            "end";

    public static final String GET_USER_ID_SP = "CREATE PROCEDURE get_userid(in name varchar(45), in surname varchar(45), in birthdate varchar(100))\n" +
            "begin\n" +
            "\tselect id from users where users.name = name and users.surname = surname and users.birthdate = convert(birthdate, date);\n" +
            "end";

    public static final String GET_USER_SP = "CREATE PROCEDURE get_user(in userid int)\n" +
            "begin\n" +
            "\tselect users.name, users.surname, users.birthdate from users where users.id = userid;\n" +
            "end";

    public static final String DELETE_USER_SP = "CREATE PROCEDURE delete_user(IN id int)\n" +
            "begin\n" +
            "\tdelete from users where users.id = id;\n" +
            "end";

    public static final String UPDATE_USER_SP = "CREATE PROCEDURE update_user(IN id int, IN name varchar(45), IN surname varchar(45), IN birthdate varchar(100))\n" +
            "begin\n" +
            "\tupdate users set users.name = name, users.surname = surname, users.birthdate = convert(birthdate, date) where users.id = id;\n" +
            "end";

    public static final String CLEAR_FRIENDSHIPS_SQL = "delete from friendships";

    public static final String CLEAR_LIKES_SQL = "delete from likes";

    public static final String CLEAR_POSTS_SQL = "delete from posts";

    public static final String CLEAR_USERS_SQL = "delete from users";

    public static final String CLEAR_POSTS_INDEXES_SQL = "ALTER TABLE posts AUTO_INCREMENT = 0";

    public static final String CLEAR_USERS_INDEXES_SQL = "ALTER TABLE users AUTO_INCREMENT = 0";

    public static final String DROP_ADD_FRIENDSHIP_SQL = "DROP PROCEDURE add_friendship";

    public static final String DROP_GET_FRIENDSHIP_SQL = "DROP PROCEDURE get_friendships_by_userid1";

    public static final String DROP_DELETE_FRIENDSHIP_SQL = "DROP PROCEDURE delete_friendships_by_userid";

    public static final String DROP_ADD_LIKE_SQL = "DROP PROCEDURE add_like";

    public static final String DROP_GET_LIKE_SQL = "DROP PROCEDURE get_like_by_post";

    public static final String DROP_DELETE_LIKES_SQL = "DROP PROCEDURE delete_like_by_user";

    public static final String DROP_ADD_POST_SQL = "DROP PROCEDURE add_post";

    public static final String DROP_GET_POST_ID_SQL = "DROP PROCEDURE get_postid";

    public static final String DROP_GET_POST_SQL = "DROP PROCEDURE get_post";

    public static final String DROP_DELETE_POST_SQL = "DROP PROCEDURE delete_post";

    public static final String DROP_UPDATE_POST_SQL = "DROP PROCEDURE update_post";

    public static final String DROP_ADD_USER_SQL = "DROP PROCEDURE add_user";

    public static final String DROP_GET_USER_ID_SQL = "DROP PROCEDURE get_userid";

    public static final String DROP_GET_USER_SQL = "DROP PROCEDURE get_user";

    public static final String DROP_DELETE_USER_SQL = "DROP PROCEDURE delete_user";

    public static final String DROP_UPDATE_USER_SQL = "DROP PROCEDURE update_user";

    public static final String PRINT_PROCEDURES_SQL = "SHOW PROCEDURE STATUS WHERE Db = 'jdbcintro'";

    public static Connection getConnection() {
        try (InputStream propFile = new FileInputStream("src/main/resources/application.properties")) {
            Properties props = new Properties();
            props.load(propFile);

            Class.forName(props.getProperty("ds.driverName"));
            String url = props.getProperty("ds.url");

            return DriverManager.getConnection(url, props);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            logger.error(e);
            return null;
        }
    }
}
