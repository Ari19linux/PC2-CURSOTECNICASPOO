package com.hospital.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://161.132.41.24:3306/hospital";

    private static final String USER = "arianna";

    private static final String PASSWORD = "Arianna@2026!";

    public static Connection conectar() {

        try {

            Connection cn =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            return cn;

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }

}
