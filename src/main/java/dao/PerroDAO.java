package dao;

import config.ConexionDB;
import enums.Edad;
import enums.EstadoAnimal;
import enums.Porte;
import enums.Temperamento;
import estructuras.Adoptante;
import estructuras.Perro;
import excepciones.AccesoDatosException;
import excepciones.ElementoNoEncontradoExcepcion;
import interfaces.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PerroDAO implements CRUD<Perro> {

    private static final String SQL_INSERTAR = "INSERT INTO perros( id_perro, nombre, edad, fecha_entrada, estado, raza, porte, temperamento) VALUES ( ?,?,?,?,?,?, ?, ?)";
    private static final String SQL_LISTAR = "SELECT * FROM perros ORDER BY id_perro";
    private static final String SQL_BUSCAR = "SELECT * FROM perros WHERE id_perro = ?";
    private static final String SQL_ACTUALIZAR =  "UPDATE perros SET nombre = ?, edad = ?, fecha_entrada = ?, estado = ?, raza = ? , porte = ? , temperamento = ? WHERE id_perro = ?";
    private static final String SQL_ELIMINAR = "DELETE FROM perros WHERE id_perro = ?";

    @Override
    public void crear(Perro objeto) throws AccesoDatosException {
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_INSERTAR)) {
            ps.setInt(1, objeto.getId());
            ps.setString(2, objeto.getNombre());
            ps.setString(3, objeto.getEdad().name());
            ps.setDate(4, java.sql.Date.valueOf(objeto.getFechaEntrada()));
            ps.setString(5, objeto.getEstado().name());
            ps.setString(6, objeto.getRaza());
            ps.setString(7, objeto.getPorte().name());
            ps.setString(8, objeto.getTemperamento().name());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException(
                    "Error al cargar el perro " + objeto.getId(), e);
        }
    }

    @Override
    public List<Perro> mostrarTodos() throws AccesoDatosException {
        List<Perro> lista = new ArrayList<>();
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_LISTAR);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(crearPerro(rs));
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al listar las Perros", e);
        }
        return lista;
    }

    @Override
    public Perro buscarPorId(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_BUSCAR)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return crearPerro(rs);
                }
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al buscar el Perro" + id , e);
        }
        throw new ElementoNoEncontradoExcepcion("Perro", id);
    }

    @Override
    public void modificar(int id, Perro objeto) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        int filas;
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_ACTUALIZAR)) {
            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getEdad().name());
            ps.setDate(3, java.sql.Date.valueOf(objeto.getFechaEntrada()));
            ps.setString(4, objeto.getEstado().name());
            ps.setString(5, objeto.getRaza());
            ps.setString(6, objeto.getPorte().name());
            ps.setString(7, objeto.getTemperamento().name());
            ps.setInt(8, id);
            filas = ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al actualizar el perro " + id, e);
        }
        if (filas == 0) {
            throw new ElementoNoEncontradoExcepcion("Perro", id);
        }
    }

    @Override
    public void eliminar(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        int filas;
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_ELIMINAR)){
            ps.setInt(1, id);
            filas = ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al Eliminar  Perro" + id, e);
        }
        if (filas == 0) {
            throw new ElementoNoEncontradoExcepcion("Perro", id);
        }
    }

    private Perro crearPerro(ResultSet rs) throws SQLException {
        int id = rs.getInt("id_perro");
        String nombre = rs.getString("nombre");
        Edad edad = Edad.valueOf(rs.getString("edad"));
        LocalDate fechaEntrada = rs.getDate("fecha_entrada").toLocalDate();
        EstadoAnimal estado = EstadoAnimal.valueOf(rs.getString("estado"));
        String raza = rs.getString("raza");
        Porte porte = Porte.valueOf(rs.getString("porte"));
        Temperamento temperamento = Temperamento.valueOf(rs.getString("temperamento"));

        Perro perro = new Perro(id, nombre, edad, fechaEntrada, estado, raza, porte, temperamento);
        return perro;
    }
}
