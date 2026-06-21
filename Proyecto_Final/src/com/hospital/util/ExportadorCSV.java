package com.hospital.util;

import com.hospital.model.Paciente;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportadorCSV {
    public static void exportarPacientes(List<Paciente> lista, String ruta) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            writer.write("ID,DNI,NOMBRES,APELLIDOS,SEXO");
            writer.newLine();
            for (Paciente p : lista) {
                writer.write(p.getIdPaciente() + "," + p.getDni() + "," + p.getNombres() + "," + p.getApellidos() + "," + p.getSexo());
                writer.newLine();
            }
        }
    }
}