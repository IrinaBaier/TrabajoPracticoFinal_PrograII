package gestores;

import estructuras.Observaciones;
import excepciones.AccesoDatosException;
import excepciones.ElementoDuplicadoExcepcion;
import excepciones.ElementoNoEncontradoExcepcion;
import interfaces.CRUD;
import dao.ObservacionesDAO;

import java.util.ArrayList;
import java.util.List;

public class GestorObservaciones extends Gestor<Observaciones>  {

    private final CRUD<Observaciones> dao = new  ObservacionesDAO();

    public boolean registrarObservacion(String descripcion, int idAdopcion) throws AccesoDatosException, ElementoDuplicadoExcepcion {
        Observaciones observacion = new Observaciones(descripcion, idAdopcion);
        agregar(observacion);
        return true;
    }
    public List<Observaciones> listarPorAdopcion(int idAdopcion) {
        List<Observaciones> observaciones = new ArrayList<>();
        for (Observaciones observacion : listar()) {
            if (observacion.getIdAdopcion() == idAdopcion) {
                observaciones.add(observacion); }
        }
        return observaciones;
    }

    public GestorObservaciones(Class<Observaciones> tipoEntidad) {
        super(tipoEntidad);
    }

    public void guardarEnBD(Observaciones o) throws AccesoDatosException {
        dao.crear(o);
    }

    public List<Observaciones> listarDesdeBD() throws AccesoDatosException {
        return dao.mostrarTodos();
    }

    public Observaciones buscarEnBD(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        return dao.buscarPorId(id);
    }

    public void actualizarEnBD(int id, Observaciones o) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        dao.modificar(id,o);
    }

    public void eliminarDeBD(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        dao.eliminar(id);
    }
}
