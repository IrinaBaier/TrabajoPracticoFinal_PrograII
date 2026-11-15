package interfaces;

import excepciones.AccesoDatosException;
import excepciones.ElementoNoEncontradoExcepcion;

import java.util.List;

public interface CRUD<T> {
    void crear(T objeto) throws AccesoDatosException;
    List<T> mostrarTodos()throws AccesoDatosException;
    T buscarPorId(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion;
    void modificar(int id, T objeto) throws AccesoDatosException, ElementoNoEncontradoExcepcion;
    void eliminar(int id)throws AccesoDatosException, ElementoNoEncontradoExcepcion;
}
