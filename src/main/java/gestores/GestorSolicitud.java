package gestores;

import enums.EstadoSolicitud;
import estructuras.Solicitud;
import excepciones.AccesoDatosException;
import excepciones.ElementoDuplicadoExcepcion;
import excepciones.ElementoNoEncontradoExcepcion;
import interfaces.CRUD;
import dao.SolicitudDAO;
import estructuras.Perro;

import java.util.ArrayList;
import java.util.List;

public class GestorSolicitud extends Gestor<Solicitud> {

    private final CRUD<Solicitud> dao = new SolicitudDAO();

    public GestorSolicitud(Class<Solicitud> tipoEntidad) {
        super(tipoEntidad);
    }

    public boolean registrarSolicitud(int idAdoptante, Perro perro) throws ElementoDuplicadoExcepcion {
        if (perro == null || !perro.puedeSerAdoptado()) {
            return false;
        }
        Solicitud solicitud = new Solicitud(idAdoptante, perro.getId());
        agregar(solicitud);

        return true;
    }
    public List<Solicitud> listarPorAdoptante(int idAdoptante) {
        List<Solicitud> solicitudes = new ArrayList<>();
        for (Solicitud solicitud : listar()) {
            if (solicitud.getIdAdoptante() == idAdoptante) {
                solicitudes.add(solicitud); }
        }

        return solicitudes;
    }
    public List<Solicitud> listarPorPerro(int idPerro) {
        List<Solicitud> solicitudes = new ArrayList<>();
        for (Solicitud solicitud : listar()) {
            if (solicitud.getIdPerro() == idPerro) {
                solicitudes.add(solicitud); }
        }

        return solicitudes;
    }
    public List<Solicitud> listarPendientes() {
        List<Solicitud> pendientes = new ArrayList<>();
        for (Solicitud solicitud : listar()) {
            if (solicitud.getEstado() == EstadoSolicitud.PENDIENTE) {
                pendientes.add(solicitud); }
        }

        return pendientes;
    }

    public void guardarEnBD(Solicitud s) throws AccesoDatosException {
        dao.crear(s);
    }

    public List<Solicitud> listarDesdeBD() throws AccesoDatosException {
        return dao.mostrarTodos();
    }

    public Solicitud buscarEnBD(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        return dao.buscarPorId(id);
    }

    public void actualizarEnBD(int id, Solicitud s) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        dao.modificar(id,s);
    }

    public void eliminarDeBD(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        dao.eliminar(id);
    }
}
