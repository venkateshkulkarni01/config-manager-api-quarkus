package com.example.service;

import com.example.model.Configuration;
import com.example.repository.ConfigurationRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ConfigurationService {

    @Inject
    ConfigurationRepository configurationRepository;

    public List<Configuration> getAllConfigurations(String name, String environment) {
        return configurationRepository.findAll(name, environment);
    }

    @Transactional
    public Configuration addConfiguration(Configuration configuration) {
        configurationRepository.persist(configuration);
        return configuration;
    }

    @Transactional
    public Configuration updateConfiguration(Long id, Configuration configuration) {
        Configuration existing = configurationRepository.findById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Configuration not found");
        }
        existing.setName(configuration.getName());
        existing.setValue(configuration.getValue());
        existing.setEnvironment(configuration.getEnvironment());
        existing.setDescription(configuration.getDescription());
        // Add other fields as needed
        return existing;
    }

    @Transactional
    public void deleteConfiguration(Long id) {
        configurationRepository.deleteById(id);
    }

    public Configuration getConfigurationByNameAndEnv(String name, String environment) {
        return configurationRepository.findByNameAndEnv(name, environment);
    }
}