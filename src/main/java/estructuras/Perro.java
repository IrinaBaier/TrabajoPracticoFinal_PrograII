package estructuras;

import enums.Edad;
import enums.EstadoAnimal;
import enums.Porte;
import enums.Temperamento;
import excepciones.DatoInvalidoException;
import interfaces.Adoptable;
import interfaces.Identificable;

import java.time.LocalDate;

public class Perro extends Animal implements Adoptable, Identificable {
    private String raza;
    private Porte porte;
    private Temperamento temperamento;

    public Perro() {
    }

    public Perro(String nombre, Edad edad, LocalDate fechaEntrada, String raza, Porte porte, Temperamento temperamento) {
        super(nombre, edad, fechaEntrada);
        setRaza(raza);
        this.porte = porte;
        this.temperamento = temperamento;
    }

    public Perro(int id, String nombre, Edad edad, LocalDate fechaEntrada, EstadoAnimal estado, String raza, Porte porte, Temperamento temperamento) {
        super(id);
        setNombre(nombre);
        setEdad(edad);
        setFechaEntrada(fechaEntrada);
        setEstado(estado);
        setRaza(raza);
        setPorte(porte);
        setTemperamento(temperamento);
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        if(raza == null || raza.trim().isEmpty()){
            throw new DatoInvalidoException("La raza no puede estar vacía");
        }
        if(raza.length() < 5){
            throw new DatoInvalidoException("La raza no es una palabra válida");
        }
        this.raza = raza;
    }

    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    public Temperamento getTemperamento() {
        return temperamento;
    }

    public void setTemperamento(Temperamento temperamento) {
        this.temperamento = temperamento;
    }

    @Override
    public String toString() {
        return "estructuras.Perro{" +
                super.toString() +
                "raza='" + raza + '\'' +
                ", porte='" + porte + '\'' +
                ", temperamento='" + temperamento + '\'' +
                '}';
    }

    @Override
    public boolean puedeSerAdoptado() {
        return getEstado() == EstadoAnimal.DISPONIBLE;
    }

    @Override
    public void marcarComoDisponible() {
        setEstado(EstadoAnimal.DISPONIBLE);
    }

    @Override
    public void marcarComoNoDisponible() {
        setEstado(EstadoAnimal.ADOPTADO);
    }
}
