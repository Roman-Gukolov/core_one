package com.epam.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Client.class, ConnectionPool.class})
public class ClientTest {

    @Mock
    private Client client;

    @Mock
    private ConnectionPool pool;

    @Test
    public void createClientTest() throws Exception {
        whenNew(Client.class).withAnyArguments().thenReturn(client);
        new Client(pool);
        verifyNew(Client.class).withArguments(pool);
    }
}
