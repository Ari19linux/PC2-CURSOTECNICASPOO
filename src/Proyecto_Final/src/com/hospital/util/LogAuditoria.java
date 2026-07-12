package com.hospital.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogAuditoria {
    private static final String PATH = "auditoria_hospital.log";

    public static void registrarAccion(String usuario, String accion) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH, true))) {
            writer.write("[" + LocalDateTime.now() + "] USER: " + usuario + " -> " + accion);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error de entrada/salida de archivos: " + e.getMessage());
        }
    }
}