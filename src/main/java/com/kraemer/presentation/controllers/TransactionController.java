package com.kraemer.presentation.controllers;

import com.kraemer.service.TransactionService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/v1/transaction")
public class TransactionController {
    
    @Inject
    private TransactionService service;

    @GET
    @RolesAllowed({"ADMIN", "USER"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(service.findAll()).build();
    }

}
