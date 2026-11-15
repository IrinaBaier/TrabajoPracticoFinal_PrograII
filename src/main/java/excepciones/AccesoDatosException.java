package excepciones;

public class AccesoDatosException extends RuntimeException {
    public AccesoDatosException(String message,Throwable causa) {
        super(message, causa);
    }
}
