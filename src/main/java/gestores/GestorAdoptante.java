package gestores;
import dao.AdopcionDAO;
import dao.AdoptanteDAO;
import estructuras.Adopcion;
import estructuras.Adoptante;
import estructuras.Perro;
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


    public List<Adoptante> listarPorDomicilio(String domicilio) {
        List<Adoptante> lista = new ArrayList<>();
        for (Adoptante a : elementos) {
            if (a.getDomicilio() != null && a.getDomicilio().equalsIgnoreCase(domicilio)) {
                lista.add(a);
            }
        }
        return lista;
    }


    public List<Adoptante> listarOrdenadosPorNombre() {
        List<Adoptante> lista = new ArrayList<>(elementos);
        lista.sort(Comparator.comparing(Adoptante::getNombre, String.CASE_INSENSITIVE_ORDER));
        return lista;
    }

    public boolean registrarSolicitud(int idAdoptante, Perro perro) throws ElementoNoEncontradoExcepcion {
        Adoptante adoptante = buscar(idAdoptante);
        if (adoptante != null) {
            return adoptante.realizarSolicitud(perro);
        }
        return false;
    }

///este metodo en adoptante o en adopciones? mi cerebro no logra identificar donde va
    public boolean registrarAdopcion(int idAdoptante, Perro perro) throws ElementoNoEncontradoExcepcion {
        Adoptante adoptante = buscar(idAdoptante);
        if (adoptante != null) {
            return adoptante.adoptar(perro);
        }
        return false;
    }

    public List<Adopcion> obtenerHistorialAdoptante(int idAdoptante) throws ElementoNoEncontradoExcepcion {
        Adoptante adoptante = buscar(idAdoptante);
        if (adoptante != null) {
            return adoptante.getHistorialAdopciones();
        }
        return null;
    }

}