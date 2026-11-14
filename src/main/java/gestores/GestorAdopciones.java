package gestores;

import dao.AdopcionDAO;
import estructuras.Adopcion;
import interfaces.CRUD;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorAdopciones extends Gestor <Adopcion>{

    private final CRUD<Adopcion> dao = new AdopcionDAO();


    public GestorAdopciones(Class<Adopcion> tipoEntidad) {
        super(tipoEntidad);
    }
    public List<Adopcion> listarPorFecha(LocalDate fecha) {
        List<Adopcion> lista = new ArrayList<>();
        for (Adopcion a : elementos) {
            if (a.getFecha() != null && a.getFecha().isEqual(fecha.atStartOfDay())) {
                lista.add(a);
            }
        }
        return lista;
    }



}
