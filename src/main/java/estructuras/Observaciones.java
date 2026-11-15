package estructuras;

import excepciones.DatoInvalidoException;

import java.time.LocalDateTime;

public class Observaciones {
    private int ID;
    static int contador = 0;
    private LocalDateTime fecha;
    private String Descripcion;

    public Observaciones(String descripcion) {
        this.ID = contador++;
        fecha = LocalDateTime.now();
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

    @Override
    public String toString() {
        return "estructuras.Observaciones{" +
                "ID=" + ID +
                ", fecha=" + fecha +
                ", Descripcion='" + Descripcion + '\'' +
                '}';
    }


}
