package com.example.repository;

import com.example.model.Configuration;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ConfigurationRepository implements PanacheRepository<Configuration> {

    public List<Configuration> findAll(String name, String environment) {
        if (name != null && environment != null) {
            return list("name = ?1 and environment = ?2", name, environment);
        } else if (name != null) {
            return list("name = ?1", name);
        } else if (environment != null) {
            return list("environment = ?1", environment);
        } else {
            return listAll();
        }
    }

    public Configuration findByNameAndEnv(String name, String environment) {
        return find("name = ?1 and environment = ?2", name, environment).firstResult();
    }
}