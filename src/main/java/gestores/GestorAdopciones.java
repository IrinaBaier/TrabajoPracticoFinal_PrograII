package gestores;

import dao.AdopcionDAO;
import dao.RegistroSolicitudDAO;
import enums.EstadoAnimal;
import estructuras.Adopcion;
import estructuras.Adoptante;
import estructuras.Perro;
import estructuras.Solicitud;
import excepciones.AccesoDatosException;
import excepciones.DatoInvalidoException;
import excepciones.ElementoDuplicadoExcepcion;
import excepciones.ElementoNoEncontradoExcepcion;
import interfaces.CRUD;

import java.util.ArrayList;
import java.util.List;

public class GestorAdopciones extends Gestor <Adopcion>{

    private final CRUD<Adopcion> dao = new AdopcionDAO();
    private final RegistroSolicitudDAO registroSolicitudDAO = new RegistroSolicitudDAO();
    private final GestorPerros gestorPerros = new GestorPerros(Perro.class);
    private final GestorAdoptante gestorAdoptante = new GestorAdoptante(Adoptante.class);

    public GestorAdopciones(Class<Adopcion> tipoEntidad) {
        super(tipoEntidad);
    }

    public void registrarAdopcion(Adopcion nuevaAdopcion) throws ElementoDuplicadoExcepcion, ElementoNoEncontradoExcepcion {
        Perro perro = gestorPerros.buscarEnBD(nuevaAdopcion.getIdPerro());
        gestorAdoptante.buscarEnBD(nuevaAdopcion.getIdAoptante());
        if(!perro.puedeSerAdoptado()){
            throw new DatoInvalidoException("el perro no esta disponible (estado: " + perro.getEstado() + ")");}
        guardarEnBD(nuevaAdopcion);
        agregar(nuevaAdopcion);
        perro.marcarComoNoDisponible();
        gestorPerros.actualizarEnBD(perro.getId(), perro);

    }
// metodo pensado para en el futuro de ser necesario mostrar el historial de adopciones con sus adoptantes

    public List<Adopcion> obtenerHistorialAdoptante(int idAdoptante) throws AccesoDatosException {
        List<Adopcion> historial = new ArrayList<>();
        for (Adopcion adopcion : listarDesdeBD()) {
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
