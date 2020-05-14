package com.epam.jdbc.intro.dao;

import com.epam.jdbc.intro.daoImpl.PostDaoImpl;
import com.epam.jdbc.intro.entity.Post;
import com.epam.jdbc.intro.util.MySqlUtils;
import org.apache.log4j.Logger;
import org.junit.*;

import java.sql.*;
import java.util.*;
import java.util.Date;

import static com.epam.jdbc.intro.util.MySqlUtils.getConnection;

public class PostDaoTest {
    private static final Logger logger = Logger.getRootLogger();

    private PostDao dao = new PostDaoImpl();

    List<String> exceptedSpNames = Arrays.asList("add_post", "delete_post", "get_postid", "update_post");

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
    public void addPostTest() {
        try (Connection con = getConnection()) {
            Post post = getPost();
            dao.addPost(con, post);

            Assert.assertNotEquals(0, dao.getPostId(con, post));
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Test
    public void getPostIdTest() {
        try (Connection con = getConnection()) {
            Post post = getPost();
            dao.addPost(con, post);
            post.setId(dao.getPostId(con, post));

            Assert.assertEquals(1, post.getId());
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Test
    public void updatePostTest() {
        try (Connection con = getConnection()) {
            Post originalPost = getPost();
            dao.addPost(con, originalPost);
            originalPost.setId(dao.getPostId(con, originalPost));

            Post updatedPost = getPost();
            updatedPost.setId(originalPost.getId());
            updatedPost.setText("updatedText");
            dao.updatePost(con, updatedPost);
            updatedPost.setId(dao.getPostId(con, updatedPost));

            Assert.assertEquals(originalPost.getId(), updatedPost.getId());
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Test
    public void deletePostTest() {
        try (Connection con = getConnection()) {
            Post post = getPost();
            dao.addPost(con, post);
            post.setId(dao.getPostId(con, post));

            dao.deletePost(con, post.getId());
            post.setId(dao.getPostId(con, post));


            Assert.assertEquals(0, post.getId());
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @After
    public void afterTest() {
        try (Connection con = getConnection()) {
            try (Statement st = con.createStatement()) {
                st.execute(MySqlUtils.CLEAR_POSTS_SQL);
                st.execute(MySqlUtils.CLEAR_POSTS_INDEXES_SQL);
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
                st.execute(MySqlUtils.CREATE_POSTS);
                st.execute(MySqlUtils.ADD_POST_SP);
                st.execute(MySqlUtils.GET_POST_ID_SP);
                st.execute(MySqlUtils.UPDATE_POST_SP);
                st.execute(MySqlUtils.DELETE_POST_SP);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @AfterClass
    public static void destroy() {
        try (Connection con = getConnection()) {
            try (Statement st = con.createStatement()) {
                st.execute(MySqlUtils.DROP_ADD_POST_SQL);
                st.execute(MySqlUtils.DROP_GET_POST_ID_SQL);
                st.execute(MySqlUtils.DROP_UPDATE_POST_SQL);
                st.execute(MySqlUtils.DROP_DELETE_POST_SQL);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private static Post getPost() {
        Post post = new Post();
        post.setId(1);
        post.setUserId(1);
        post.setText("text for post");
        post.setDate(new Date());

        return post;
    }
}
