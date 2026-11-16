package estructuras;

import enums.EstadoSolicitud;
import enums.ObservacionSolicitud;
import excepciones.DatoInvalidoException;
import interfaces.Identificable;

import java.time.LocalDateTime;

public class Solicitud implements Identificable {
    private int ID;
    static int contador = 1;
    private LocalDateTime fecha = LocalDateTime.now();
    private EstadoSolicitud estado;
    private ObservacionSolicitud observacionSolicitud;

    private int idPerro;

    private int idAdoptante;

    public Solicitud(int idAdoptante, int idPerro) {
        this.ID = contador++;
        this.estado = EstadoSolicitud.PENDIENTE;
        setIdAoptante(idAdoptante);
        setIdPerro(idPerro);
    }

    public Solicitud(int id, int idAdoptante, int idPerro, LocalDateTime fecha, EstadoSolicitud estado, ObservacionSolicitud observacionSolicitud) {
        this.ID = id;
        this.idAdoptante = idAdoptante;
        this.idPerro = idPerro;
        this.fecha = fecha;
        this.estado = estado;
        this.observacionSolicitud = observacionSolicitud;
    }

    public Solicitud(int idAdoptante, int idPerro, LocalDateTime fecha, EstadoSolicitud estado, ObservacionSolicitud observacionSolicitud) {
        this.ID = contador++;
        this.idAdoptante = idAdoptante;
        this.idPerro = idPerro;
        this.fecha = fecha;
        this.estado = estado;
        this.observacionSolicitud = observacionSolicitud;
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

    public void setIdAoptante(int idAdoptante) {
        // En gestor o DAO adoptante, crear un get adoptante por id y usarlo acá, para ver si es un id que no corresponde a un adoptante registrado
        if (idAdoptante < 1) {
            throw new DatoInvalidoException("El id del adoptante no es un número válido");
        }
        this.idAdoptante = idAdoptante;
    }

    @Override
    public String toString() {
        return "Solicitud: ( " +
                "ID=" + ID +
                ", fecha=" + fecha +
                ", estado=" + estado +
                ", observacionSolicitud=" + observacionSolicitud +
                ')';
    }
   ///se agrega para solicitusDAO
    public int getIdPerro() {
        return idPerro;
    }

    public int getIdAdoptante() {
        return idAdoptante;
    }

    @Override
    public int getId() {
        return this.ID;
    }
}
