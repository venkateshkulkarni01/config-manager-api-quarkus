package com.example.controller;

import com.example.model.Configuration;
import com.example.service.ConfigurationService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConfigurationControllerTest {

    @InjectMocks
    ConfigurationController controller;

    @Mock
    ConfigurationService configurationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllConfigurations() {
        Configuration config = new Configuration();
        List<Configuration> configs = Arrays.asList(config);
        when(configurationService.getAllConfigurations("n", "env")).thenReturn(configs);

        Response response = controller.getAllConfigurations("n", "env");
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(configs, response.getEntity());
    }

    @Test
    void testAddConfiguration() {
        Configuration config = new Configuration();
        when(configurationService.addConfiguration(config)).thenReturn(config);

        Response response = controller.addConfiguration(config);
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals(config, response.getEntity());
    }

    @Test
    void testUpdateConfiguration() {
        Configuration config = new Configuration();
        when(configurationService.updateConfiguration(1L, config)).thenReturn(config);

        Response response = controller.updateConfiguration(1L, config);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(config, response.getEntity());
    }

    @Test
    void testDeleteConfiguration() {
        doNothing().when(configurationService).deleteConfiguration(1L);

        Response response = controller.deleteConfiguration(1L);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        assertNull(response.getEntity());
    }

    @Test
    void testGetConfigurationByNameAndEnv() {
        Configuration config = new Configuration();
        when(configurationService.getConfigurationByNameAndEnv("n", "env")).thenReturn(config);

        Response response = controller.getConfigurationByNameAndEnv("n", "env");
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(config, response.getEntity());
    }
}