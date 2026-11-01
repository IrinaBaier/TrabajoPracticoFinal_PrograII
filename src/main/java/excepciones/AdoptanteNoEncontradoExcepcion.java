package excepciones;

public class AdoptanteNoEncontradoExcepcion extends Exception {
    private final int id;

    public AdoptanteNoEncontradoExcepcion(String message, int id) {
        super("No existe un adoptante con el id " + id);
        this.id = id;
    }
}
