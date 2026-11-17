package dao;

import config.ConexionDB;
import enums.EstadoSolicitud;
import enums.ObservacionSolicitud;
import estructuras.Solicitud;
import excepciones.AccesoDatosException;
import excepciones.ElementoNoEncontradoExcepcion;
import interfaces.CRUD;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SolicitudDAO implements CRUD<Solicitud> {
    private static final String SQL_INSERTAR = "INSERT INTO solicitudes (id_solicitud, id_adoptante, id_perro, fecha, estado, observacionSolicitud) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_LISTAR = "SELECT * FROM solicitudes ORDER BY id_solicitud";
    private static final String SQL_BUSCAR = "SELECT * FROM solicitudes WHERE id_solicitud = ?";
    private static final String SQL_ACTUALIZAR =  "UPDATE solicitudes SET estado = ?, observacionSolicitud = ? WHERE id_solicitud = ?";
    private static final String SQL_ELIMINAR = "DELETE FROM solicitudes WHERE id_solicitud = ?";

    @Override
    public void crear(Solicitud objeto) throws AccesoDatosException {
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_INSERTAR)) {
            ps.setInt(1, objeto.getID());
            ps.setInt(2, objeto.getIdAdoptante());
            ps.setInt(3, objeto.getIdPerro());
            ps.setTimestamp(4, Timestamp.valueOf(objeto.getFecha()));
            ps.setString(5, objeto.getEstado().name());
            if (objeto.getMotivoRechazo() != null) {
                ps.setString(6, objeto.getMotivoRechazo().name());
            } else {
                ps.setString(6, null);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException( "Error al cargar Solicitud " + objeto.getID(), e);
        }
        }


    @Override
    public List<Solicitud> mostrarTodos() throws AccesoDatosException {
        List<Solicitud> lista = new ArrayList<>();
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_LISTAR);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(crearSolicitud(rs));
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al listar Solicitudes", e);
        }
        return lista;
    }

    @Override
    public Solicitud buscarPorId(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_BUSCAR)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return crearSolicitud(rs);
                }
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al buscar Solicitud" + id , e);
        }
        throw new ElementoNoEncontradoExcepcion("Solicitud", id);
    }

    @Override
    public void modificar(int id, Solicitud objeto) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        int filas;
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_ACTUALIZAR)) {
        ps.setString(1, objeto.getEstado().name());
        if (objeto.getMotivoRechazo() != null) {
            ps.setString(2, objeto.getMotivoRechazo().name());
        } else {
            ps.setString(2, null);
        }
        ps.setInt(3,id);
            filas = ps.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al actualizar Solicitud " + id, e);
        }
        if (filas == 0) {
            throw new ElementoNoEncontradoExcepcion("Solicitud", id);
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
            throw new AccesoDatosException("Error al Eliminar Solicitud " + id, e);
        }
        if (filas == 0) {
            throw new ElementoNoEncontradoExcepcion("Solicitud", id);
        }
    }

    private Solicitud crearSolicitud(ResultSet rs) throws SQLException {

        int id = rs.getInt("id_solicitud");
        int idAdoptante = rs.getInt("id_adoptante");
        int idPerro = rs.getInt("id_perro");
        LocalDateTime fecha = rs.getTimestamp("fecha").toLocalDateTime();
        EstadoSolicitud estado = EstadoSolicitud.valueOf(rs.getString("estado"));
        ObservacionSolicitud observacionSolicitud;
        if (rs.getString("observacionSolicitud") != null) {
            observacionSolicitud = ObservacionSolicitud.valueOf( rs.getString("observacionSolicitud"));
        } else {
            observacionSolicitud = null;
        }

        Solicitud solicitud = new Solicitud(id, idAdoptante, idPerro, fecha, estado, observacionSolicitud );
        return solicitud;
    }
}
