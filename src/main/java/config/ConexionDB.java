package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://172.16.1.64:3306/la_manada_de_blaidd";
    private static final String USUARIO = "bdd1";
    private static final String CLAVE = "bdd1";

    private ConexionDB() {
    }

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
