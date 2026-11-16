package estructuras;

import excepciones.DatoInvalidoException;
import interfaces.Identificable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Adopcion implements Identificable {
    private int ID;
    static int contador = 1;

    private int idPerro;

    private int idAoptante;

    private Integer idSolicitud;
    private LocalDateTime fecha = LocalDateTime.now();

    private final List<Observaciones> observacion = new ArrayList<>();

    public Adopcion() {
        this.ID = contador++;
    }

    public Adopcion(int idPerro, int idAoptante, Integer idSolicitud, LocalDateTime fecha) {
        this.ID = contador++;
        setIdPerro(idPerro);
        setIdAoptante(idAoptante);
        setIdSolicitud(idSolicitud);
        setFecha(fecha);
    }
    /// comprobar funcionamiento.
    public Adopcion(int id, int idPerro, int idAdoptante, Integer idSolicitud, LocalDateTime fecha) {
        this.ID = id;
        setIdPerro(idPerro);
        setIdAoptante(idAdoptante);
        setIdSolicitud(idSolicitud);
        setFecha(fecha);
    }

    public int getIdPerro() {
        return idPerro;
    }

    public int getIdAoptante() {
        return idAoptante;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public List<Observaciones> getObservacion() {
        return observacion;
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

    public void setIdSolicitud(Integer idSolicitud) {
        //Usar el getSolicitud para validar que se corresponda a una solicitud válida, solo si no viene null
        //Se va a permitir este campo nulo
        this.idSolicitud = idSolicitud;
    }

    public void setFecha(LocalDateTime fecha) {
        if(fecha == null){
            throw new DatoInvalidoException("La fecha no puede ser null");
        }else if(fecha.isAfter(LocalDateTime.now())){
            throw new DatoInvalidoException("No se puede registrar una adopción que aún no sucedió");
        }
        this.fecha = fecha;
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
        return "Adopcion{" +
                "ID=" + ID +
                ", idPerro=" + idPerro +
                ", idAdoptante=" + idAoptante +
                ", idSolicitud=" + idSolicitud +
                ", fecha=" + fecha +
                '}';
    }

    @Override
    public int getId() {
        return this.ID;
    }


}
