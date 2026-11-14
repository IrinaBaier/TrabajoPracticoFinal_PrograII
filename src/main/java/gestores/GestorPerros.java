package gestores;

import dao.AdoptanteDAO;
import dao.PerroDAO;
import estructuras.Adoptante;
import estructuras.Perro;
import interfaces.Adoptable;
import interfaces.CRUD;

import java.util.List;

public class GestorPerros  extends Gestor<Perro>   {

    private final CRUD<Perro> dao = new PerroDAO();

    public GestorPerros(Class<Perro> tipoEntidad) {
        super(tipoEntidad);
    }


}
