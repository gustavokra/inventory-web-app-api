package com.kraemer.domain.usecases.bakcup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.utils.exception.InventoryAppException;

import jakarta.ws.rs.core.StreamingOutput;

public class BaixarBackup {

    private File backupFile;

    public BaixarBackup(String BACKUP_FILE_PATH) {
        this.backupFile = new File(BACKUP_FILE_PATH);
    }

    public StreamingOutput execute() {
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

        return fileStream;
    }
}
