package excepciones;

import enums.EstadoAnimal;

public class ElementoNoDisponibleExcepcion extends Exception {


    public ElementoNoDisponibleExcepcion(String elemento, int id, EstadoAnimal estado) {

        super("El " + elemento + " " + id + "no esta disponible ( su estado actual es: " + estado + ")");
    }
}
