package estructuras;

import enums.Edad;
import enums.EstadoAnimal;
import excepciones.DatoInvalidoException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Animal {
    private static int contadorId = 1;
    private int id;
    private String nombre;
    private Edad edad;
    private LocalDate fechaEntrada;

    private EstadoAnimal estado;

    public Animal(String nombre, Edad edad, LocalDate fechaEntrada) {
        this.id = contadorId++;
        setNombre(nombre);
        setEdad(edad);
        setFechaEntrada(fechaEntrada);
    }

    public Animal() {
        this.id = contadorId++;
    }

    public int getId() {
        return id;
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

    public Edad getEdad() {
        return edad;
    }

    public void setEdad(Edad edad) {
        this.edad = edad;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        if(fechaEntrada == null){
            throw new DatoInvalidoException("La fecha no puede ser null");
        }else if(fechaEntrada.isAfter(LocalDate.now())){
            throw new DatoInvalidoException("No se puede ingresar una fecha futura");
        }
        this.fechaEntrada = fechaEntrada;
    }

    public EstadoAnimal getEstado() {
        return estado;
    }

    public void setEstado(EstadoAnimal estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", fechaEntrada=" + fechaEntrada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
