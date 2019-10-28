package com.epam.jdbc;

import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.testng.IObjectFactory;
import org.testng.annotations.*;

import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;

@PrepareForTest({Client.class, ConnectionPool.class})
public class ClientTest {
    private Client client;
    private ConnectionPool pool;

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new org.powermock.modules.testng.PowerMockObjectFactory();
    }

    @BeforeMethod
    public void init() {
        pool = mock(ConnectionPool.class);
        client = mock(Client.class);
    }

    @AfterClass
    public void destroy() {
        pool = null;
        client = null;
    }

    @Test
    public void createClientTest() throws Exception {
        PowerMockito.mockStatic(ConnectionPool.class);
        PowerMockito.mockStatic(Client.class);
        whenNew(Client.class).withAnyArguments().thenReturn(client);
        new Client(pool);
        verifyNew(Client.class).withArguments(pool);
        client.start();
        verify(client).start();
    }
}
