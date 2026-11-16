package dao;

import config.ConexionDB;
import enums.EstadoAnimal;
import enums.EstadoSolicitud;
import estructuras.Adopcion;
import estructuras.Perro;
import estructuras.Solicitud;
import excepciones.AccesoDatosException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Objects;

public class RegistroSolicitudDAO {

    public void registrarAprobacion( Adopcion adopcion,Perro perro,Solicitud solicitud) throws AccesoDatosException {
        if (adopcion == null || perro == null || solicitud == null) {
            throw new IllegalArgumentException( "La adopción, el perro y la solicitud son obligatorios.");
        }
        if (solicitud.getEstado() != EstadoSolicitud.PENDIENTE || perro.getEstado() != EstadoAnimal.DISPONIBLE || adopcion.getIdPerro() != perro.getId() || adopcion.getIdPerro() != solicitud.getIdPerro() || adopcion.getIdAoptante() != solicitud.getIdAdoptante() || !Objects.equals(adopcion.getIdSolicitud(), solicitud.getID())) {
            throw new IllegalArgumentException("La solicitud debe estar pendiente y corresponder al perro y a la adopción." );
        }
        String sqlPerro = "UPDATE perros SET estado = ? WHERE id_perro = ? AND estado = ?";

        String sqlSolicitud = "UPDATE solicitudes SET estado = ?, observacionSolicitud = NULL WHERE id_solicitud = ? AND estado = ? AND id_perro = ? AND id_adoptante = ?";

        String sqlAdopcion = "INSERT INTO adopciones(id_adopcion, id_adoptante, id_perro, id_solicitud, fecha) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConexion()) {
            con.setAutoCommit(false);
            try {
                try (PreparedStatement ps = con.prepareStatement(sqlPerro)) {
                    ps.setString(1, EstadoAnimal.ADOPTADO.name());
                    ps.setInt(2, perro.getId());
                    ps.setString(3, EstadoAnimal.DISPONIBLE.name());
                    if (ps.executeUpdate() != 1) {
                        throw new SQLException("El perro no existe o ya no está disponible.");
                    }
                }
                try (PreparedStatement ps = con.prepareStatement(sqlSolicitud)) {
                    ps.setString(1, EstadoSolicitud.APROBADA.name());
                    ps.setInt(2, solicitud.getID());
                    ps.setString(3, EstadoSolicitud.PENDIENTE.name());
                    ps.setInt(4, perro.getId());
                    ps.setInt(5, solicitud.getIdAdoptante());
                    if (ps.executeUpdate() != 1) {
                        throw new SQLException( "La solicitud no existe, ya fue procesada o no corresponde al perro.");
                    }
                }

                try (PreparedStatement ps = con.prepareStatement(sqlAdopcion)) {
                    ps.setInt(1, adopcion.getID());
                    ps.setInt(2, adopcion.getIdAoptante());
                    ps.setInt(3, adopcion.getIdPerro());

                    if (adopcion.getIdSolicitud() != null) {
                        ps.setInt(4, adopcion.getIdSolicitud());
                    } else {
                        ps.setNull(4, Types.INTEGER);
                    }
                    ps.setTimestamp(5, Timestamp.valueOf(adopcion.getFecha()));
                    ps.executeUpdate();
                }
                con.commit();
                perro.setEstado(EstadoAnimal.ADOPTADO);
                solicitud.setEstado(EstadoSolicitud.APROBADA);
                solicitud.setMotivoRechazo(null);
            } catch (SQLException e) {
                try {
                    con.rollback();
                } catch (SQLException errorRollback) {
                    e.addSuppressed(errorRollback);
                }
                throw e;
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("No se pudo registrar la aprobación de la adopción.", e);
        }
    }
}