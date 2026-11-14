package estructuras;

import interfaces.Identificable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Adopcion implements Identificable {
    private int ID;
    static int contador = 0;
    private LocalDateTime fecha = LocalDateTime.now();

    private final List<Observaciones> observacion = new ArrayList<>();

    public Adopcion() {
        this.ID = contador++;
    }

    public int getID() {
        return ID;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }


    public static int getContador() {
        return contador;
    }

    @Override
    public String toString() {
        return "estructuras.Adopcion{" +
                "ID=" + ID +
                ", fecha=" + fecha +
                '}';
    }

    @Override
    public int getId() {
        return this.ID;
    }
}
