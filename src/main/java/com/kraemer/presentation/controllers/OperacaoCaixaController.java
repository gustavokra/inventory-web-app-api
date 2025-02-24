package com.kraemer.presentation.controllers;

import java.util.List;

import com.kraemer.domain.entities.dto.OperacaoCaixaDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.service.OperacaoCaixaService;

import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/v1/operacao-caixa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OperacaoCaixaController {
    
    @Inject
    OperacaoCaixaService service;
    
    @POST
    @RolesAllowed({ "ADMIN", "USER" })
    public Response criar(OperacaoCaixaDTO dto, @HeaderParam EnumDBImpl dbImpl) {
        return Response.ok(service.criar(dto, dbImpl)).build();
    }
    
    @PUT
    @RolesAllowed({ "ADMIN", "USER" })
    public Response atualizar(OperacaoCaixaDTO dto, @PathParam("id") Long id, @HeaderParam EnumDBImpl dbImpl) {
        return Response.ok(service.atualizar(dto, id, dbImpl)).build();
    }
    
    @GET
    @RolesAllowed({ "ADMIN", "USER" })
    public Response buscarTodos(@HeaderParam EnumDBImpl dbImpl) {
        return Response.ok(service.buscarTodos(dbImpl)).build();
    }
    
    @DELETE
    @RolesAllowed({ "ADMIN", "USER" })
    public Response excluir(@PathParam("id") Long id, @HeaderParam EnumDBImpl dbImpl) {
        service.excluir(id, dbImpl);

        return Response.ok().build();
    }
} 