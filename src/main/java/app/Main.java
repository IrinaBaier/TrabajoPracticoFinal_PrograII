package app;

import excepciones.ElementoDuplicadoExcepcion;
import gestores.GestorObservaciones;
import menus.MenuGeneral;
import dao.*;
import enums.*;
import estructuras.*;
import excepciones.AccesoDatosException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        try {
            // Los datos de prueba se cargan solo si la BD está vacía.
            // Si no, la segunda vez que se ejecuta el programa se repiten los IDs (PRIMARY KEY) y se corta.
            if (new AdoptanteDAO().mostrarTodos().isEmpty()) {
                cargarAdoptantes();
                cargarPerros();
                cargarSolicitudes();
                cargarAdopciones();
                cargarObservaciones();
            }
            sincronizarContadores();
        } catch (AccesoDatosException e) {
            System.out.println("No se pudo conectar con la base de datos: " + e.getMessage());
            return;
        }
        MenuGeneral.menuGeneral();
    }

        // Los id se generan con contadores static que arrancan en 1 en cada ejecución.
        // Los ponemos en (último id de la BD + 1) para no repetir id al cargar desde los menús.
        private static void sincronizarContadores () {
            int maxPerro = 0;
            for (Perro p : new PerroDAO().mostrarTodos()) {
                maxPerro = Math.max(maxPerro, p.getId());
            }
            Animal.setContadorId(maxPerro + 1);

            int maxAdoptante = 0;
            for (Adoptante a : new AdoptanteDAO().mostrarTodos()) {
                maxAdoptante = Math.max(maxAdoptante, a.getId());
            }
            Adoptante.setContador(maxAdoptante + 1);

            int maxAdopcion = 0;
            for (Adopcion a : new AdopcionDAO().mostrarTodos()) {
                maxAdopcion = Math.max(maxAdopcion, a.getId());
            }
            Adopcion.setContador(maxAdopcion + 1);

            int maxSolicitud = 0;
            for (Solicitud s : new SolicitudDAO().mostrarTodos()) {
                maxSolicitud = Math.max(maxSolicitud, s.getId());
            }
            Solicitud.setContador(maxSolicitud + 1);

            int maxObservacion = 0;
            for (Observacion o : new ObservacionesDAO().mostrarTodos()) {
                maxObservacion = Math.max(maxObservacion, o.getId());
            }
            Observacion.setContador(maxObservacion + 1);

        }


    private static void cargarAdoptantes() {

        AdoptanteDAO dao = new AdoptanteDAO();

        dao.crear(new Adoptante("Juan Perez", 30111222, "2235123456", "juan@gmail.com", "Mar del Plata"));
        dao.crear(new Adoptante("Maria Gomez", 28999888, "2234987654", "maria@gmail.com", "Batan"));
        dao.crear(new Adoptante("Carlos Lopez", 35123456, "2234556677", "carlos@gmail.com", "Mar del Plata"));
        dao.crear(new Adoptante("Laura Fernandez", 32777888, "2234112233", "laura@gmail.com", "Miramar"));
        dao.crear(new Adoptante("Pedro Ramirez", 25111999, "2234778899", "pedro@gmail.com", "Mar del Plata"));
        dao.crear(new Adoptante("Sofia Martinez", 40555666, "2234667788", "sofia@gmail.com", "Santa Clara"));
        dao.crear(new Adoptante("Diego Torres", 33999111, "2234332211", "diego@gmail.com", "Mar del Plata"));
        dao.crear(new Adoptante("Camila Rodriguez", 42111222, "2234998877", "camila@gmail.com", "Batan"));
    }

    private static void cargarPerros() {

        PerroDAO dao = new PerroDAO();

        cargarPerro(dao, new Perro("Toby", Edad.CACHORRO, LocalDate.of(2026, 10, 1), "Labrador", Porte.GRANDE, Temperamento.JUGUETON), EstadoAnimal.DISPONIBLE);
        cargarPerro(dao, new Perro("Luna", Edad.ADULTO, LocalDate.of(2026, 9, 15), "Mestiza", Porte.MEDIANO, Temperamento.CALMADO), EstadoAnimal.ADOPTADO);
        cargarPerro(dao, new Perro("Rocky", Edad.ANCIANO, LocalDate.of(2026, 8, 20), "Ovejero", Porte.GRANDE, Temperamento.SOCIABLE), EstadoAnimal.DISPONIBLE);
        cargarPerro(dao, new Perro("Mora", Edad.CACHORRO, LocalDate.of(2026, 11, 1), "Caniche", Porte.CHICO, Temperamento.ANSIOSO), EstadoAnimal.DISPONIBLE);
        cargarPerro(dao, new Perro("Max", Edad.ADULTO, LocalDate.of(2026, 7, 10), "Golden Retriever", Porte.GRANDE, Temperamento.JUGUETON), EstadoAnimal.ADOPTADO);
        cargarPerro(dao, new Perro("Nina", Edad.ADULTO, LocalDate.of(2026, 10, 15), "Mestiza", Porte.CHICO, Temperamento.CALMADO), EstadoAnimal.DISPONIBLE);
        cargarPerro(dao, new Perro("Roky", Edad.CACHORRO, LocalDate.of(2026, 11, 5), "Pitbull", Porte.GRANDE, Temperamento.SOCIABLE), EstadoAnimal.NO_DISPONIBLE);
        cargarPerro(dao, new Perro("Simba", Edad.ANCIANO, LocalDate.of(2026, 6, 20), "Mestizo", Porte.MEDIANO, Temperamento.CALMADO), EstadoAnimal.DISPONIBLE);
        cargarPerro(dao, new Perro("Kira", Edad.ADULTO, LocalDate.of(2026, 9, 25), "Border Collie", Porte.MEDIANO, Temperamento.SOCIABLE), EstadoAnimal.ADOPTADO);
        cargarPerro(dao, new Perro("Bobby", Edad.CACHORRO, LocalDate.of(2026, 11, 8), "Mestizo", Porte.CHICO, Temperamento.JUGUETON), EstadoAnimal.DISPONIBLE);
    }

    private static void cargarPerro(PerroDAO dao, Perro perro, EstadoAnimal estado) {
        perro.setEstado(estado);
        dao.crear(perro);
    }

    private static void cargarSolicitudes() {

        SolicitudDAO dao = new SolicitudDAO();

        dao.crear(new Solicitud(1, 2, LocalDateTime.of(2026, 10, 20, 10, 30), EstadoSolicitud.APROBADA, ObservacionSolicitud.OTRO));
        dao.crear(new Solicitud(2, 5, LocalDateTime.of(2026, 10, 22, 11, 0), EstadoSolicitud.APROBADA, ObservacionSolicitud.OTRO));
        dao.crear(new Solicitud(3, 1, LocalDateTime.of(2026, 11, 2, 9, 15), EstadoSolicitud.PENDIENTE, ObservacionSolicitud.OTRO));
        dao.crear(new Solicitud(4, 3, LocalDateTime.of(2026, 11, 3, 14, 0), EstadoSolicitud.RECHAZADA, ObservacionSolicitud.FALTA_DE_TIEMPO));
        dao.crear(new Solicitud(5, 4, LocalDateTime.of(2026, 11, 4, 16, 30), EstadoSolicitud.PENDIENTE, ObservacionSolicitud.OTRO));
        dao.crear(new Solicitud(6, 7, LocalDateTime.of(2026, 10, 25, 12, 0), EstadoSolicitud.APROBADA, ObservacionSolicitud.OTRO));
        dao.crear(new Solicitud(7, 6, LocalDateTime.of(2026, 11, 6, 10, 0), EstadoSolicitud.PENDIENTE, ObservacionSolicitud.OTRO));
        dao.crear(new Solicitud(8, 8, LocalDateTime.of(2026, 11, 7, 15, 30), EstadoSolicitud.RECHAZADA, ObservacionSolicitud.INCOMPATIBILIDAD_CON_OTRAS_MASCOTAS));
    }

    private static void cargarAdopciones() {

        AdopcionDAO dao = new AdopcionDAO();

        dao.crear(new Adopcion(2, 1, 1, LocalDateTime.of(2026, 10, 21, 12, 0)));
        dao.crear(new Adopcion(5, 2, 2, LocalDateTime.of(2026, 10, 23, 13, 30)));
        dao.crear(new Adopcion(7, 6, 6, LocalDateTime.of(2026, 10, 26, 11, 0)));
    }

    private static void cargarObservaciones() {

        ObservacionesDAO dao = new ObservacionesDAO();
        dao.crear(new Observacion(1, LocalDateTime.of(2026, 10, 28, 10, 0), "Luna se adapto correctamente a su nuevo hogar."));
        dao.crear(new Observacion(1, LocalDateTime.of(2026, 11, 5, 10, 30), "Buena convivencia con los integrantes de la familia."));
        dao.crear(new Observacion(2, LocalDateTime.of(2026, 10, 30, 15, 0), "Max presenta buena alimentacion y comportamiento."));
        dao.crear(new Observacion(3, LocalDateTime.of(2026, 11, 2, 12, 0), "Roky se murió."));
        dao.crear(new Observacion(3, LocalDateTime.of(2026, 11, 5, 12, 0), "Roky resucitó."));

    }
}



