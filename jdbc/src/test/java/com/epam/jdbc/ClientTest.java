package com.epam.jdbc;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import static org.powermock.api.mockito.PowerMockito.*;

@PrepareForTest({Client.class, ConnectionPool.class})
public class ClientTest {
    private Client client;
    private ConnectionPool pool;

    @BeforeClass
    public void init() {
        pool = mock(ConnectionPool.class);
        client = mock(Client.class);
    }

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new org.powermock.modules.testng.PowerMockObjectFactory();
    }

    @Test
    public void createClientTest() throws Exception {
        PowerMockito.mockStatic(ConnectionPool.class);
        PowerMockito.mockStatic(Client.class);
        whenNew(Client.class).withAnyArguments().thenReturn(client);
        new Client(pool);
        verifyNew(Client.class).withArguments(pool);
    }
}
