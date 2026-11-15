package gestores;


import excepciones.DatoInvalidoException;
import excepciones.ElementoDuplicadoExcepcion;
import excepciones.ElementoNoEncontradoExcepcion;
import interfaces.Identificable;

import java.util.ArrayList;
import java.util.List;

public class Gestor<T extends Identificable> {

    protected List<T> elementos;
    protected Class<T> tipoEntidad;

    public Gestor(Class<T> tipoEntidad) {
        this.elementos = new ArrayList<>();
        this.tipoEntidad = tipoEntidad;
    }

    public void agregar(T entidad) throws ElementoDuplicadoExcepcion{
        if (entidad == null ){
            throw new DatoInvalidoException("No se puede agregar " + tipoEntidad.getSimpleName() + " nulo");
        }
        if (existe(entidad)){
            throw new ElementoDuplicadoExcepcion(tipoEntidad.getSimpleName(), entidad.getId());
        }
        elementos.add(entidad);
    }

    public boolean existe(T elemento) {
        return elementos.contains(elemento);
    }


    public T buscar(int id) throws ElementoNoEncontradoExcepcion {
        for (T elemento : elementos) {
            if (elemento.getId() == id) {
                return elemento;
            }
        }
        throw new ElementoNoEncontradoExcepcion(tipoEntidad.getSimpleName(), id);
    }

    public void eliminar(int id) throws ElementoNoEncontradoExcepcion {
        T elemento = buscar(id);
        elementos.remove(elemento);
    }

    public List<T> listar() {
        return new ArrayList<>(elementos);
    }

    public int cantidad() {
        return elementos.size();
    }


}

