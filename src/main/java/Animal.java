import java.time.LocalDate;

public abstract class Animal {
    private static int contadorId = 1;
    private int id;
    private String nombre;
    private int edad;
    private LocalDate fechaEntrada;

    public Animal(String nombre, int edad, LocalDate fechaEntrada) {
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", fechaEntrada=" + fechaEntrada;
    }
}
