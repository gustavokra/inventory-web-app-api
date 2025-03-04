package com.kraemer.presentation.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.utils.exception.InventoryAppException;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;

@Path("api/v1/backup")
public class BackupController {

    private static final String BACKUP_FILE_PATH = "/home/gustavo/Documentos/projetos/inventory-web-app-api/data/database";

    @GET
    @Produces("application/sql")
    @RolesAllowed({ "ADMIN", "USER" })
    public Response getBackup() {
        File backupFile = new File(BACKUP_FILE_PATH);

        if (!backupFile.exists()) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_INVALIDO, "O arquivo de backup não foi encontrado.");
        }

        StreamingOutput fileStream = new StreamingOutput() {
            @Override
            public void write(OutputStream output) throws IOException {
                try (FileInputStream inputStream = new FileInputStream(backupFile)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                    output.flush();
                }
            }
        };

        return Response.ok(fileStream)
                .header("Content-Disposition", "attachment; filename=\"database_backup.sql\"")
                .build();
    }
}
