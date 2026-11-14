package dao;

import estructuras.Perro;
import interfaces.CRUD;

public class PerroDAO implements CRUD<Perro> {

    @Override
    public boolean crear(Perro objeto) {
        return false;
    }

    @Override
    public void mostrar() {

    }

    @Override
    public boolean modificar(int id, Perro objeto) {
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        return false;
    }
}
