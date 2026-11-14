package gestores;

import excepciones.DatoInvalidoException;
import excepciones.ElementoDuplicadoExcepcion;
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
}

