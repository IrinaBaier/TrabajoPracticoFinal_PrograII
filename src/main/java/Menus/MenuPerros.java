package Menus;

import enums.Edad;
import enums.Porte;
import enums.Temperamento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPerros {
    public static void MenuPerros(){
        Scanner entradaTeclado = new Scanner(System.in);
        int opcion;

        do{
            System.out.println("+-----------------------------------------+");
            System.out.println("| \t 1.- Cargar Perrito               |");
            System.out.println("| \t 2.- Mostrar Perritos             |");
            System.out.println("| \t 3.- Sacar un Perritol sistema    |");
            System.out.println("| \t 0.- Salir                        |");
            System.out.println("+-----------------------------------------+");

            System.out.println("Elige la opción correcta: ");
            try{
                opcion = entradaTeclado.nextInt();
            }catch (InputMismatchException e){
                opcion = 4;
                entradaTeclado.next();
            }

            switch (opcion){
                case 1:

                    System.out.println("Ingresa el nombre del perrito");
                    String nombre = entradaTeclado.next();

                    System.out.println("Ingresa la edad del perrito:");
                    System.out.println("+-----------------------------------------+");
                    System.out.println("| \t 0.- CACHORRO                     |");
                    System.out.println("| \t 1.- ADULTO                       |");
                    System.out.println("| \t 2.- ANCIANO                      |");
                    System.out.println("+-----------------------------------------+");
                    do{
                        opcion = entradaTeclado.nextInt();
                        entradaTeclado.next();
                        if (opcion >= 0 && opcion < Edad.values().length) {
                            Edad edad = Edad.values()[opcion];
                        } else {
                            System.out.println("Opción inválida, vuelve a intentarlo");
                        }
                    }while(opcion < 0 || opcion >= Edad.values().length);

                    System.out.println("¿Estás cargando un ingreso reciente? S/N");
                    String op = entradaTeclado.next();
                    LocalDate fechaEntrada;

                    if(op.equalsIgnoreCase("S") || op.equalsIgnoreCase("Sí") || op.equalsIgnoreCase("si")){
                        fechaEntrada = LocalDate.now();
                    }else{
                        try {
                            System.out.println("Ingresá la fecha de ingreso (dd/MM/yyyy):");
                            String fecha = entradaTeclado.next();

                            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            fechaEntrada = LocalDate.parse(fecha, formato);

                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha inválido. Usá dd/MM/yyyy.");
                        }
                    }

                    System.out.println("ingresá la raza del perrito:");
                    String raza = entradaTeclado.next();

                    System.out.println("Ingresa el porte del perrito:");
                    System.out.println("+-----------------------------------------+");
                    System.out.println("| \t 0.- CHICO                        |");
                    System.out.println("| \t 1.- MEDIANO                      |");
                    System.out.println("| \t 2.- GRANDE                       |");
                    System.out.println("+-----------------------------------------+");
                    do{
                        opcion = entradaTeclado.nextInt();
                        entradaTeclado.next();
                        if (opcion >= 0 && opcion < Porte.values().length) {
                            Porte porte = Porte.values()[opcion];
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
                    do{
                        opcion = entradaTeclado.nextInt();
                        entradaTeclado.next();
                        if (opcion >= 0 && opcion < Temperamento.values().length) {
                            Temperamento temperamento = Temperamento.values()[opcion];
                        } else {
                            System.out.println("Opción inválida, vuelve a intentarlo");
                        }
                    }while(opcion < 0 || opcion >= Temperamento.values().length);
                    ///Implemento el gestor de carga de perrito
                    break;
                case 2:
                   ///Implemento el gestor para mostrar los perritos
                    break;
                case 3:
                    System.out.println("Ingresa el ID del perrito que quieres sacar del sistema");
                    ///Implemento el gestor para eliminar perritos
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
