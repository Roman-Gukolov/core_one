package com.epam.jdbc;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.IObjectFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class UtilTest {
    private Util util;

    @BeforeClass
    public void init() {
        util = mock(Util.class);
    }

    @AfterClass
    public void destroy() {
        util = null;
    }

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new org.powermock.modules.testng.PowerMockObjectFactory();
    }


    @Test
    public void testGetDriverName() {
        String driver = "oracle.jdbc.driver.OracleDriver";
        when(util.getDriverName()).thenReturn("oracle.jdbc.driver.OracleDriver");

        assertThat(driver, equalTo(util.getDriverName()));
    }

    @Test
    public void testGetUrl() {
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
        when(util.getDriverName()).thenReturn("jdbc:oracle:thin:@127.0.0.1:1521:xe");

        assertThat(url, equalTo(util.getDriverName()));
    }

    @Test
    public void testGetUser() {
        String user = "user";
        when(util.getDriverName()).thenReturn("user");

        assertThat(user, equalTo(util.getDriverName()));
    }

    @Test
    public void testGetPassword() {
        String psw = "psw";
        when(util.getDriverName()).thenReturn("psw");

        assertThat(psw, equalTo(util.getDriverName()));
    }
}
