import Enums.EstadoSolicitud;
import Enums.MotivoRechazo;

import java.time.LocalDateTime;

public class Solicitud {
    private int ID;
    static int contador = 0;
    private LocalDateTime fecha = LocalDateTime.now();
    private EstadoSolicitud estado;
    private MotivoRechazo motivoRechazo;

    public Solicitud() {
        this.ID = contador++;
        this.estado = EstadoSolicitud.PENDIENTE;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public MotivoRechazo getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(MotivoRechazo motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public int getID() {
        return ID;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "ID=" + ID +
                ", fecha=" + fecha +
                ", estado=" + estado +
                ", motivoRechazo=" + motivoRechazo +
                '}';
    }
}
