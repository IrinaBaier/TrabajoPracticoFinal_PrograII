package estructuras;

import enums.Edad;
import enums.EstadoAnimal;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Animal {
    private static int contadorId = 1;
    private int id;
    private int codigo;
    private String nombre;
    private Edad edad;
    private LocalDate fechaEntrada;

    private EstadoAnimal estado;

    public Animal(String nombre, Edad edad, LocalDate fechaEntrada) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.edad = edad;
        this.fechaEntrada = fechaEntrada;
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
        return codigo == animal.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
