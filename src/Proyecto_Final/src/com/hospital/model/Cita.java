package com.hospital.model;
import java.time.LocalDate;
import java.time.LocalTime;
//Soporta RF-10, RF-11, RF-12, RF-13, RF-14, RF-15
public class Cita {
    private int idCita;
    private int idPaciente;
    private int idMedico;
    private LocalDate fechaCita;
    private LocalTime horaCita;
    private String motivo;
    private String estado; 

    public Cita() {
        this.estado = "Pendiente"; // RF-12: Estado por defecto forzado por software
    }

    public int getIdCita() { return idCita; }
    public void setIdCita(int id) { this.idCita = id; }
    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int id) { this.idPaciente = id; }
    public int getIdMedico() { return idMedico; }
    public void setIdMedico(int id) { this.idMedico = id; }
    public LocalDate getFechaCita() { return fechaCita; }
    public void setFechaCita(LocalDate fecha) { this.fechaCita = fecha; }
    public LocalTime getHoraCita() { return horaCita; }
    public void setHoraCita(LocalTime hora) { this.horaCita = hora; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String mot) { this.motivo = mot; }
    public String getEstado() { return estado; }
    public void setEstado(String est) { this.estado = est; }
}