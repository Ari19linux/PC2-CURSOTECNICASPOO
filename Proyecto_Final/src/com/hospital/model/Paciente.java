package com.hospital.model;
import java.time.LocalDate;
//Soporta RF-07, RF-08, RF-09
public class Paciente {
    private int idPaciente;
    private String dni;
    private String nombres;
    private String apellidos;
    private String sexo;
    private LocalDate fechaRegistro;

    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int id) { this.idPaciente = id; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getNombres() { return nombres; }
    public void setNombres(String nom) { this.nombres = nom; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String ape) { this.apellidos = ape; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fecha) { this.fechaRegistro = fecha; }
}