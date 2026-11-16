package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/la_manada_de_blaidd";
    private static final String USUARIO = "root";
    private static final String CLAVE = "@Lucas4654!";

    private ConexionDB() {
    }

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
