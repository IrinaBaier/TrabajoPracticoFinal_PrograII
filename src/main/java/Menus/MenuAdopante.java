package Menus;

import estructuras.Adoptante;
import excepciones.ElementoDuplicadoExcepcion;
import gestores.GestorAdoptante;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAdopante {
    private static final GestorAdoptante gestor = new GestorAdoptante(Adoptante.class);
    public static void menuAdoptantes(){
        Scanner entradaTeclado = new Scanner(System.in);
        int opcion;

        do{
            System.out.println("+-----------------------------------------+");
            System.out.println("| \t Menu Adoptante                   |");
            System.out.println("+-----------------------------------------+");
            System.out.println("| \t 1.- Cargar Adoptante             |");
            System.out.println("| \t 2.- Mostrar Adoptantes           |");
            System.out.println("| \t 0.- Salir                        |");
            System.out.println("+-----------------------------------------+");

            System.out.println("Elige la opción correcta");

            // Capturo el error de que el usuario pueda introducir algo diferente a un int por teclado
            try{
                opcion = entradaTeclado.nextInt();
            }catch (InputMismatchException e){
                opcion = 9;
                entradaTeclado.next();
            }

            switch (opcion){
                case 1:
                    System.out.println("Ingresa el nombre del adoptante");
                    String nombre = entradaTeclado.next();

                    System.out.println("Ingresa el DNI:");
                    int dni = 0;
                    do {
                        try {
                            dni = entradaTeclado.nextInt();
                        } catch (InputMismatchException e) {
                            dni = 0;
                            entradaTeclado.next();
                        }
                        if (dni == 0) {System.out.println("DNI inválido, vuelve a intentarlo:");}

                    } while (dni == 0);

                    entradaTeclado.nextLine();
                    System.out.println("Ingresa el teléfono:");
                    String telefono = entradaTeclado.nextLine();

                    System.out.println("Ingresa el email:");
                    String email = entradaTeclado.nextLine();

                    System.out.println("Ingresa el domicilio:");
                    String domicilio = entradaTeclado.nextLine();

                    Adoptante adoptante = new Adoptante();
                    try {
                        gestor.registrarAdoptante(adoptante);
                        System.out.println("Muy biem!! El Adoptante fue exitosamente registrado :D");
                    }catch (ElementoDuplicadoExcepcion e){
                        System.out.println("Ups! Este Adoptante ya se estaba registrado.");
                    }
                    break;

                case 2:
                    do {
                        System.out.println("+-----------------------------------------+");
                        System.out.println("| \t Listar Adoptantes                |");
                        System.out.println("+-----------------------------------------+");
                        System.out.println("| \t 1.- Listar todos                 |");
                        System.out.println("| \t 2.- Listar por Domicilio         |");
                        System.out.println("| \t 3.- Listar ordenados por Nombre  |");
                        System.out.println("| \t 0.- Volver                       |");
                        System.out.println("+-----------------------------------------+");

                        try {
                            opcion = entradaTeclado.nextInt();
                        } catch (InputMismatchException e) {
                            opcion = 9;
                            entradaTeclado.next();
                        }

                        entradaTeclado.nextLine();

                        switch (opcion) {
                            case 1:
                                gestor.listar();
                                break;

                            case 2:
                                System.out.println("Ingrese el domicilio a buscar:");
                                String domicilioBuscar = entradaTeclado.nextLine();

                                gestor.listarPorDomicilio(domicilioBuscar);
                                break;

                            case 3:
                                gestor.listarOrdenadosPorNombre();
                                break;

                            case 0:
                                System.out.println("Volviendo al menú de adoptantes...");
                                break;

                            default:
                                System.out.println("Tenés que ingresar una opción válida.");
                                break;
                        }

                    } while (opcion != 0);

                    break;
                case 0:
                    System.out.println("Nos vemos!! :D");
                    break;
                default:
                    System.out.println("Tenés que ingresar una opción válida");
                    break;
            }

        }while(opcion != 0);

    }
}



