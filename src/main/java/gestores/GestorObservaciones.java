package gestores;

import estructuras.Adopcion;
import estructuras.Observacion;
import excepciones.AccesoDatosException;
import excepciones.DatoInvalidoException;
import excepciones.ElementoDuplicadoExcepcion;
import excepciones.ElementoNoEncontradoExcepcion;
import interfaces.CRUD;
import dao.ObservacionesDAO;

import java.util.ArrayList;
import java.util.List;

public class GestorObservaciones extends Gestor<Observacion>  {

    private final CRUD<Observacion> dao = new  ObservacionesDAO();

    public GestorObservaciones(Class<Observacion> tipoEntidad) {
        super(tipoEntidad);
    }

    GestorAdopciones gestorAdopciones = new GestorAdopciones(Adopcion.class);

    public void registrarObservacion(Observacion observacion) throws AccesoDatosException, ElementoDuplicadoExcepcion, ElementoNoEncontradoExcepcion {
        if(observacion == null){
            throw new DatoInvalidoException("La observación no puede ser nula");
        }

        Adopcion a = gestorAdopciones.buscarEnBD(observacion.getIdAdopcion());


        dao.crear(observacion);

        agregar(observacion);
    }

    public List<Observacion> listarPorAdopcion(int idAdopcion) {
        List<Observacion> observacionesXAdop = new ArrayList<Observacion>();
        for(Observacion o : listarDesdeBD()){
            if(o.getIdAdopcion() == idAdopcion){
                observacionesXAdop.add(o);
            }
        }
        return observacionesXAdop;
    }

    public void guardarEnBD(Observacion o) throws AccesoDatosException {
        dao.crear(o);
    }

    public List<Observacion> listarDesdeBD() throws AccesoDatosException {
        return dao.mostrarTodos();
    }

    public Observacion buscarEnBD(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        return dao.buscarPorId(id);
    }

    public void actualizarEnBD(int id, Observacion o) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        dao.modificar(id,o);
    }

    public void eliminarDeBD(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        dao.eliminar(id);
    }
}
