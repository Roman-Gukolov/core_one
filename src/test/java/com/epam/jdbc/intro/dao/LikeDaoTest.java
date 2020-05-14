package com.epam.jdbc.intro.dao;

import com.epam.jdbc.intro.daoImpl.LikesDaoImpl;
import com.epam.jdbc.intro.entity.Like;
import com.epam.jdbc.intro.util.MySqlUtils;
import org.apache.log4j.Logger;
import org.junit.*;

import java.sql.*;
import java.util.*;
import java.util.Date;

import static com.epam.jdbc.intro.util.MySqlUtils.getConnection;

public class LikeDaoTest {
    private static final Logger logger = Logger.getRootLogger();

    private LikesDao dao = new LikesDaoImpl();

    List<String> exceptedSpNames = Arrays.asList("add_like", "delete_like_by_user", "get_like_by_post");

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
    public void addLikeAndGetLikesTest() {
        try (Connection con = getConnection()) {
            Like like = getLikes();
            dao.addLike(con, like);

            Assert.assertEquals(1, dao.getLikes(con, like.getPostId()).size());

        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Test
    public void deleteLikeTest() {
        try (Connection con = getConnection()) {
            Like like = getLikes();
            dao.addLike(con, like);
            dao.deleteLike(con, like.getUserId(), like.getPostId());

            like.setUserId(2);
            dao.addLike(con, like);
            dao.deleteLike(con, like.getUserId(), like.getPostId());

            Assert.assertEquals(0, dao.getLikes(con, like.getPostId()).size());


        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @After
    public void afterTest() {
        try (Connection con = getConnection()) {
            try (Statement st = con.createStatement()) {
                st.execute(MySqlUtils.CLEAR_LIKES_SQL);
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
                st.execute(MySqlUtils.CREATE_LIKES);
                st.execute(MySqlUtils.ADD_LIKE_SP);
                st.execute(MySqlUtils.GET_LIKES_SP);
                st.execute(MySqlUtils.DELETE_LIKE_SP);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @AfterClass
    public static void destroy() {
        try (Connection con = getConnection()) {
            try (Statement st = con.createStatement()) {
                st.execute(MySqlUtils.DROP_ADD_LIKE_SQL);
                st.execute(MySqlUtils.DROP_GET_LIKE_SQL);
                st.execute(MySqlUtils.DROP_DELETE_LIKES_SQL);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private Like getLikes() {
        Like like = new Like();
        like.setPostId(1);
        like.setUserId(1);
        like.setDate(new Date());

        return like;
    }
}
