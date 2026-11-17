package menus;

import estructuras.Adoptante;
import excepciones.AccesoDatosException;
import excepciones.DatoInvalidoException;
import excepciones.ElementoDuplicadoExcepcion;
import gestores.GestorAdoptante;

import java.util.InputMismatchException;
import java.util.List;
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
                opcion = -1;
                entradaTeclado.next();

            }
            entradaTeclado.nextLine();

            switch (opcion) {
                case 1:
                    Adoptante adoptante = new Adoptante();
                    boolean valido;

                    valido=false;
                    do {
                        System.out.println("Ingresa el nombre del adoptante");
                        try {
                            adoptante.setNombre(entradaTeclado.nextLine());
                            valido = true;
                        } catch (DatoInvalidoException e) {
                            System.out.println("Dato invalido " + e.getMessage());
                        }

                    }while (!valido);

                    valido=false;
                    do{
                        System.out.println("Ingresa el DNI:");
                        try {
                            int dni = entradaTeclado.nextInt();
                            adoptante.setDni(dni);
                            valido = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Dato invalido, ingresa solo numeros");
                        } catch (DatoInvalidoException e) {
                            System.out.println("Dato invalido " + e.getMessage());
                        }
                        entradaTeclado.nextLine();

                    }while (!valido);

                    valido = false;
                    do {
                        System.out.println("Ingresa el telefono (10 dígitos)");
                        try {
                            adoptante.setTelefono(entradaTeclado.nextLine());
                            valido = true;
                        } catch (DatoInvalidoException e) {
                            System.out.println("Dato invalido: " + e.getMessage());
                        }
                    } while (!valido);

                    valido = false;
                    do {
                        System.out.println("Ingresa el email:");
                        try {
                            adoptante.setEmail(entradaTeclado.nextLine());
                            valido = true;
                        } catch (DatoInvalidoException e) {
                            System.out.println("Dato invalido: " + e.getMessage());
                        }
                    } while (!valido);

                    valido = false;
                    do {
                        System.out.println("Ingresa el domicilio:");
                        try {
                            adoptante.setDomicilio(entradaTeclado.nextLine());
                            valido = true;
                        } catch (DatoInvalidoException e) {
                            System.out.println("Dato invalido: " + e.getMessage());
                        }
                    } while (!valido);

                    try {
                        gestor.registrarAdoptante(adoptante);
                        System.out.println("Muy bien!! El Adoptante fue exitosamente registrado :D");
                    } catch (ElementoDuplicadoExcepcion e) {
                        System.out.println("Ups! Ya hay un adoptante registrado con ese DNI.");
                    }catch (AccesoDatosException e) {
                        System.out.println("Error con la base de datos: " + e.getMessage());
                    }
                    break;

                case 2:
                    int opcion2;

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
                            opcion2 = entradaTeclado.nextInt();
                        } catch (InputMismatchException e) {
                            opcion2 = -1;
                            entradaTeclado.next();
                        }

                        entradaTeclado.nextLine();

                        List<Adoptante> lista = null;

                    try {
                        switch (opcion2) {
                            case 1:
                                lista= gestor.listarDesdeBD();
                                break;

                            case 2:
                                System.out.println("Ingrese el domicilio a buscar:");
                                String domicilioBuscar = entradaTeclado.nextLine().trim();
                                lista= gestor.listarPorDomicilio(domicilioBuscar);
                                break;

                            case 3:
                                lista= gestor.listarOrdenadosPorNombre();
                                break;

                            case 0:
                                System.out.println("Volviendo al menú de adoptantes...");
                                break;

                            default:
                                System.out.println("Tenés que ingresar una opción válida.");
                                break;
                        }

                    } catch (AccesoDatosException e){
                        System.out.println("No se pudo leer la base de datos: " + e.getMessage());}

                        if (lista != null) {
                            if (lista.isEmpty()) {
                                System.out.println("No hay adoptantes para mostrar.");
                            } else {
                                for (Adoptante a : lista) {
                                    System.out.println(a);
                                }
                            }
                        }
                    } while (opcion2 != 0);
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



