package gestores;

import dao.PerroDAO;
import enums.Porte;
import estructuras.Perro;
import excepciones.AccesoDatosException;
import excepciones.ElementoNoEncontradoExcepcion;
import interfaces.CRUD;

import java.util.ArrayList;
import java.util.List;

public class GestorPerros  extends Gestor<Perro>   {

    private final CRUD<Perro> dao = new PerroDAO();

    public GestorPerros(Class<Perro> tipoEntidad) {
        super(tipoEntidad);
    }
    public List<Perro> listarDisponibles() {
        List<Perro> disponibles = new ArrayList<>();
        for (Perro perro : listar()) {
            if (perro.puedeSerAdoptado()) {
                disponibles.add(perro); }
        }

        return disponibles;
    }

    public List<Perro> listarPorRaza(String raza) {
        List<Perro> perros = new ArrayList<>();
        for (Perro perro : listar()) {
            if (perro.getRaza() != null && perro.getRaza().equalsIgnoreCase(raza)) {
                perros.add(perro); }
        }

        return perros;
    }
    public List<Perro> listarPorPorte(Porte porte) {
        List<Perro> perros = new ArrayList<>();
        for (Perro perro : listar()) {
            if (perro.getPorte() == porte) {
                perros.add(perro);
            }
        }

        return perros;
    }
    public void marcarPerroDisponible(int idPerro) throws ElementoNoEncontradoExcepcion {
        Perro perro = buscar(idPerro);
        perro.marcarComoDisponible();
    }

    public void guardarEnBD(Perro a) throws AccesoDatosException {
        dao.crear(a);
    }

    public List<Perro> listarDesdeBD() throws AccesoDatosException {
        return dao.mostrarTodos();
    }

    public Perro buscarEnBD(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        return dao.buscarPorId(id);
    }

    public void actualizarEnBD(int id, Perro a) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        dao.modificar(id,a);
    }

    public void eliminarDeBD(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        dao.eliminar(id);
    }


}
