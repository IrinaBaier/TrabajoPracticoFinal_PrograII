import Enums.Edad;
import Enums.Porte;
import Enums.Temperamento;

import java.time.LocalDate;

public class Perro extends Animal {
    private String raza;
    private Porte porte;
    private Temperamento temperamento;

    public Perro() {
    }

    public Perro(String nombre, Edad edad, LocalDate fechaEntrada, String raza, Porte porte, Temperamento temperamento) {
        super(nombre, edad, fechaEntrada);
        this.raza = raza;
        this.porte = porte;
        this.temperamento = temperamento;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
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
        return "Perro{" +
                super.toString() +
                "raza='" + raza + '\'' +
                ", porte='" + porte + '\'' +
                ", temperamento='" + temperamento + '\'' +
                '}';
    }
}
