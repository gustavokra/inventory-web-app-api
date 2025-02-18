package com.kraemer.presentation.controllers;

import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

import com.kraemer.domain.entities.dto.FormaPagamentoDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.service.FormaPagamentoService;

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

@Path("api/v1/forma_pagamento")
public class FormaPagamentoController {

    @Inject
    private FormaPagamentoService service;

    @GET
    @RolesAllowed({ "ADMIN", "USER" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response encontrarTodas(@HeaderParam EnumDBImpl dbImpl) {
        var allFormaPagamentos = service.encontrarTodas(dbImpl);

        return Response.ok(allFormaPagamentos).build();
    }

    @POST
    @RolesAllowed({ "ADMIN" })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criar(FormaPagamentoDTO dto, @HeaderParam EnumDBImpl dbImpl) {
        var createdFormaPagamento = service.criar(dto, dbImpl);

        return Response.ok(createdFormaPagamento).build();
    }

    @PUT
    @RolesAllowed({ "ADMIN" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(
            FormaPagamentoDTO dto,
            @HeaderParam Long id,
            @HeaderParam EnumDBImpl dbImpl) {

        var supplierUpdated = service.atualizar(dto, id, dbImpl);

        return Response.ok(supplierUpdated).build();
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
