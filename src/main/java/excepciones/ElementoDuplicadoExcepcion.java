package excepciones;

import enums.EstadoAnimal;

public class ElementoDuplicadoExcepcion extends Exception {
    public ElementoDuplicadoExcepcion(String elemento, int id) {

        super(elemento + " esta duplicado " + id );
    }
}
