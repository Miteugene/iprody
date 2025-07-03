package org.example.lesson32.service.actions;

import org.example.lesson32.jdbc.NetworkDao;
import org.example.lesson32.model.DeviceConnection;
import org.example.lesson32.model.Network;
import org.example.lesson32.ui.ConsoleController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NetworkActionTest {
    @Mock
    private ConsoleController consoleController;
    @Mock
    private NetworkDao networkDao;
    @InjectMocks
    private NetworkAction networkAction;
    private Network sampleNetwork;

    @BeforeEach
    void setUp() {
        sampleNetwork = new Network();
        sampleNetwork.setId(1);
        sampleNetwork.setName("Some Network");
        sampleNetwork.setDescription("Some network description");
    }

    @Test
    void testCreateNetwork() throws SQLException {
        when(consoleController.readNewNetwork()).thenReturn(sampleNetwork);
        when(networkDao.save(sampleNetwork)).thenReturn(sampleNetwork);

        networkAction.create();

        verify(consoleController).readNewNetwork();
        verify(networkDao).save(sampleNetwork);
        verify(consoleController).print(sampleNetwork);
    }

    @Test
    void testGetNetworks() throws SQLException {
        when(networkDao.getAllNetworks()).thenReturn(List.of(sampleNetwork));

        networkAction.get();

        verify(networkDao).getAllNetworks();
        verify(consoleController).print(anyList());
    }

    @Test
    void testUpdateWhenNoNetworks() throws SQLException {
        when(networkDao.getAllNetworks()).thenReturn(Collections.emptyList());

        networkAction.update();

        verify(consoleController).printError("Networks not found");
        verify(networkDao, never()).update((Network) any());
    }
}
