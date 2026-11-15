package dao;

import estructuras.Adopcion;
import excepciones.AccesoDatosException;
import excepciones.ElementoNoEncontradoExcepcion;
import interfaces.CRUD;

import java.util.List;

public class AdopcionDAO implements CRUD<Adopcion> {


    @Override
    public void crear(Adopcion objeto) throws AccesoDatosException {

    }

    @Override
    public List<Adopcion> mostrarTodos() throws AccesoDatosException {
        return List.of();
    }

    @Override
    public Adopcion buscarPorId(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        return null;
    }

    @Override
    public void modificar(int id, Adopcion objeto) throws AccesoDatosException, ElementoNoEncontradoExcepcion {

    }

    @Override
    public void eliminar(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {

    }
}
