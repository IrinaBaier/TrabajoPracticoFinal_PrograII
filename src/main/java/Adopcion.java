import Enums.MotivoRechazo;

import java.time.LocalDateTime;

public class Adopcion {
    private int ID;
    static int contador = 0;
    private LocalDateTime fecha = LocalDateTime.now();
    private String Observacion;

    public Adopcion(String Observacion) {
        this.ID = contador++;
        this.Observacion = Observacion;
    }

    public int getID() {
        return ID;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }

    public static int getContador() {
        return contador;
    }

    @Override
    public String toString() {
        return "Adopcion{" +
                "ID=" + ID +
                ", fecha=" + fecha +
                ", Observacion='" + Observacion + '\'' +
                '}';
    }
}
