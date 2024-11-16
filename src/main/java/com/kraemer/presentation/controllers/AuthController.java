package com.kraemer.presentation.controllers;

import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

import com.kraemer.domain.entities.dto.UserCredentialsDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.service.AuthService;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/v1/auth")
public class AuthController {

    @Inject
    private AuthService service;

    @POST
    @Path("/login")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserCredentialsDTO userCredentials, @HeaderParam EnumDBImpl dbImpl) {
        var token = service.login(userCredentials, dbImpl);

        return token != null
                ? Response.ok(token).build()
                : Response.status(Response.Status.UNAUTHORIZED).build();
    }

}
