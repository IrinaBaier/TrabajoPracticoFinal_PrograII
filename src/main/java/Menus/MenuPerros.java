package Menus;

import enums.Edad;
import enums.Porte;
import enums.Temperamento;
import estructuras.Perro;
import excepciones.AccesoDatosException;
import excepciones.ElementoDuplicadoExcepcion;
import excepciones.ElementoNoEncontradoExcepcion;
import gestores.GestorPerros;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuPerros {
    private static final GestorPerros gestor = new GestorPerros(Perro.class);
    public static void MenuPerros(){
        Scanner entradaTeclado = new Scanner(System.in);

        int opcion;

        do{
            System.out.println("+-----------------------------------------+");
            System.out.println("| \t Menú de gestión de perritos      |");
            System.out.println("+-----------------------------------------+");
            System.out.println("| \t 1.- Cargar Perrito               |");
            System.out.println("| \t 2.- Mostrar Perritos             |");
            System.out.println("| \t 3.- Sacar un Perrito sistema    |");
            System.out.println("| \t 0.- Salir                        |");
            System.out.println("+-----------------------------------------+");

            System.out.println("Elige la opción correcta: ");
            try{
                opcion = entradaTeclado.nextInt();
            }catch (InputMismatchException e){
                opcion = 4;
                entradaTeclado.next();
            }
            entradaTeclado.nextLine();

            switch (opcion){
                case 1:
                    Perro perrito = new Perro();

                    System.out.println("Ingresa el nombre del perrito");
                    String nombre = entradaTeclado.next();

                    System.out.println("Ingresa la edad del perrito:");
                    System.out.println("+-----------------------------------------+");
                    System.out.println("| \t 0.- CACHORRO                     |");
                    System.out.println("| \t 1.- ADULTO                       |");
                    System.out.println("| \t 2.- ANCIANO                      |");
                    System.out.println("+-----------------------------------------+");
                    Edad edad = Edad.CACHORRO;
                    do{
                        try{
                            opcion = entradaTeclado.nextInt();
                        }catch (InputMismatchException e){
                            opcion = 3;
                            entradaTeclado.next();
                        }
                        entradaTeclado.next();
                        if (opcion >= 0 && opcion < Edad.values().length) {
                            edad = Edad.values()[opcion];
                        } else {
                            System.out.println("Opción inválida, vuelve a intentarlo");
                        }
                    }while(opcion < 0 || opcion >= Edad.values().length);

                    System.out.println("¿Estás cargando un ingreso reciente? S/N");
                    String op = entradaTeclado.next();
                    LocalDate fechaEntrada = LocalDate.now();;
                    boolean fechaValida = false;

                    if(!(op.equalsIgnoreCase("S") || op.equalsIgnoreCase("Sí") || op.equalsIgnoreCase("si")))
                    {
                        do{
                            try {
                                System.out.println("Ingresá la fecha de ingreso (dd/MM/yyyy):");
                                String fecha = entradaTeclado.next();

                                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                fechaEntrada = LocalDate.parse(fecha, formato);
                                fechaValida = true;

                            } catch (DateTimeParseException e) {
                                System.out.println("Formato de fecha inválido. Usá dd/MM/yyyy.");
                            }
                        }while (!fechaValida);

                    }

                    System.out.println("ingresá la raza del perrito:");
                    String raza = entradaTeclado.next();

                    System.out.println("Ingresa el porte del perrito:");
                    System.out.println("+-----------------------------------------+");
                    System.out.println("| \t 0.- CHICO                        |");
                    System.out.println("| \t 1.- MEDIANO                      |");
                    System.out.println("| \t 2.- GRANDE                       |");
                    System.out.println("+-----------------------------------------+");
                    Porte porte = Porte.CHICO;
                    do{
                        try{
                            opcion = entradaTeclado.nextInt();
                        }catch (InputMismatchException e){
                            opcion = 3;
                            entradaTeclado.next();
                        }
                        entradaTeclado.next();
                        if (opcion >= 0 && opcion < Porte.values().length) {
                            porte = Porte.values()[opcion];
                        } else {
                            System.out.println("Opción inválida, vuelve a intentarlo");
                        }
                    }while(opcion < 0 || opcion >= Porte.values().length);

                    System.out.println("Ingresa el temperamento del perrito:");
                    System.out.println("+-----------------------------------------+");
                    System.out.println("| \t 0.- CALMADO                      |");
                    System.out.println("| \t 1.- JUGUETÓN                     |");
                    System.out.println("| \t 2.- ANSIOSO                      |");
                    System.out.println("| \t 3.- AGRESIVO                     |");
                    System.out.println("| \t 4.- SOCIABLE                     |");
                    System.out.println("+-----------------------------------------+");
                    Temperamento temperamento = Temperamento.AGRESIVO;
                    do{
                        try{
                            opcion = entradaTeclado.nextInt();
                        }catch (InputMismatchException e){
                            opcion = 5;
                            entradaTeclado.next();
                        }
                        entradaTeclado.next();
                        if (opcion >= 0 && opcion < Temperamento.values().length) {
                            temperamento = Temperamento.values()[opcion];
                        } else {
                            System.out.println("Opción inválida, vuelve a intentarlo");
                        }
                    }while(opcion < 0 || opcion >= Temperamento.values().length);


                    try {
                        gestor.registrar(perrito);
                        System.out.println("Muy bien!! Perrito exitosamente registrado :D");
                    }catch (ElementoDuplicadoExcepcion e){
                        System.out.println("Ups! Este perrito ya se estaba registrado.");
                    }catch (AccesoDatosException e) {
                        System.out.println("Error con la base de datos: " + e.getMessage());
                    }

                    break;
                case 2:
                    int opcion2;
                    do {
                        System.out.println("+-----------------------------------------+");
                        System.out.println("| \t Listar Perritos                  |");
                        System.out.println("+-----------------------------------------+");
                        System.out.println("| \t 1.- Listar todos                 |");
                        System.out.println("| \t 2.- Listar disponibles           |");
                        System.out.println("| \t 0.- Volver                       |");
                        System.out.println("+-----------------------------------------+");

                        try {
                            opcion2 = entradaTeclado.nextInt();
                        } catch (InputMismatchException e) {
                            opcion2 = -1;
                            entradaTeclado.next();
                        }
                        entradaTeclado.nextLine();

                        List<Perro> lista = null;

                        try {
                            switch (opcion2) {
                                case 1:
                                    lista = gestor.listarDesdeBD();
                                    break;

                                case 2:
                                    lista = gestor.listarDisponibles();
                                    break;

                                case 0:
                                    System.out.println("Volviendo al menú de perritos...");
                                    break;

                                default:
                                    System.out.println("Tenés que ingresar una opción válida.");
                                    break;
                            }
                        } catch (AccesoDatosException e) {
                            System.out.println("No se pudo leer la base de datos: " + e.getMessage());
                        }

                        if (lista != null) {
                            if (lista.isEmpty()) {
                                System.out.println("No hay perritos para mostrar.");
                            } else {
                                for (Perro p : lista) {
                                    System.out.println(p);
                                }
                            }
                        }
                    } while (opcion2 != 0);
                    break;

                case 3:
                    System.out.println("Ingresa el ID del perrito que quieres sacar del sistema");
                    try{
                        opcion = entradaTeclado.nextInt();
                    }catch (InputMismatchException e){
                        opcion = 0;
                        entradaTeclado.next();
                    }
                    try{
                        gestor.quitarDelRegistro(opcion);
                    }catch (ElementoNoEncontradoExcepcion e){
                        System.out.println("No puedes quitar al perrito, porque no existe :o ");
                    }catch (AccesoDatosException e) {
                        System.out.println("Error con la base de datos: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("...");
                    break;
                default:
                    System.out.println("Tenés que ingresar una opción válida  (¬_¬)");
                    break;
            }
        }while (opcion > 3 || opcion < 0);
    }
}
