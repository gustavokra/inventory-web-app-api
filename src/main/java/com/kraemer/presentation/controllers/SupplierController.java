package com.kraemer.presentation.controllers;

import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

import com.kraemer.domain.entities.dto.SupplierDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.service.SupplierService;

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

@Path("api/v1/supplier")
public class SupplierController {
    @Inject
    private SupplierService service;

    @POST
    @RolesAllowed({ "ADMIN", "USER" })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(SupplierDTO dto, @HeaderParam EnumDBImpl dbImpl) {
        var createdSupplier = service.create(dto, dbImpl);

        return Response.ok(createdSupplier).build();
    }

    @GET
    @RolesAllowed({ "ADMIN", "USER" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllSuppliers(@HeaderParam EnumDBImpl dbImpl) {
        var allSuppliers = service.findAll(dbImpl);

        return Response.ok(allSuppliers).build();
    }

    @PUT
    @RolesAllowed({ "ADMIN", "USER" })
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSupplierInfo(
            SupplierDTO dto,
            @HeaderParam Long id,
            @HeaderParam EnumDBImpl dbImpl) {

        var supplierUpdated = service.updateSupplierInfo(dto, id, dbImpl);

        return Response.ok(supplierUpdated).build();
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
