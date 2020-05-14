package com.epam.jdbc.intro;

import com.epam.jdbc.intro.dao.FriendshipsDao;
import com.epam.jdbc.intro.dao.LikesDao;
import com.epam.jdbc.intro.dao.PostDao;
import com.epam.jdbc.intro.dao.UserDao;
import com.epam.jdbc.intro.daoImpl.FriendshipsDaoImpl;
import com.epam.jdbc.intro.daoImpl.LikesDaoImpl;
import com.epam.jdbc.intro.daoImpl.PostDaoImpl;
import com.epam.jdbc.intro.daoImpl.UserDaoImpl;
import com.epam.jdbc.intro.entity.Friendships;
import com.epam.jdbc.intro.entity.Like;
import com.epam.jdbc.intro.entity.Post;
import com.epam.jdbc.intro.entity.User;
import com.epam.jdbc.intro.util.MySqlUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.epam.jdbc.intro.util.MySqlUtils.getConnection;

public class MyFirstConnection {
    private static final Logger logger = Logger.getLogger(MyFirstConnection.class);
    private static UserDao userDao = new UserDaoImpl();
    private static PostDao postDao = new PostDaoImpl();
    private static LikesDao likesDao = new LikesDaoImpl();
    private static FriendshipsDao friendshipsDao = new FriendshipsDaoImpl();

    private static final int MONTHS = 12;

    public static void main(String[] args) {
        init();
        populate();
        destroy();
    }
    
    private static void populate() {
        int usersCount = addUsers();
        int postsCount = addPosts(usersCount);

        addLikes(usersCount, postsCount);
        addFriends(usersCount);
        logger.info(String.format("Создано пользователей: %s", usersCount));
        logger.info(String.format("Создано постов: %s", postsCount));
    }

    private static int addUsers() {
        int usersCount = 0;
        for (int i = 0; i < MONTHS; i++) {
            int usersPerMonth = RandomUtils.nextInt(70, 300);
            addUsers(usersPerMonth);
            usersCount += usersPerMonth;
        }
        return usersCount;
    }

    private static int addPosts(int usersCount) {
        int postsCount = 0;
        for (int i = 0; i < MONTHS; i++) {
            int postsPerMonth = RandomUtils.nextInt(0, 3);
            addPosts(postsPerMonth, usersCount);
            postsCount += postsPerMonth;
        }
        return postsCount;
    }

    private static void addUsers(int count) {
        for (int i = 1; i <= count; i++) {
            User user = new User();
            user.setName("name" + i);
            user.setSurName("surname" + i);
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.set(Calendar.YEAR, RandomUtils.nextInt(1970, 2002));
            user.setBirthday(calendar.getTime());

            addUser(user);
        }
    }

    private static void addPosts(int postCount, int usersCount) {
        for (int i = 1; i <= usersCount; i++) {
            for (int j = 1; j <= postCount; j++) {
                Post post = new Post();
                post.setUserId(i);
                post.setText(String.format("text for post. ID author: %s", j));

                User postedUser = getUser(i);
                Calendar calendar = GregorianCalendar.getInstance();
                calendar.setTime(postedUser.getBirthday());
                calendar.set(Calendar.YEAR, RandomUtils.nextInt(calendar.get(Calendar.YEAR), 2002));

                post.setDate(calendar.getTime());
                addPost(post);
            }
        }
    }

    private static void addLikes(int usersCount, int postsCount) {
        for (int i = 1; i <= usersCount; i++) {
            for (int j = 1; j <= postsCount; j++) {
                boolean isUserLikedPost = BooleanUtils.toBoolean(RandomUtils.nextBoolean());

                if (isUserLikedPost) {
                    Like like = new Like();
                    like.setUserId(i);
                    like.setPostId(j);
                    like.setDate(new Date());

                    addLike(like);
                }
            }
        }
    }

