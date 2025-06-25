package com.example.controller;

import com.example.model.Configuration;
import com.example.service.ConfigurationService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST controller for managing configuration entries.
 * Provides endpoints for CRUD operations and querying by name/environment.
 */
@Path("/configurations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConfigurationController {

    @Inject
    ConfigurationService configurationService;

    /**
     * Retrieves all configurations, optionally filtered by name and environment.
     * @param name Optional configuration name filter.
     * @param environment Optional environment filter.
     * @return List of configurations.
     */
    @GET
    public Response getAllConfigurations(@QueryParam("name") String name, @QueryParam("environment") String environment) {
        List<Configuration> configurations = configurationService.getAllConfigurations(name, environment);
        return Response.ok(configurations).build();
    }

    /**
     * Adds a new configuration entry.
     * @param configuration The configuration to add.
     * @return The created configuration.
     */
    @POST
    public Response addConfiguration(Configuration configuration) {
        Configuration createdConfiguration = configurationService.addConfiguration(configuration);
        return Response.status(Response.Status.CREATED).entity(createdConfiguration).build();
    }

     /**
     * Updates an existing configuration entry by ID.
     * @param id The ID of the configuration to update.
     * @param configuration The updated configuration data.
     * @return The updated configuration.
     */
    @PUT
    @Path("/{id}")
    public Response updateConfiguration(@PathParam("id") Long id, Configuration configuration) {
        Configuration updatedConfiguration = configurationService.updateConfiguration(id, configuration);
        return Response.ok(updatedConfiguration).build();
    }

    /**
     * Deletes a configuration entry by ID.
     * @param id The ID of the configuration to delete.
     * @return No content response.
     */
    @DELETE
    @Path("/{id}")
    public Response deleteConfiguration(@PathParam("id") Long id) {
        configurationService.deleteConfiguration(id);
        return Response.noContent().build();
    }

    /**
     * Retrieves a configuration by name and environment.
     * @param name The configuration name.
     * @param environment The environment.
     * @return The matching configuration, if found.
     */
    @GET
    @Path("/by-name-env")
    public Response getConfigurationByNameAndEnv(@QueryParam("name") String name, @QueryParam("environment") String environment) {
        Configuration configuration = configurationService.getConfigurationByNameAndEnv(name, environment);
        return Response.ok(configuration).build();
    }
}