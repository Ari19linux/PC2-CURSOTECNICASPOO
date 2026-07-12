package com.hospital.model;
//Soporta RF-16, RF-17, RF-18
public class Atencion {
    private int idAtencion;
    private int idCita;
    private String diagnostico;
    private String tratamiento;
    private String receta;

    public int getIdAtencion() { return idAtencion; }
    public void setIdAtencion(int id) { this.idAtencion = id; }
    public int getIdCita() { return idCita; }
    public void setIdCita(int id) { this.idCita = id; }
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diag) { this.diagnostico = diag; }
    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String trat) { this.tratamiento = trat; }
    public String getReceta() { return receta; }
    public void setReceta(String rec) { this.receta = rec; }
}