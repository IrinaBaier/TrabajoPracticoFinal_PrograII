package gestores;

import dao.AdoptanteDAO;
import estructuras.Adoptante;
import estructuras.Perro;
import excepciones.AccesoDatosException;
import excepciones.DatoInvalidoException;
import excepciones.ElementoDuplicadoExcepcion;
import excepciones.ElementoNoEncontradoExcepcion;
import interfaces.CRUD;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



public class GestorAdoptante extends Gestor<Adoptante>{
    private final CRUD<Adoptante> dao = new AdoptanteDAO();

    public GestorAdoptante(Class<Adoptante> tipoEntidad) {
        super(tipoEntidad);
    }


    public List<Adoptante> listarPorDomicilio(String domicilio) throws AccesoDatosException{
        List<Adoptante> lista = new ArrayList<>();
        for (Adoptante a : dao.mostrarTodos()) {
            if (a.getDomicilio() != null && a.getDomicilio().equalsIgnoreCase(domicilio)) {
                lista.add(a);
            }
        }
        return lista;
    }

    public List<Adoptante> listarOrdenadosPorNombre() throws AccesoDatosException{
        List<Adoptante> lista = new ArrayList<>(dao.mostrarTodos());
        lista.sort(Comparator.comparing(Adoptante::getNombre, String.CASE_INSENSITIVE_ORDER));
        return lista;
    }
    public void registrarAdoptante(Adoptante a) throws ElementoDuplicadoExcepcion, AccesoDatosException, DatoInvalidoException {

        if (a == null) {
            throw new DatoInvalidoException("El Adoptante no puede ser nulo");
        }

        for (Adoptante existente : dao.mostrarTodos()) {
            if (existente.getDni() == a.getDni()) {
                throw new ElementoDuplicadoExcepcion("El adoptante con DNI", a.getDni());
            }
        }

        dao.crear(a);
        agregar(a);
    }


    public List<Adoptante> listarDesdeBD() throws AccesoDatosException {
        return dao.mostrarTodos();
    }

    public Adoptante buscarEnBD(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        return dao.buscarPorId(id);
    }

    public void actualizarEnBD(int id, Adoptante a) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        dao.modificar(id,a);
    }

    public void eliminarDeBD(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        dao.eliminar(id);
    }

}