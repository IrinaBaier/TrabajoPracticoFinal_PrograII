package gestores;

import dao.AdopcionDAO;
import dao.RegistroSolicitudDAO;
import enums.EstadoAnimal;
import estructuras.Adopcion;
import estructuras.Perro;
import estructuras.Solicitud;
import excepciones.AccesoDatosException;
import excepciones.ElementoDuplicadoExcepcion;
import excepciones.ElementoNoEncontradoExcepcion;
import interfaces.CRUD;

import java.util.ArrayList;
import java.util.List;

public class GestorAdopciones extends Gestor <Adopcion>{

    private final CRUD<Adopcion> dao = new AdopcionDAO();
    private final RegistroSolicitudDAO registroSolicitudDAO = new RegistroSolicitudDAO();
    private final GestorPerros gestorPerros = new GestorPerros(Perro.class);


    public GestorAdopciones(Class<Adopcion> tipoEntidad) {
        super(tipoEntidad);
    }

    public void registrarAdopcion(Adopcion nuevaAdopcion) throws ElementoDuplicadoExcepcion, ElementoNoEncontradoExcepcion {
        Perro perro = gestorPerros.buscarEnBD(nuevaAdopcion.getIdPerro());
        agregar(nuevaAdopcion);
        guardarEnBD(nuevaAdopcion);
        perro.setEstado(EstadoAnimal.ADOPTADO);
        gestorPerros.actualizarEnBD(perro.getId(), perro);
        gestorPerros.modificar(perro.getId(), perro);
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
