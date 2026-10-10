import java.time.LocalDateTime;

public class Observaciones {
    private int ID;
    static int contador = 0;
    private LocalDateTime fecha = LocalDateTime.now();
    private String Descripcion;

    public Observaciones(String descripcion) {
        this.ID = contador++;
        Descripcion = descripcion;
    }

    public int getID() {
        return ID;
    }


    public static int getContador() {
        return contador;
    }


    public LocalDateTime getFecha() {
        return fecha;
    }


    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Observaciones{" +
                "ID=" + ID +
                ", fecha=" + fecha +
                ", Descripcion='" + Descripcion + '\'' +
                '}';
    }
}
