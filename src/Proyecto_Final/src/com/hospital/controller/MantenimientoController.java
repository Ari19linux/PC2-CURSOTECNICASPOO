package com.hospital.controller;

public class MantenimientoController {
    
    // RF-08: Validación estricta del DNI mediante expresiones regulares
    public boolean validarDniFormat(String dni) {
        return dni != null && dni.matches("\\d{8}");
    }

    // RF-17: Validación estricta de campos clínicos obligatorios
    public boolean validarDiagnostico(String diagnostico) {
        return diagnostico != null && !diagnostico.trim().isEmpty();
    }
}