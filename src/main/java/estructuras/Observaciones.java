package estructuras;

import excepciones.DatoInvalidoException;
import interfaces.Identificable;

import java.time.LocalDateTime;

public class Observaciones implements Identificable {
    private int ID;
    static int contador = 1;
    private LocalDateTime fecha;
    private String Descripcion;
    private int idAdopcion;

    public Observaciones(String descripcion, int idAdopcion) {
        this.ID = contador++;
        this.fecha = LocalDateTime.now();
        setDescripcion(descripcion);
        setIdAdopcion(idAdopcion);
    }

    public Observaciones(int id, int idAdopcion, LocalDateTime fecha, String descripcion) {
        this.ID = id;
        setIdAdopcion(idAdopcion);
        setFecha(fecha);
        setDescripcion(descripcion);
    }

    public Observaciones(int idAdopcion, LocalDateTime fecha, String descripcion) {
        this.ID = contador++;
        setIdAdopcion(idAdopcion);
        setFecha(fecha);
        setDescripcion(descripcion);
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

    public void setFecha(LocalDateTime fecha) {
        if(fecha == null){
            throw new DatoInvalidoException("La fecha no puede ser null");
        }else if(fecha.isAfter(LocalDateTime.now())){
            throw new DatoInvalidoException("No se admite la clarividencia en la observación");
        }
        this.fecha = fecha;
    }

    public void setDescripcion(String descripcion) {
        if(descripcion == null || descripcion.trim().isEmpty()){
            throw new DatoInvalidoException("La descripción no puede estar vacía");
        }
        if(descripcion.length() < 13){
            throw new DatoInvalidoException("La descripción es muy corta");
        }
        Descripcion = descripcion;
    }

    public int getIdAdopcion() {
        return idAdopcion;
    }

    public void setIdAdopcion(int idAdopcion) {
        if (idAdopcion < 1) {
            throw new DatoInvalidoException("ID de adopción inválido");
        }
        this.idAdopcion = idAdopcion;
    }

    @Override
    public String toString() {
        return "estructuras.Observaciones{" +
                "ID=" + ID +
                ", fecha=" + fecha +
                ", Descripcion='" + Descripcion + '\'' +
                '}';
    }


    @Override
    public int getId() {
        return this.ID;
    }
}
