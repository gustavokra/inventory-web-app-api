package com.kraemer.presentation.controllers;

import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

import com.kraemer.domain.entities.dto.OrderDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.service.OrderService;

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

@Path("api/v1/order")
public class OrderController {

    @Inject
    private OrderService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "ADMIN", "USER" })
    public Response findAll(@HeaderParam EnumDBImpl dbImpl) {
        var allOrdersSortedByCreatedAt = service.findAll(dbImpl);

        return Response.ok(allOrdersSortedByCreatedAt).build();
    }

    @POST
    @RolesAllowed({ "ADMIN", "USER" })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(OrderDTO dto, @HeaderParam EnumDBImpl dbImpl) {
        var createdOrder = service.create(dto, dbImpl);

        return Response.ok(createdOrder).build();
    }

    @PUT
    @RolesAllowed({ "ADMIN", "USER" })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(OrderDTO dto, @HeaderParam Long id, @HeaderParam EnumDBImpl dbImpl) {
        var updatedOrder = service.update(dto, id, dbImpl);

        return Response.ok(updatedOrder).build();
    }

    @DELETE
    @RolesAllowed({ "ADMIN" })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@HeaderParam Long id, @HeaderParam EnumDBImpl dbImpl) {
        service.delete(id, dbImpl);

        return Response.ok().build();
    }

}
