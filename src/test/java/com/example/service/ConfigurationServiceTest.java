package com.example.service;

import com.example.model.Configuration;
import com.example.repository.ConfigurationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// src/test/java/com/example/service/ConfigurationServiceTest.java

class ConfigurationServiceTest {

    private ConfigurationRepository configurationRepository;
    private ConfigurationService configurationService;

    @BeforeEach
    void setUp() {
        configurationRepository = mock(ConfigurationRepository.class);
        configurationService = new ConfigurationService();
        configurationService.configurationRepository = configurationRepository;
    }

    @Test
    void testGetAllConfigurations() {
        Configuration config1 = new Configuration();
        Configuration config2 = new Configuration();
        List<Configuration> configs = Arrays.asList(config1, config2);

        when(configurationRepository.findAll("name", "env")).thenReturn(configs);

        List<Configuration> result = configurationService.getAllConfigurations("name", "env");
        assertEquals(2, result.size());
        assertSame(config1, result.get(0));
        assertSame(config2, result.get(1));
        verify(configurationRepository).findAll("name", "env");
    }

    @Test
    void testAddConfiguration() {
        Configuration config = new Configuration();
        Configuration result = configurationService.addConfiguration(config);
        assertSame(config, result);
        verify(configurationRepository).persist(config);
    }

    @Test
    void testUpdateConfiguration() {
        Configuration existing = new Configuration();
        existing.setName("old");
        existing.setValue("oldValue");
        existing.setEnvironment("oldEnv");
        existing.setDescription("oldDesc");

        Configuration update = new Configuration();
        update.setName("new");
        update.setValue("newValue");
        update.setEnvironment("newEnv");
        update.setDescription("newDesc");

        when(configurationRepository.findById(1L)).thenReturn(existing);

        Configuration result = configurationService.updateConfiguration(1L, update);

        assertSame(existing, result);
        assertEquals("new", existing.getName());
        assertEquals("newValue", existing.getValue());
        assertEquals("newEnv", existing.getEnvironment());
        assertEquals("newDesc", existing.getDescription());
        verify(configurationRepository).findById(1L);
    }

    // @Test
    // void testUpdateConfiguration_NotFound() {
    //     when(configurationRepository.findById(2L)).thenReturn(null);
    //     Configuration update = new Configuration();
    //     Exception ex = assertThrows(IllegalArgumentException.class,
    //             () -> configurationService.updateConfiguration(2L, update));
    //     assertEquals("Configuration not found", ex.getMessage());
    // }

    @Test
    void testDeleteConfiguration() {
        configurationService.deleteConfiguration(3L);
        verify(configurationRepository).deleteById(3L);
    }

    @Test
    void testGetConfigurationByNameAndEnv() {
        Configuration config = new Configuration();
        when(configurationRepository.findByNameAndEnv("name", "env")).thenReturn(config);

        Configuration result = configurationService.getConfigurationByNameAndEnv("name", "env");
        assertSame(config, result);
        verify(configurationRepository).findByNameAndEnv("name", "env");
    }
}