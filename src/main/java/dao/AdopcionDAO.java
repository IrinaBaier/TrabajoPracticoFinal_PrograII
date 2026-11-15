package dao;

import config.ConexionDB;
import estructuras.Adopcion;
import excepciones.AccesoDatosException;
import excepciones.ElementoNoEncontradoExcepcion;
import interfaces.CRUD;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdopcionDAO implements CRUD<Adopcion> {
    private static final String SQL_INSERTAR ="INSERT INTO adopciones (id_adopcion, id_adoptante, id_perro, id_solicitud, fecha) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_LISTAR = "SELECT * FROM adopciones ORDER BY id_adopcion";
    private static final String SQL_BUSCAR ="SELECT * FROM adopciones WHERE id_adopcion = ?";
    private static final String SQL_ACTUALIZAR ="UPDATE adopciones SET id_adoptante = ?, id_perro = ?, id_solicitud = ?, fecha = ? WHERE id_adopcion = ?";
    private static final String SQL_ELIMINAR = "DELETE FROM adopciones WHERE id_adopcion = ?";


    @Override
    public void crear(Adopcion objeto) throws AccesoDatosException {

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_INSERTAR)) {
            ps.setInt(1, objeto.getID());
            ps.setInt(2, objeto.getIdAoptante());
            ps.setInt(3, objeto.getIdPerro());
            if (objeto.getIdSolicitud() != null) {
                ps.setInt(4, objeto.getIdSolicitud());
            } else {
                ps.setNull(4, Types.INTEGER);
            }
            ps.setTimestamp(5, Timestamp.valueOf(objeto.getFecha()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException(
                    "Error al cargar Adopcion " + objeto.getID(), e);
        }
    }


    @Override
    public List<Adopcion> mostrarTodos() throws AccesoDatosException {
        List<Adopcion> lista = new ArrayList<>();
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_LISTAR);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(crearAdopcion(rs));
            }
        } catch (SQLException e) {
            throw new AccesoDatosException(
                    "Error al listar Adopciones", e);
        }
        return lista;
    }


    @Override
    public Adopcion buscarPorId(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_BUSCAR)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return crearAdopcion(rs);
                }
            }
        } catch (SQLException e) {
            throw new AccesoDatosException(
                    "Error al buscar Adopcion " + id, e);
        }
        throw new ElementoNoEncontradoExcepcion("Adopcion", id);
    }


    @Override
    public void modificar(int id, Adopcion objeto) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        int filas;
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_ACTUALIZAR)) {
            ps.setInt(1, objeto.getIdAoptante());
            ps.setInt(2, objeto.getIdPerro());
            if (objeto.getIdSolicitud() != null) {
                ps.setInt(3, objeto.getIdSolicitud());
            } else {
                ps.setNull(3, Types.INTEGER);
            }
            ps.setTimestamp(4, Timestamp.valueOf(objeto.getFecha()));
            ps.setInt(5, id);
            filas = ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException(
                    "Error al actualizar Adopcion " + id, e);
        }
        if (filas == 0) {
            throw new ElementoNoEncontradoExcepcion("Adopcion", id);
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
                    "Error al eliminar Adopcion " + id, e);
        }
        if (filas == 0) {
            throw new ElementoNoEncontradoExcepcion("Adopcion", id);
        }
    }


    private Adopcion crearAdopcion(ResultSet rs) throws SQLException {
        int id = rs.getInt("id_adopcion");
        int idAdoptante = rs.getInt("id_adoptante");
        int idPerro = rs.getInt("id_perro");
        int idSolicitudSQL = rs.getInt("id_solicitud");
        Integer idSolicitud;
        if (rs.wasNull()) {
            idSolicitud = null;
        } else {
            idSolicitud = idSolicitudSQL;
        }
        LocalDateTime fecha = rs.getTimestamp("fecha").toLocalDateTime();

        Adopcion adopcion = new Adopcion( id, idPerro, idAdoptante, idSolicitud, fecha);
        return adopcion;
    }
}