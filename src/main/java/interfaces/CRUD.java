package interfaces;

public interface CRUD<T> {
    boolean crear(T objeto);
    void mostrar();
    boolean modificar(int id, T objeto);

    boolean eliminar(int id);
}
