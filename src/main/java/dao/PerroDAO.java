package dao;

import estructuras.Perro;
import excepciones.AccesoDatosException;
import excepciones.ElementoNoEncontradoExcepcion;
import interfaces.CRUD;

import java.util.List;

public class PerroDAO implements CRUD<Perro> {

    @Override
    public void crear(Perro objeto) throws AccesoDatosException {

    }

    @Override
    public List<Perro> mostrarTodos() throws AccesoDatosException {
        return List.of();
    }

    @Override
    public Perro buscarPorId(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        return null;
    }

    @Override
    public void modificar(int id, Perro objeto) throws AccesoDatosException, ElementoNoEncontradoExcepcion {

    }

    @Override
    public void eliminar(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {

    }
}
