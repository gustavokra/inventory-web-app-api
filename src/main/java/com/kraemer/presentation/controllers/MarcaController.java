package com.kraemer.presentation.controllers;

import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

import com.kraemer.domain.entities.dto.MarcaDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.service.MarcaService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/v1/marca")
public class MarcaController {
    
    @Inject
    private MarcaService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "ADMIN", "USER" })
    public Response encontrarTodas(@HeaderParam EnumDBImpl dbImpl) {

        return Response.ok(service.encontrarTodas(dbImpl)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "ADMIN", "USER" })
    public Response criar(MarcaDTO dto, @HeaderParam EnumDBImpl dbImpl) {

        return Response.ok(service.criar(dto, dbImpl)).build();
    }
}
