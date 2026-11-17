package gestores;

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

    public void registrarSolicitud(Solicitud solicitud)
            throws ElementoDuplicadoExcepcion, AccesoDatosException {

        agregar(solicitud);

        try {
            guardarEnBD(solicitud);
        } catch (AccesoDatosException e) {
            elementos.remove(solicitud);
            throw e;
        }
    }
// Metodo pensado en escabilidad a futuro
    public List<Solicitud> listarPorAdoptante(int idAdoptante)  throws AccesoDatosException{
        List<Solicitud> solicitudes = new ArrayList<>();
        for (Solicitud solicitud : dao.mostrarTodos()) {
            if (solicitud.getIdAdoptante() == idAdoptante) {
                solicitudes.add(solicitud); }
        }

        return solicitudes;
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
