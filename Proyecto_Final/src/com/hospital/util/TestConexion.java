package com.hospital.util;

import java.sql.Connection;

public class TestConexion {

    public static void main(String[] args) {

        Connection cn = Conexion.conectar();

        if (cn != null) {
            System.out.println("Conectado a MySQL correctamente");
        } else {
            System.out.println("No se pudo conectar");
        }

    }
}