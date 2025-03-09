package com.kraemer.service;

import com.kraemer.domain.usecases.bakcup.BaixarBackup;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.StreamingOutput;

@ApplicationScoped
public class BackupService {

    private static final String BACKUP_FILE_PATH = "/data/database";
    // private static final String BACKUP_FILE_PATH = "/home/gustavo/Documentos/projetos/inventory-web-app-api/data/database";;

    public StreamingOutput baixarBackup() {
        var BaixarBackup = new BaixarBackup(BACKUP_FILE_PATH);
        return BaixarBackup.execute();
    }
}
