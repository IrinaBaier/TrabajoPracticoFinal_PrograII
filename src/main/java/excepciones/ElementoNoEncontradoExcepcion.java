package excepciones;

public class ElementoNoEncontradoExcepcion extends Exception {
    private final int id;

    public ElementoNoEncontradoExcepcion( int id) {
        super("No existe " + elemento + " con el id " + id);
        this.id = id;
    }
}
