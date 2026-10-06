import java.time.LocalDate;

public class Perro extends Animal {
    private String raza;
    private String porte; //próximamente Enum
    private String temperamento; //próximamente Enum

    public Perro() {
    }

    public Perro(String nombre, int edad, LocalDate fechaEntrada, String raza, String porte, String temperamento) {
        super(nombre, edad, fechaEntrada);
        this.raza = raza;
        this.porte = porte;
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
