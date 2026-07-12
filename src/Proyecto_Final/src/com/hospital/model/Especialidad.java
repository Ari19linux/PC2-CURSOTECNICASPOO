package com.hospital.model;
//Soporta RF-01, RF-02, RF-03
public class Especialidad {
	private int idEspecialidad;
    private String nombreEspecialidad;
    private String description;
    private String estado; 

    public int getIdEspecialidad() { return idEspecialidad; }
    public void setIdEspecialidad(int id) { this.idEspecialidad = id; }
    public String getNombreEspecialidad() { return nombreEspecialidad; }
    public void setNombreEspecialidad(String nombre) { this.nombreEspecialidad = nombre; }
    public String getDescripcion() { return description; }
    public void setDescripcion(String desc) { this.description = desc; }
    public String getEstado() { return estado; }
    public void setEstado(String est) { this.estado = est; }

}
