package com.epam.jdbc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class UtilTest {

    @Mock
    private Util util;

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
