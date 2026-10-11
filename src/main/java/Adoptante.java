import Enums.EstadoAnimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Adoptante {

    private int id;
    static int contador = 1;
    private String nombre;
    private int dni;
    private int telefono;
    private String email;
    private String domicilio;

    private List<Adopcion> historialAdopciones;

    public Adoptante(String nombre, int dni, int telefono, String email, String domicilio) {
        this.id = contador++;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.domicilio = domicilio;
        this.historialAdopciones = new ArrayList<>();
    }

    public Adoptante() {
        this.id = contador++;
        this.historialAdopciones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Adoptante.contador = contador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Adoptante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dni=" + dni +
                ", telefono=" + telefono +
                ", email='" + email + '\'' +
                ", domicilio='" + domicilio + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Adoptante adoptante)) return false;
        return dni == adoptante.dni;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }


    public boolean adoptar(Perro perro) {
        if (!validarDatos() || perro == null || perro.getEstado() != EstadoAnimal.DISPONIBLE) {
            return false;
        }
       else {
            perro.setEstado(EstadoAnimal.ADOPTADO);
            Adopcion nuevaAdopcion = new Adopcion(this, perro);
            this.historialAdopciones.add(nuevaAdopcion);
            return true;
        }
    }

    public boolean validarDatos() {
        if (nombre != null && !nombre.trim().isEmpty() && // .trim para validar que el string no tenga espacios vacios
                dni >= 10000000 && // Valida que tenga al menos 8 cifras
                telefono > 0 && // hay que definir cuantas cifras tiene
                email != null && email.contains("@") && !email.trim().isEmpty() &&
                domicilio != null && !domicilio.trim().isEmpty()) {

            return true;
        }
    }


    public boolean realizarSolicitud(Perro perro) {
        if (!validarDatos() || perro == null || perro.getEstado() != EstadoAnimal.DISPONIBLE) {
            return false;
        }
        else{
        Solicitud nuevaSolicitud = new Solicitud(this, perro);
        return true;
    }
    }

    public List<Adopcion> getHistorialAdopciones() {
        return historialAdopciones;
    }

}

