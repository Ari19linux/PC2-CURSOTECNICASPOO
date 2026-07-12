package com.hospital.dao;

import com.hospital.model.*;
import com.hospital.controller.MantenimientoController;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.hospital.util.Conexion;

public class HospitalDAO {
    private MantenimientoController validador = new MantenimientoController();

    

    // RF-01: Registrar Especialidad
    public boolean registrarEspecialidad(Especialidad esp) throws SQLException {
        String sql = "INSERT INTO Especialidades (nombre_especialidad, descripcion, estado) VALUES (?, ?, 'Activo')";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, esp.getNombreEspecialidad());
            ps.setString(2, esp.getDescripcion());
            return ps.executeUpdate() > 0;
        }
    }

    // RF-02: Validar Duplicidad de Especialidades
    public boolean existeEspecialidad(String nombre) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Especialidades WHERE nombre_especialidad = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // RF-03: Baja Lógica de Especialidades
    public boolean darBajaEspecialidad(int id) throws SQLException {
        String sql = "UPDATE Especialidades SET estado = 'Inactivo' WHERE id_especialidad = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // RF-04: Registro de Médico con FK
    public boolean registrarMedico(Medico med) throws SQLException {
        String sql = "INSERT INTO Medicos (cmp, nombres, apellidos, id_especialidad, fecha_contratacion, estado) VALUES (?, ?, ?, ?, NOW(), 'Activo')";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, med.getCmp());
            ps.setString(2, med.getNombres());
            ps.setString(3, med.getApellidos());
            ps.setInt(4, med.getIdEspecialidad());
            return ps.executeUpdate() > 0;
        }
    }

    // RF-05: Validar que el CMP del médico sea único
    public boolean existeCmpMedico(String cmp) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Medicos WHERE cmp = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cmp);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // RF-06: Búsqueda avanzada de médicos (Filtro por apellido o especialidad)
    public List<Medico> buscarMedicos(String criterio) throws SQLException {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT m.* FROM Medicos m INNER JOIN Especialidades e ON m.id_especialidad = e.id_especialidad " +
                     "WHERE m.apellidos LIKE ? OR e.nombre_especialidad LIKE ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + criterio + "%");
            ps.setString(2, "%" + criterio + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Medico m = new Medico();
                    m.setIdMedico(rs.getInt("id_medico"));
                    m.setCmp(rs.getString("cmp"));
                    m.setNombres(rs.getString("nombres"));
                    m.setApellidos(rs.getString("apellidos"));
                    lista.add(m);
                }
            }
        }
        return lista;
    }

    // RF-07: Registro Automatizado de Paciente
    public boolean registrarPaciente(Paciente pac) throws SQLException {
        if (!validador.validarDniFormat(pac.getDni())) {
            throw new IllegalArgumentException("DNI no cumple con los 8 dígitos requeridos.");
        }
        String sql = "INSERT INTO Pacientes (dni, nombres, apellidos, sexo, fecha_registro) VALUES (?, ?, ?, ?, NOW())";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pac.getDni());
            ps.setString(2, pac.getNombres());
            ps.setString(3, pac.getApellidos());
            ps.setString(4, pac.getSexo());
            return ps.executeUpdate() > 0;
        }
    }

    // RF-09: Búsqueda de Pacientes por DNI
    public Paciente obtenerPacientePorDni(String dni) throws SQLException {
        String sql = "SELECT * FROM Pacientes WHERE dni = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Paciente p = new Paciente();
                    p.setIdPaciente(rs.getInt("id_paciente"));
                    p.setDni(rs.getString("dni"));
                    p.setNombres(rs.getString("nombres"));
                    p.setApellidos(rs.getString("apellidos"));
                    return p;
                }
            }
        }
        return null;
    }

    // RF-11: Control de Concurrencia (Evitar cruce de horarios en citas)
    public boolean tieneCruceCita(Cita cita) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Citas WHERE id_medico = ? AND fecha_cita = ? AND hora_cita = ? AND estado = 'Pendiente'";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cita.getIdMedico());
            ps.setDate(2, java.sql.Date.valueOf(cita.getFechaCita()));
            ps.setTime(3, java.sql.Time.valueOf(cita.getHoraCita()));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // RF-10: Agendamiento de Citas
    public boolean registrarCita(Cita cita) throws SQLException {
        if (tieneCruceCita(cita)) {
            throw new SQLException("El médico ya cuenta con una cita asignada en esa fecha y bloque horario.");
        }
        String sql = "INSERT INTO Citas (id_paciente, id_medico, fecha_cita, hora_cita, motivo, estado, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, NOW())";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cita.getIdPaciente());
            ps.setInt(2, cita.getIdMedico());
            ps.setDate(3, java.sql.Date.valueOf(cita.getFechaCita()));
            ps.setTime(4, java.sql.Time.valueOf(cita.getHoraCita()));
            ps.setString(5, cita.getMotivo());
            ps.setString(6, cita.getEstado()); 
            return ps.executeUpdate() > 0;
        }
    }

    // RF-13: Cancelación lógica de Citas
    public boolean cancelarCita(int idCita, String usuario) throws SQLException {
        String sql = "UPDATE Citas SET estado = 'Cancelada' WHERE id_cita = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCita);
            boolean ok = ps.executeUpdate() > 0;
            if (ok) {
                com.hospital.util.LogAuditoria.registrarAccion(usuario, "Cancelación de cita ID: " + idCita);
            }
            return ok;
        }
    }

    // RF-16 y RF-14: Registro de Atención y Cambio Automático de Estado de Cita (Transaccional)
    public boolean registrarAtencion(Atencion atn, String usuario) throws SQLException {
        if (!validador.validarDiagnostico(atn.getDiagnostico())) {
            throw new IllegalArgumentException("El campo de diagnóstico clínico es obligatorio.");
        }
        String sqlAtencion = "INSERT INTO Atencion (id_cita, fecha_atencion, diagnostico, tratamiento, receta) VALUES (?, NOW(), ?, ?, ?)";
        String sqlCitaEstado = "UPDATE Citas SET estado = 'Atendida' WHERE id_cita = ?";
        
        Connection conn = null;
        try {
            conn = Conexion.conectar();
            conn.setAutoCommit(false); 

            try (PreparedStatement psA = conn.prepareStatement(sqlAtencion)) {
                psA.setInt(1, atn.getIdCita());
                psA.setString(2, atn.getDiagnostico());
                psA.setString(3, atn.getTratamiento());
                psA.setString(4, atn.getReceta());
                psA.executeUpdate();
            }

            try (PreparedStatement psC = conn.prepareStatement(sqlCitaEstado)) {
                psC.setInt(1, atn.getIdCita());
                psC.executeUpdate();
            }

            conn.commit();
            com.hospital.util.LogAuditoria.registrarAccion(usuario, "Atención registrada para Cita ID: " + atn.getIdCita());
            return true;
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) conn.close();
        }
    }

    // RF-15: Historial Clínico Consolidado
    public List<String> obtenerHistorialClinico(int idPaciente) throws SQLException {
        List<String> historial = new ArrayList<>();
        String sql = "SELECT c.fecha_cita, a.diagnostico FROM Atencion a " +
                     "INNER JOIN Citas c ON a.id_cita = c.id_cita WHERE c.id_paciente = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    historial.add(rs.getDate("fecha_cita") + " - Diag: " + rs.getString("diagnostico"));
                }
            }
        }
        return historial;
    }

    // RF-18: Bloqueo de Modificación de Ficha (Read-Only)
    public boolean esAtencionModificable(int idCita) throws SQLException {
        String sql = "SELECT estado FROM Citas WHERE id_cita = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCita);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return !"Atendida".equals(rs.getString("estado"));
            }
        }
        return true;
    }
}