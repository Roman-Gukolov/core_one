package com.epam.jdbc.intro.dao;

import com.epam.jdbc.intro.daoImpl.FriendshipsDaoImpl;
import com.epam.jdbc.intro.entity.Friendships;
import com.epam.jdbc.intro.util.MySqlUtils;
import org.apache.log4j.Logger;
import org.junit.*;

import java.sql.*;
import java.util.*;
import java.util.Date;

import static com.epam.jdbc.intro.util.MySqlUtils.getConnection;

public class FriendshipDaoTest {
    private static final Logger logger = Logger.getRootLogger();
    private FriendshipsDao dao = new FriendshipsDaoImpl();

    List<String> exceptedSpNames = Arrays.asList("add_friendship", "delete_friendships_by_userid",
            "get_friendships_by_userid1");

    @Test
    public void initTest() {
        try (Connection con = getConnection()) {
            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(MySqlUtils.PRINT_PROCEDURES_SQL)) {

                List<String> spNames = new ArrayList<>();
                while (rs.next()) {
                    spNames.add(rs.getString("Name"));
                }

                Assert.assertTrue(spNames.containsAll(exceptedSpNames));
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Test
    public void addFriendsShipAndGetFriendshipsTest() {
        Friendships friendshipsFirst = getFriendship();
        Friendships friendshipsSecond = getFriendship();

        friendshipsSecond.setUserIdFirst(3);

        try (Connection con = getConnection()) {
            dao.addFriendship(con,friendshipsFirst);
            dao.addFriendship(con,friendshipsSecond);
            friendshipsSecond.setUserIdSecond(1);
            dao.addFriendship(con,friendshipsSecond);

            Assert.assertEquals(1, dao.getFriendships(con, friendshipsFirst.getUserIdFirst()).size());
            Assert.assertEquals(2, dao.getFriendships(con, friendshipsSecond.getUserIdFirst()).size());

        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Test
    public void deleteFriendshipsTest() {
        Friendships friendships = getFriendship();
        friendships.setUserIdFirst(3);

        try (Connection con = getConnection()) {
            dao.addFriendship(con,friendships);
            friendships.setUserIdSecond(1);
            dao.addFriendship(con,friendships);

            dao.deleteFriendships(con, 3, 2);
            Assert.assertEquals(1, dao.getFriendships(con, friendships.getUserIdFirst()).size());
            dao.deleteFriendships(con, 3, 1);
            Assert.assertEquals(0, dao.getFriendships(con, friendships.getUserIdFirst()).size());

        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @After
    public void afterTest() {
        try (Connection con = getConnection()) {
            try (Statement st = con.createStatement()) {
                st.execute(MySqlUtils.CLEAR_FRIENDSHIPS_SQL);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @BeforeClass
    public static void init() {
        try (Connection con = getConnection()) {
            try (Statement st = con.createStatement()) {
                st.execute(MySqlUtils.CREATE_SCHEMA);
                st.execute(MySqlUtils.CREATE_FRIENDSHIPS);
                st.execute(MySqlUtils.ADD_FRIENDSHIP_SP);
                st.execute(MySqlUtils.GET_FRIENDSHIP_SP);
                st.execute(MySqlUtils.DELETE_FRIENDSHIP_SP);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @AfterClass
    public static void destroy() {
        try (Connection con = getConnection()) {
            try (Statement st = con.createStatement()) {
                st.execute(MySqlUtils.DROP_ADD_FRIENDSHIP_SQL);
                st.execute(MySqlUtils.DROP_GET_FRIENDSHIP_SQL);
                st.execute(MySqlUtils.DROP_DELETE_FRIENDSHIP_SQL);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private Friendships getFriendship() {
        Friendships friendships = new Friendships();
        friendships.setUserIdFirst(1);
        friendships.setUserIdSecond(2);
        friendships.setDate(new Date());

        return friendships;
    }
}
