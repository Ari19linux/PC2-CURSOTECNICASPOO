package com.hospital.model;
import java.time.LocalDate;
//Soporta RF-04, RF-05, RF-06
public class Medico {
    private int idMedico;
    private String cmp;
    private String nombres;
    private String apellidos;
    private int idEspecialidad; 
    private LocalDate fechaContratacion;
    private String estado;

    public int getIdMedico() { return idMedico; }
    public void setIdMedico(int id) { this.idMedico = id; }
    public String getCmp() { return cmp; }
    public void setCmp(String cmp) { this.cmp = cmp; }
    public String getNombres() { return nombres; }
    public void setNombres(String nom) { this.nombres = nom; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String ape) { this.apellidos = ape; }
    public int getIdEspecialidad() { return idEspecialidad; }
    public void setIdEspecialidad(int idEsp) { this.idEspecialidad = idEsp; }
    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fecha) { this.fechaContratacion = fecha; }
    public String getEstado() { return estado; }
    public void setEstado(String est) { this.estado = est; }
}