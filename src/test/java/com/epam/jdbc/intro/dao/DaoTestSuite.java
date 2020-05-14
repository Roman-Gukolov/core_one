package com.epam.jdbc.intro.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserDaoTest.class,
        PostDaoTest.class,
        LikeDaoTest.class,
        FriendshipDaoTest.class
})
public class DaoTestSuite {
}
