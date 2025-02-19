package com.kraemer.presentation.controllers;

import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

import com.kraemer.domain.entities.dto.TituloDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.service.TituloService;

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

@Path("api/v1/titulo")
public class TituloController {
    
    @Inject
    private TituloService service;

    @GET
    @RolesAllowed({ "ADMIN", "USER" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response encontrarTodas(@HeaderParam EnumDBImpl dbImpl) {
        var titulos = service.encontrarTodos(dbImpl);

        return Response.ok(titulos).build();
    }

    @POST
    @RolesAllowed({ "ADMIN" })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criar(TituloDTO dto, @HeaderParam EnumDBImpl dbImpl) {
        var tituloCriado = service.criar(dto, dbImpl);

        return Response.ok(tituloCriado).build();
    }

    @PUT
    @RolesAllowed({ "ADMIN" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(
            TituloDTO dto,
            @HeaderParam Long id,
            @HeaderParam EnumDBImpl dbImpl) {

        var tituloAtualizado = service.atualizar(dto, id, dbImpl);

        return Response.ok(tituloAtualizado).build();
    }

    @DELETE
    @RolesAllowed({ "ADMIN" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluir(
            @HeaderParam Long id,
            @HeaderParam EnumDBImpl dbImpl) {

        service.excluir(id, dbImpl);

        return Response.ok().build();
    }
}
