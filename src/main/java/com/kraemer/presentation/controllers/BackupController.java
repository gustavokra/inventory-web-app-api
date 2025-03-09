package com.kraemer.presentation.controllers;

import com.kraemer.service.BackupService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("api/v1/backup")
public class BackupController {

    @Inject
    private BackupService service;

    @GET
    @Produces("application/sql")
    @RolesAllowed({ "ADMIN", "USER" })
    public Response getBackup() {
        var backup = service.baixarBackup();

        return Response.ok(backup)
                .header("Content-Disposition", "attachment; filename=\"database_backup.sql\"")
                .build();
    }
}
