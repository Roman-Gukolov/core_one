package com.epam.jdbc.intro.dao;

import com.epam.jdbc.intro.daoImpl.UserDaoImpl;
import com.epam.jdbc.intro.entity.User;
import com.epam.jdbc.intro.util.MySqlUtils;
import org.apache.log4j.Logger;
import org.junit.*;

import java.sql.*;
import java.util.Date;
import java.util.*;

import static com.epam.jdbc.intro.util.MySqlUtils.getConnection;

public class UserDaoTest {
    private static final Logger logger = Logger.getRootLogger();

    private UserDao dao = new UserDaoImpl();

    List<String> exceptedSpNames = Arrays.asList("add_user", "delete_user", "get_userid", "update_user");

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
    public void addUserTest() {
        try (Connection con = getConnection()) {
            User user = getUser();
            dao.addUser(con, user);

            Assert.assertNotEquals(0, dao.getUserId(con, user));
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Test
    public void getUserIdTest() {
        try (Connection con = getConnection()) {
            User user = getUser();
            dao.addUser(con, user);
            user.setUserId(dao.getUserId(con, user));

            Assert.assertEquals(1, user.getUserId());
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Test
    public void updateUserTest() {
        try (Connection con = getConnection()) {
            User originalUser = getUser();
            dao.addUser(con, originalUser);
            originalUser.setUserId(dao.getUserId(con, originalUser));

            User updatedUser = getUser();
            updatedUser.setUserId(originalUser.getUserId());
            updatedUser.setName("updatedName");
            updatedUser.setSurName("updatedSurname");
            dao.updateUser(con, updatedUser);
            updatedUser.setUserId(dao.getUserId(con, updatedUser));

            Assert.assertEquals(originalUser.getUserId(), updatedUser.getUserId());
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Test
    public void deletePostTest() {
        try (Connection con = getConnection()) {
            User user = getUser();
            dao.addUser(con, user);
            user.setUserId(dao.getUserId(con, user));

            dao.deleteUser(con, user.getUserId());
            user.setUserId(dao.getUserId(con, user));


            Assert.assertEquals(0, user.getUserId());
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @After
    public void afterTest() {
        try (Connection con = getConnection()) {
            try (Statement st = con.createStatement()) {
                st.execute(MySqlUtils.CLEAR_USERS_SQL);
                st.execute(MySqlUtils.CLEAR_USERS_INDEXES_SQL);
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
                st.execute(MySqlUtils.CREATE_USERS);
                st.execute(MySqlUtils.ADD_USER_SP);
                st.execute(MySqlUtils.GET_USER_ID_SP);
                st.execute(MySqlUtils.UPDATE_USER_SP);
                st.execute(MySqlUtils.DELETE_USER_SP);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @AfterClass
    public static void destroy() {
        try (Connection con = getConnection()) {
            try (Statement st = con.createStatement()) {
                st.execute(MySqlUtils.DROP_ADD_USER_SQL);
                st.execute(MySqlUtils.DROP_GET_USER_ID_SQL);
                st.execute(MySqlUtils.DROP_UPDATE_USER_SQL);
                st.execute(MySqlUtils.DROP_DELETE_USER_SQL);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private static User getUser() {
        User user = new User();
        user.setName("name");
        user.setSurName("surname");
        user.setBirthday(new Date());

        return user;
    }
}
