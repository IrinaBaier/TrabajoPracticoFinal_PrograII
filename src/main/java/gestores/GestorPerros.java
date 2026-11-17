package gestores;

import dao.PerroDAO;
import estructuras.Perro;
import excepciones.*;
import interfaces.CRUD;

import java.util.ArrayList;
import java.util.List;

public class GestorPerros  extends Gestor<Perro>   {

    private final CRUD<Perro> dao = new PerroDAO();

    public GestorPerros(Class<Perro> tipoEntidad) {
        super(tipoEntidad);
    }

    public List<Perro> listarDisponibles() throws AccesoDatosException{
        List<Perro> disponibles = new ArrayList<>();
        for (Perro perro : dao.mostrarTodos()) {
            if (perro.puedeSerAdoptado()) {
                disponibles.add(perro); }
        }

        return disponibles;
    }


    //Generamos un especie de atomicidad en el registro del perro para asegurarnos de que la lista del gestor y la base de datos se mantengan en sintonía
    public void registrar(Perro a) throws ElementoDuplicadoExcepcion, AccesoDatosException, DatoInvalidoException {
        //Primero validamos que no exista en la lista
        if (a == null) {
            throw new DatoInvalidoException("El perro no puede ser nulo");
        }

        if (existe(a)) {
            throw new ElementoDuplicadoExcepcion(
                    "Perro", a.getId()
            );
        }
        //Ya podemos guardar en la DB
        dao.crear(a);

        //Si esto no falla, la agregamos a la lista
        agregar(a);
    }

    public List<Perro> listarDesdeBD() throws AccesoDatosException {
        return dao.mostrarTodos();
    }

    public Perro buscarEnBD(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        return dao.buscarPorId(id);
    }

    public void actualizarEnBD(int id, Perro a) throws AccesoDatosException, ElementoNoEncontradoExcepcion {
        dao.modificar(id,a);
    }

    public void quitarDelRegistro(int id) throws AccesoDatosException, ElementoNoEncontradoExcepcion {

        ///Primero validamos que exista en la lista
        Perro p = dao.buscarPorId(id);

        ///Ya podemos eliminarl de la DB
        dao.eliminar(id);
        /// lo saca de la lista si estaba. si no, no hace nada
        if (existe(p)) {
            eliminar(id);
        }
    }


}
