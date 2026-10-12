package estructuras;

import enums.EstadoSolicitud;
import enums.ObservacionSolicitud;

import java.time.LocalDateTime;

public class Solicitud {
    private int ID;
    static int contador = 0;
    private LocalDateTime fecha = LocalDateTime.now();
    private EstadoSolicitud estado;
    private ObservacionSolicitud observacionSolicitud;

    public Solicitud() {
        this.ID = contador++;
        this.estado = EstadoSolicitud.PENDIENTE;
        this.observacionSolicitud = ObservacionSolicitud.OTRO;
    }

    public Solicitud(ObservacionSolicitud observacionSolicitud) {
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
