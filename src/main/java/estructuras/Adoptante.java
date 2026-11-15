package estructuras;

import enums.EstadoAnimal;
import excepciones.DatoInvalidoException;
import interfaces.Identificable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Adoptante implements Identificable {

    private int id;
    static int contador = 1;
    private String nombre;
    private int dni;
    private String telefono;
    private String email;
    private String domicilio;

    private List<Adopcion> historialAdopciones;

    public Adoptante(String nombre, int dni, String telefono, String email, String domicilio) {
        this.id = contador++;
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
        setEmail(email);
        setDomicilio(domicilio);
        this.historialAdopciones = new ArrayList<>();
    }

    public Adoptante() {
        this.id = contador++;
        this.historialAdopciones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public static int getContador() {
        return contador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatoInvalidoException("El nombre no puede estar vacío");
        }else if(nombre.trim().length() < 3){
            throw new DatoInvalidoException("El nombre no puede tener menos de 3 caracteres");
        }
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        if(!(dni >= 1000000 && dni <=99999999)){
            throw new DatoInvalidoException("El DNI no es válido");
        }
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if(telefono.trim().isEmpty()){
            throw new DatoInvalidoException("El teléfono no puede estar vacío");
        }
        // Elimino espacios en blanco, guiones y paréntesis
        String numero = telefono.replaceAll("[\\s()-]", "");
        if(numero.length() != 10){
            throw new DatoInvalidoException("Un teléfono válido debe tener 10 dígitos");
        }
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null || email.trim().isEmpty()){
            throw new DatoInvalidoException("El mail no puede estar vacío");
        }
        if(!email.contains("@")){
            throw new DatoInvalidoException("No ingresaste una dirección de mail válida");
        }
        this.email = email;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        if(domicilio == null || domicilio.trim().isEmpty()){
            throw new DatoInvalidoException("El domicilio no puede estar vacío");
        }
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "estructuras.Adoptante{" +
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

    public boolean validarDatos(Perro perro) {
        if (perro == null || perro.getEstado() != EstadoAnimal.DISPONIBLE) {
            return false;
        }
        return true;
    }
    public boolean realizarSolicitud(Perro perro) {
        if (!validarDatos(perro) ) {
            return false;
        }
        else{
        Solicitud nuevaSolicitud = new Solicitud(this.id, perro.getId());
        return true;
        }
    }

    //--------No debería existir, todas las adopciones se deberían gestionar desde adopciones
    public boolean adoptar(Perro perro) {
        if (!validarDatos(perro)) {
            return false;
        }
        else {
            perro.setEstado(EstadoAnimal.ADOPTADO);
            Adopcion nuevaAdopcion = new Adopcion();
            this.historialAdopciones.add(nuevaAdopcion);
            return true;
        }
    }

//-------------------------------------

    public List<Adopcion> getHistorialAdopciones() {
        return historialAdopciones;
    }

}

