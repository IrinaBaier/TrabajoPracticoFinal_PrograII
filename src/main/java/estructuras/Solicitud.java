package estructuras;

import enums.EstadoSolicitud;
import enums.ObservacionSolicitud;
import excepciones.DatoInvalidoException;

import java.time.LocalDateTime;

public class    Solicitud {
    private int ID;
    static int contador = 0;
    private LocalDateTime fecha = LocalDateTime.now();
    private EstadoSolicitud estado;
    private ObservacionSolicitud observacionSolicitud;

    private int idPerro;

    private int idAoptante;

    public Solicitud(int idAoptante, int idPerro) {
        this.ID = contador++;
        this.estado = EstadoSolicitud.PENDIENTE;
        setIdAoptante(idAoptante);
        setIdPerro(idPerro);
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public ObservacionSolicitud getMotivoRechazo() {
        return observacionSolicitud;
    }

    public void setMotivoRechazo(ObservacionSolicitud observacionSolicitud) {
        this.observacionSolicitud = observacionSolicitud;
    }

    public int getID() {
        return ID;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setIdPerro(int idPerro) {
        // En gestor o DAO perros, crear un get perro por id y usarlo acá, para ver si es un id que no pertence a ningún perro o corresponde a un perro y adoptado
        if (idPerro < 1) {
            throw new DatoInvalidoException("El id del perro no es un número válido");
        }
        this.idPerro = idPerro;
    }

    public void setIdAoptante(int idAoptante) {
        // En gestor o DAO adoptante, crear un get adoptante por id y usarlo acá, para ver si es un id que no corresponde a un adoptante registrado
        if (idAoptante < 1) {
            throw new DatoInvalidoException("El id del adoptante no es un número válido");
        }
        this.idAoptante = idAoptante;
    }

    @Override
    public String toString() {
        return "estructuras.Solicitud{" +
                "ID=" + ID +
                ", fecha=" + fecha +
                ", estado=" + estado +
                ", observacionSolicitud=" + observacionSolicitud +
                '}';
    }
}
