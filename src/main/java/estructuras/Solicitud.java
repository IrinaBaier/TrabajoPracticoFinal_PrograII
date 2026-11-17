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
        setEstado(EstadoSolicitud.PENDIENTE);
        setIdAoptante(idAdoptante);
        setIdPerro(idPerro);
    }

    public Solicitud(int id, int idAdoptante, int idPerro, LocalDateTime fecha, EstadoSolicitud estado, ObservacionSolicitud observacionSolicitud) {
        this.ID = id;
        setIdAoptante(idAdoptante);
        setIdPerro(idPerro);
        setFecha(fecha);
        setEstado(estado);
        setMotivoRechazo(observacionSolicitud);
    }

    public Solicitud(int idAdoptante, int idPerro, LocalDateTime fecha, EstadoSolicitud estado, ObservacionSolicitud observacionSolicitud) {
        this.ID = contador++;
        setIdAoptante(idAdoptante);
        setIdPerro(idPerro);
        setFecha(fecha);
        setEstado(estado);
        setMotivoRechazo(observacionSolicitud);
    }

    public Solicitud() {
        this.ID = contador++;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        if (estado == null) {
            throw new DatoInvalidoException("El estado de la solicitud no puede ser nulo");
        }
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

    public void setFecha(LocalDateTime fecha) {
        if (fecha == null) {
            throw new DatoInvalidoException("La fecha no puede ser nula");
        } else if (fecha.isAfter(LocalDateTime.now())) {
            throw new DatoInvalidoException("No se puede registrar una solicitud con fecha futura");
        }
        this.fecha = fecha;
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

    public static void setContador(int siguienteId) {
        contador = siguienteId;
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
