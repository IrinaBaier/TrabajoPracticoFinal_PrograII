package dao;

import estructuras.Solicitud;
import excepciones.AccesoDatosException;
import excepciones.ElementoNoEncontradoExcepcion;
import interfaces.CRUD;

import java.util.List;

public class SolicitudDAO implements CRUD<Solicitud> {

    @Override
    public void crear(Solicitud objeto) throws AccesoDatosException {

    }

    @Override
    public List<Solicitud> mostrarTodos() throws AccesoDatosException {
        return List.of();
    }

    @Override
    public Solicitud buscarPorId(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        return null;
    }

    @Override
    public void modificar(int id, Solicitud objeto) throws AccesoDatosException, ElementoNoEncontradoExcepcion {

    }

    @Override
    public void eliminar(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {

    }
}
