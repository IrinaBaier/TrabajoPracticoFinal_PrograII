package Gestores;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GestorAdoptante {
    List<Adoptante> adoptantes;

    public GestorAdoptante() {this.adoptantes = new ArrayList<>();}
/// duda con el agregar adoptante, mas abajo esta el metodo de registrar solicitud de dicho adoptante, queda ambos metodos o los unifico
/// y que a la hora de agregar adoptante tambien se agrege la solicitud???
    public boolean agregarAdoptante(Adoptante adoptante) {
        if (!adoptantes.contains(adoptante)) {
            return adoptantes.add(adoptante);
        }
        return false;
    }

    public Adoptante buscarPorId(int id) {
        for (Adoptante a : adoptantes) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public boolean modificarAdoptante(int id, String nuevoNombre, int nuevoDni, int nuevoTelefono, String nuevoEmail, String nuevoDomicilio) {
        Adoptante a = buscarPorId(id);
        if (a != null) {
            a.setNombre(nuevoNombre);
            a.setDni(nuevoDni);
            a.setTelefono(nuevoTelefono);
            a.setEmail(nuevoEmail);
            a.setDomicilio(nuevoDomicilio);
            return true;
        }
        return false;
    }

    public boolean eliminarAdoptante(int id) {
        Adoptante a = buscarPorId(id);
        if (a != null) {
            return adoptantes.remove(a);
        }
        return false;
    }

    public List<Adoptante> listarPorDomicilio(String domicilio) {
        List<Adoptante> lista = new ArrayList<>();
        for (Adoptante a : adoptantes) {
            if (a.getDomicilio() != null && a.getDomicilio().equalsIgnoreCase(domicilio)) {
                lista.add(a);
            }
        }
        return lista;
    }

    public int cantidadTotalAdoptantes() {
        return adoptantes.size();
    }

    public List<Adoptante> listarOrdenadosPorNombre() {
        List<Adoptante> lista = new ArrayList<>(adoptantes);
        lista.sort(Comparator.comparing(Adoptante::getNombre, String.CASE_INSENSITIVE_ORDER));
        return lista;
    }

    public boolean hayAdoptantes() {
        return !adoptantes.isEmpty();
    }
    public boolean registrarSolicitud(int idAdoptante, Perro perro) {
        Adoptante adoptante = buscarPorId(idAdoptante);
        if (adoptante != null) {
            return adoptante.realizarSolicitud(perro);
        }
        return false;
    }


    public boolean registrarAdopcion(int idAdoptante, Perro perro) {
        Adoptante adoptante = buscarPorId(idAdoptante);
        if (adoptante != null) {
            return adoptante.adoptar(perro);
        }
        return false;
    }

    public List<Adopcion> obtenerHistorialAdoptante(int idAdoptante) {
        Adoptante adoptante = buscarPorId(idAdoptante);
        if (adoptante != null) {
            return adoptante.getHistorialAdopciones();
        }
        return null;
    }

}