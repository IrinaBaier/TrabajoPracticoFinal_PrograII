
package dao;

import config.ConexionDB;
import estructuras.Observaciones;
import excepciones.AccesoDatosException;
import excepciones.ElementoNoEncontradoExcepcion;
import interfaces.CRUD;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ObservacionesDAO implements CRUD<Observaciones> {

    private static final String SQL_INSERTAR ="INSERT INTO observaciones (id_observacion, id_adopcion, fecha, descripcion) VALUES (?, ?, ?, ?)";
    private static final String SQL_LISTAR = "SELECT * FROM observaciones ORDER BY id_observacion";
    private static final String SQL_BUSCAR = "SELECT * FROM observaciones WHERE id_observacion = ?";
    private static final String SQL_ACTUALIZAR = "UPDATE observaciones SET descripcion = ? WHERE id_observacion = ?";
    private static final String SQL_ELIMINAR = "DELETE FROM observaciones WHERE id_observacion = ?";


    @Override
    public void crear(Observaciones objeto) throws AccesoDatosException {
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_INSERTAR)) {
            ps.setInt(1, objeto.getID());
            ps.setInt(2, objeto.getIdAdopcion());
            ps.setObject(3, objeto.getFecha());
            ps.setString(4, objeto.getDescripcion());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException(
                    "Error al cargar Observación " + objeto.getID(), e);
        }
    }


    @Override
    public List<Observaciones> mostrarTodos() throws AccesoDatosException {
        List<Observaciones> lista = new ArrayList<>();
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_LISTAR);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(crearObservacion(rs));
            }
        } catch (SQLException e) {
            throw new AccesoDatosException(
                    "Error al listar Observaciones", e);
        }
        return lista;
    }


    @Override
    public Observaciones buscarPorId(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_BUSCAR)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return crearObservacion(rs);
                }
            }
        } catch (SQLException e) {
            throw new AccesoDatosException(
                    "Error al buscar Observación " + id, e);
        }
        throw new ElementoNoEncontradoExcepcion("Observación", id);
    }


    @Override
    public void modificar(int id, Observaciones objeto) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        int filas;
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_ACTUALIZAR)) {
            ps.setString(1, objeto.getDescripcion());
            ps.setInt(2, id);
            filas = ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException(
                    "Error al actualizar Observación " + id, e);
        }
        if (filas == 0) {
            throw new ElementoNoEncontradoExcepcion("Observación", id);
        }
    }


    @Override
    public void eliminar(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        int filas;
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_ELIMINAR)) {
            ps.setInt(1, id);
            filas = ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException(
                    "Error al eliminar Observación " + id, e);
        }
        if (filas == 0) {
            throw new ElementoNoEncontradoExcepcion("Observación", id);
        }
    }


    private Observaciones crearObservacion(ResultSet rs) throws SQLException {
        int id = rs.getInt("id_observacion");
        int idAdopcion = rs.getInt("id_adopcion");
        LocalDateTime fecha = rs.getObject("fecha", LocalDateTime.class);
        String descripcion = rs.getString("descripcion");
        Observaciones observacion = new Observaciones( id, idAdopcion, fecha, descripcion);
        return observacion;
    }
}
