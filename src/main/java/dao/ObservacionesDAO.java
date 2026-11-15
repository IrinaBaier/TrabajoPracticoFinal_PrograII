package dao;

import estructuras.Observaciones;
import excepciones.AccesoDatosException;
import excepciones.ElementoNoEncontradoExcepcion;
import interfaces.CRUD;

import java.util.List;

public class ObservacionesDAO implements CRUD<Observaciones> {

    @Override
    public void crear(Observaciones objeto) throws AccesoDatosException {

    }

    @Override
    public List<Observaciones> mostrarTodos() throws AccesoDatosException {
        return List.of();
    }

    @Override
    public Observaciones buscarPorId(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        return null;
    }

    @Override
    public void modificar(int id, Observaciones objeto) throws AccesoDatosException, ElementoNoEncontradoExcepcion {

    }

    @Override
    public void eliminar(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {

    }
}
