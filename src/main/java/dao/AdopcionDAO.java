package dao;

import estructuras.Adopcion;
import interfaces.CRUD;

public class AdopcionDAO implements CRUD<Adopcion> {

    @Override
    public boolean crear(Adopcion objeto) {
        return false;
    }

    @Override
    public void mostrar() {

    }

    @Override
    public boolean modificar(int id, Adopcion objeto) {
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        return false;
    }
}
