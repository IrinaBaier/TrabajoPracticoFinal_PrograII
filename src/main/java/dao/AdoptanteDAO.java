package dao;

import estructuras.Adopcion;
import estructuras.Adoptante;
import interfaces.CRUD;

public class AdoptanteDAO implements CRUD<Adoptante> {

    @Override
    public boolean crear(Adoptante objeto) {
        return false;
    }

    @Override
    public void mostrar() {

    }

    @Override
    public boolean modificar(int id, Adoptante objeto) {
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        return false;
    }
}
