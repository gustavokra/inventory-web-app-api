package com.kraemer.presentation.controllers;

import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

import com.kraemer.domain.entities.dto.ClientDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.service.ClientService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/v1/client")
public class ClientController {

    @Inject
    private ClientService service;

    @GET
    @RolesAllowed({ "ADMIN", "USER" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllClients(@HeaderParam EnumDBImpl dbImpl) {
        var allClients = service.findAll(dbImpl);

        return Response.ok(allClients).build();
    }

    @POST
    @RolesAllowed({ "ADMIN" })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(ClientDTO dto, @HeaderParam EnumDBImpl dbImpl) {
        var createdClient = service.create(dto, dbImpl);

        return Response.ok(createdClient).build();
    }

    @PUT
    @RolesAllowed({ "ADMIN" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateClientInfo(
            ClientDTO dto,
            @HeaderParam Long id,
            @HeaderParam EnumDBImpl dbImpl) {

        var clientUpdated = service.updateClientInfo(dto, id, dbImpl);

        return Response.ok(clientUpdated).build();
    }

    @DELETE
    @RolesAllowed({ "ADMIN" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(
            @HeaderParam Long id,
            @HeaderParam EnumDBImpl dbImpl) {

        service.delete(id, dbImpl);

        return Response.ok().build();
    }

}