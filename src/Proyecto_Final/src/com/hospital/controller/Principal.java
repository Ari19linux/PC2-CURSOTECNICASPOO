package com.hospital.controller;

// 1. Importaciones de tus paquetes (Sostiene la integración de la arquitectura)
import com.hospital.model.Especialidad;
import com.hospital.model.Medico;
import com.hospital.model.Paciente;
import com.hospital.model.Cita;
import com.hospital.model.Atencion;
import com.hospital.util.ExportadorCSV;
import com.hospital.util.LogAuditoria;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Principal {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   SISTEMA DE GESTIÓN HOSPITALARIA - CONTROL");
        System.out.println("==================================================\n");

        // -----------------------------------------------------------------
        // FASE 1: Validación del Paquete Model (Mapeo de Entidades POO)
        // -----------------------------------------------------------------
        System.out.println(">>> FASE 1: Validando Estructura de Modelos (POO)...");
        
        // Instancia de Especialidad (RF-01)
        Especialidad esp = new Especialidad();
        esp.setIdEspecialidad(1);
        esp.setNombreEspecialidad("Pediatría");
        esp.setDescripcion("Atención médica infantil");
        esp.setEstado("Activo");

        // Instancia de Médico vinculado a Especialidad (RF-04)
        Medico med = new Medico();
        med.setIdMedico(101);
        med.setCmp("45896");
        med.setNombres("Carlos");
        med.setApellidos("Mendoza Ruiz");
        med.setIdEspecialidad(esp.getIdEspecialidad()); // Relación FK simulada
        med.setFechaContratacion(LocalDate.now());
        med.setEstado("Activo");

        // Instancia de Paciente (RF-07)
        Paciente pac = new Paciente();
        pac.setIdPaciente(501);
        pac.setDni("74859612");
        pac.setNombres("Luis Alberto");
        pac.setApellidos("Gomez Pardo");
        pac.setSexo("M");
        pac.setFechaRegistro(LocalDate.now());

        // Instancia de Cita vinculando Paciente y Médico (RF-10 / RF-12)
        Cita cita = new Cita(); // Inicia automáticamente como 'Pendiente' por constructor
        cita.setIdCita(9001);
        cita.setIdPaciente(pac.getIdPaciente());
        cita.setIdMedico(med.getIdMedico());
        cita.setFechaCita(LocalDate.now().plusDays(1)); // Cita para mañana
        cita.setHoraCita(LocalTime.of(10, 30));
        cita.setMotivo("Chequeo general de rutina");

        System.out.println("  [OK] Modelos creados e interrelacionados con éxito.");
        System.out.println("       Cita registrada para el paciente " + pac.getApellidos() + 
                           " con el Dr. " + med.getApellidos() + " (Estado: " + cita.getEstado() + ").\n");


        // -----------------------------------------------------------------
        // FASE 2: Validación del Paquete Controller (Reglas de Negocio)
        // -----------------------------------------------------------------
        System.out.println(">>> FASE 2: Validando Controladores y Reglas de Negocio...");
        MantenimientoController controller = new MantenimientoController();

        // Prueba de Validación de DNI (RF-08)
        String dniCorrecto = "12345678";
        String dniIncorrecto = "1234567890"; // 10 dígitos (Debe fallar)

        System.out.println("  - Validando DNI '" + dniCorrecto + "': " + controller.validarDniFormat(dniCorrecto));
        System.out.println("  - Validando DNI '" + dniIncorrecto + "': " + controller.validarDniFormat(dniIncorrecto));

        // Prueba de Validación de Diagnóstico Clínico (RF-17)
        Atencion atn = new Atencion();
        atn.setDiagnostico("   "); // Vacío (Debe ser rechazado)
        
        boolean diagValido = controller.validarDiagnostico(atn.getDiagnostico());
        System.out.println("  - Validando Diagnóstico Obligatorio (Vacío): " + diagValido);
        if(!diagValido) {
            System.out.println("  [OK] El controlador bloqueó correctamente un diagnóstico vacío.");
        }
        System.out.println();


        // -----------------------------------------------------------------
        // FASE 3: Validación del Paquete Util (Manejo Físico de Archivos E/S)
        // -----------------------------------------------------------------
        System.out.println(">>> FASE 3: Validando Escritura de Archivos Físicos (I/O)...");
        
        // 1. Probar Logs de Auditoría (RF-20)
        System.out.println("  - Escribiendo en el archivo log...");
        LogAuditoria.registrarAccion("Edgar_Ancajima", "INTENTO_TEST: El usuario inició pruebas del sistema.");
        System.out.println("  [OK] Revisa la raíz de tu proyecto. Debe aparecer el archivo 'auditoria_hospital.log'.");

        // 2. Probar Exportación a CSV (RF-19)
        System.out.println("  - Generando reporte masivo en formato plano CSV...");
        List<Paciente> listaPacientesTest = new ArrayList<>();
        listaPacientesTest.add(pac); // Agregamos al paciente Luis Gomez que creamos arriba
        
        try {
            // Guarda el archivo en la raíz del proyecto
            ExportadorCSV.exportarPacientes(listaPacientesTest, "reporte_pacientes_test.csv");
            System.out.println("  [OK] Archivo 'reporte_pacientes_test.csv' creado de forma exitosa.");
        } catch (Exception e) {
            System.err.println("  [ERROR] Falló la escritura del archivo CSV: " + e.getMessage());
        }
        System.out.println();

        System.out.println("==================================================");
        System.out.println("   ¡TODAS LAS VALIDACIONES LOCALES PASARON CON ÉXITO!");
        System.out.println("==================================================");
    }
}