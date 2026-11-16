package gestores;

import dao.AdopcionDAO;
import dao.RegistroSolicitudDAO;
import enums.EstadoAnimal;
import estructuras.Adopcion;
import estructuras.Adoptante;
import estructuras.Perro;
import estructuras.Solicitud;
import excepciones.AccesoDatosException;
import excepciones.ElementoDuplicadoExcepcion;
import excepciones.ElementoNoEncontradoExcepcion;
import interfaces.CRUD;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestorAdopciones extends Gestor <Adopcion>{

    private final CRUD<Adopcion> dao = new AdopcionDAO();
    private final RegistroSolicitudDAO registroSolicitudDAO = new RegistroSolicitudDAO();


    public GestorAdopciones(Class<Adopcion> tipoEntidad) {
        super(tipoEntidad);
    }


    public boolean registrarAdopcion(Adoptante adoptante, Perro perro) throws ElementoDuplicadoExcepcion {

        if (adoptante == null || perro == null) {
            return false;
        }
        if (perro.getEstado() != EstadoAnimal.DISPONIBLE) {
            return false;
        }
        Adopcion nuevaAdopcion = new Adopcion(perro.getId(), adoptante.getId(), null, LocalDateTime.now());
        agregar(nuevaAdopcion);
        perro.setEstado(EstadoAnimal.ADOPTADO);

        return true;
    }

    public List<Adopcion> obtenerHistorialAdoptante(int idAdoptante) throws AccesoDatosException {
        List<Adopcion> historial = new ArrayList<>();
        for (Adopcion adopcion : listar()) {
            if (adopcion.getIdAoptante() == idAdoptante) {
                historial.add(adopcion); }
        }

        return historial;
    }

    public void guardarEnBD(Adopcion a) throws AccesoDatosException {
        dao.crear(a);
    }

    public List<Adopcion> listarDesdeBD() throws AccesoDatosException {
        return dao.mostrarTodos();
    }

    public Adopcion buscarEnBD(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        return dao.buscarPorId(id);
    }

    public void actualizarEnBD(int id, Adopcion a) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        dao.modificar(id,a);
    }

    public void eliminarDeBD(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        dao.eliminar(id);
    }

    public void registrarAprobacion(Adopcion adopcion, Perro perro, Solicitud solicitud ) {
        registroSolicitudDAO.registrarAprobacion(adopcion, perro, solicitud);
    }

}
