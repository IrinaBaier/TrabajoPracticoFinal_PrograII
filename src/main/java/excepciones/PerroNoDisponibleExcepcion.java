package excepciones;

import enums.EstadoAnimal;

public class PerroNoDisponibleExcepcion extends Exception {

    private final int id;
    private final EstadoAnimal estado;

    public PerroNoDisponibleExcepcion(String message, int id, EstadoAnimal estado) {

        super("El perro"+ id + "no esta disponible ( su estado actual es: "+ estado +")");
        this.id = id;
        this.estado = estado;
    }
}