    private static void addFriends(int usersCount) {
        for (int i = 1; i <= usersCount; i++) {
            for (int j = 2; j <= usersCount; j++) {
                boolean isUsersFriends = BooleanUtils.toBoolean(RandomUtils.nextBoolean()) && i != j;

                if (isUsersFriends) {
                    Friendships friend = new Friendships();
                    friend.setUserIdFirst(i);
                    friend.setUserIdSecond(j);
                    friend.setDate(new Date());

                    addFriend(friend);
                }
            }
        }
    }

    private static void addFriend(Friendships friendships) {
        try (Connection connection = getConnection()) {
            friendshipsDao.addFriendship(connection, friendships);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    private static void addLike(Like like) {
        try (Connection connection = getConnection()) {
            likesDao.addLike(connection, like);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    private static void addPost(Post post) {
        try (Connection connection = getConnection()) {
            postDao.addPost(connection, post);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    private static void addUser(User user) {
        try (Connection connection = getConnection()) {
            userDao.addUser(connection, user);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    private static User getUser(int id) {
        try (Connection connection = getConnection()) {
            return userDao.getUser(connection, id);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    private static Post getPost(int id) {
        try (Connection connection = getConnection()) {
            return postDao.getPost(connection, id);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    private static void init() {
        try (Connection con = getConnection()) {
            try (Statement st = con.createStatement()) {
                st.execute(MySqlUtils.CREATE_SCHEMA);
                st.execute(MySqlUtils.CREATE_FRIENDSHIPS);
                st.execute(MySqlUtils.CREATE_LIKES);
                st.execute(MySqlUtils.CREATE_POSTS);
                st.execute(MySqlUtils.CREATE_USERS);
                st.execute(MySqlUtils.CREATE_TABLE);
                st.execute(MySqlUtils.ADD_LIKE_SP);
                st.execute(MySqlUtils.ADD_POST_SP);
                st.execute(MySqlUtils.ADD_USER_SP);
                st.execute(MySqlUtils.ADD_FRIENDSHIP_SP);
                st.execute(MySqlUtils.GET_LIKES_SP);
                st.execute(MySqlUtils.GET_FRIENDSHIP_SP);
                st.execute(MySqlUtils.GET_POST_ID_SP);
                st.execute(MySqlUtils.GET_USER_ID_SP);
                st.execute(MySqlUtils.GET_POST_SP);
                st.execute(MySqlUtils.GET_USER_SP);
                st.execute(MySqlUtils.DELETE_LIKE_SP);
                st.execute(MySqlUtils.DELETE_FRIENDSHIP_SP);
                st.execute(MySqlUtils.UPDATE_USER_SP);
                st.execute(MySqlUtils.DELETE_USER_SP);
                st.execute(MySqlUtils.UPDATE_POST_SP);
                st.execute(MySqlUtils.DELETE_POST_SP);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }
    
    private static void destroy() {
        try (Connection con = getConnection()) {
            try (Statement st = con.createStatement()) {
                st.execute(MySqlUtils.DROP_ADD_POST_SQL);
                st.execute(MySqlUtils.DROP_GET_POST_ID_SQL);
                st.execute(MySqlUtils.DROP_GET_POST_SQL);
                st.execute(MySqlUtils.DROP_UPDATE_POST_SQL);
                st.execute(MySqlUtils.DROP_DELETE_POST_SQL);
                st.execute(MySqlUtils.DROP_ADD_FRIENDSHIP_SQL);
                st.execute(MySqlUtils.DROP_GET_FRIENDSHIP_SQL);
                st.execute(MySqlUtils.DROP_DELETE_FRIENDSHIP_SQL);
                st.execute(MySqlUtils.DROP_ADD_USER_SQL);
                st.execute(MySqlUtils.DROP_GET_USER_ID_SQL);
                st.execute(MySqlUtils.DROP_GET_USER_SQL);
                st.execute(MySqlUtils.DROP_UPDATE_USER_SQL);
                st.execute(MySqlUtils.DROP_DELETE_USER_SQL);
                st.execute(MySqlUtils.DROP_ADD_LIKE_SQL);
                st.execute(MySqlUtils.DROP_GET_LIKE_SQL);
                st.execute(MySqlUtils.DROP_DELETE_LIKES_SQL);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
