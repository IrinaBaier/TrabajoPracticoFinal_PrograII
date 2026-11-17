package menus;

import estructuras.Adoptante;
import estructuras.Perro;
import estructuras.Solicitud;
import excepciones.AccesoDatosException;
import excepciones.DatoInvalidoException;
import excepciones.ElementoDuplicadoExcepcion;
import excepciones.ElementoNoEncontradoExcepcion;
import gestores.GestorAdoptante;
import gestores.GestorPerros;
import gestores.GestorSolicitud;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuAdopanteCliente {
    private static final GestorAdoptante gestorAdoptantes = new GestorAdoptante(Adoptante.class);
    private static final GestorPerros gestorPerros = new GestorPerros(Perro.class);
    private static final GestorSolicitud gestorSolicitud = new GestorSolicitud(Solicitud.class);
    public static void MenuAdopanteCliente(){
        Scanner entradaTeclado = new Scanner(System.in);
        int opcion;

        do{
            System.out.println("+-----------------------------------------+");
            System.out.println("| \t Menu Adoptante                       |");
            System.out.println("+-----------------------------------------+");
            System.out.println("| \t 1.- Quiero ser adoptante             |");
            System.out.println("| \t 2.- Mostrar Perritos                 |");
            System.out.println("| \t 3.- Ya soy adoptante, hacer solicitud|");
            System.out.println("| \t 0.- Salir                            |");
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
                        System.out.println("Ingresa su nombre");
                        try {
                            adoptante.setNombre(entradaTeclado.nextLine());
                            valido = true;
                        } catch (DatoInvalidoException e) {
                            System.out.println("Dato invalido " + e.getMessage());
                        }

                    }while (!valido);

                    valido=false;
                    do{
                        System.out.println("Ingresa su DNI:");
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
                        System.out.println("Ingresa su telefono (10 dígitos)");
                        try {
                            adoptante.setTelefono(entradaTeclado.nextLine());
                            valido = true;
                        } catch (DatoInvalidoException e) {
                            System.out.println("Dato invalido: " + e.getMessage());
                        }
                    } while (!valido);

                    valido = false;
                    do {
                        System.out.println("Ingresa su email:");
                        try {
                            adoptante.setEmail(entradaTeclado.nextLine());
                            valido = true;
                        } catch (DatoInvalidoException e) {
                            System.out.println("Dato invalido: " + e.getMessage());
                        }
                    } while (!valido);

                    valido = false;
                    do {
                        System.out.println("Ingresa su  domicilio:");
                        try {
                            adoptante.setDomicilio(entradaTeclado.nextLine());
                            valido = true;
                        } catch (DatoInvalidoException e) {
                            System.out.println("Dato invalido: " + e.getMessage());
                        }
                    } while (!valido);

                    try {
                        gestorAdoptantes.registrarAdoptante(adoptante);
                        System.out.println("+------------------------------------------------------------+");
                        System.out.println("| \t \"¡Felicidades! Ya sos adoptante.\"                      |");
                        System.out.println("+------------------------------------------------------------+");
                        System.out.println("| ¿Querés realizar una solicitud de adopción?                |");
                        System.out.println("| 1.- Sí                                                     |");
                        System.out.println("| 0.- No                                                     |");
                        System.out.println("+------------------------------------------------------------+");
                        System.out.print("Elegí una opción: ");
                        int opcionSolicitud;
                        do {
                            try {
                                opcionSolicitud = entradaTeclado.nextInt();
                                if (opcionSolicitud != 0 && opcionSolicitud != 1) {
                                    System.out.println("Ingresá 1 para Sí o 0 para No.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Tenés que ingresar un número.");
                                entradaTeclado.next();
                                opcionSolicitud = -1;
                            }
                        } while (opcionSolicitud != 0 && opcionSolicitud != 1);

                        if (opcionSolicitud == 1) {
                            System.out.println("Elegiste realizar una solicitud de adopción.");
                            System.out.println("Estos son los Perritos disponibles.");
                            List<Perro> lista2 = null;
                            try {
                                lista2 = gestorPerros.listarDisponibles();
                            } catch (AccesoDatosException e) {
                                System.out.println("No se pudo leer la base de datos: " + e.getMessage());
                            }
                            if (lista2 != null) {
                                if (lista2.isEmpty()) {
                                    System.out.println("No hay perritos para mostrar.");
                                } else {
                                    for (Perro p : lista2) {
                                        System.out.println(p);
                                    }
                                }
                            }
                            if (lista2 == null || lista2.isEmpty()) {
                                System.out.println("No hay perritos disponibles para solicitar.");
                                break;
                            }
                            Solicitud solicitud = new Solicitud( adoptante.getId(),lista2.get(0).getId() );
                            boolean valido2 = false;
                            do {
                                System.out.println("Ingresá el ID del perrito que querés solicitar:");
                                try {
                                    int idPerro = entradaTeclado.nextInt();
                                    solicitud.setIdPerro(idPerro);
                                    boolean estaDisponible = false;
                                    for (Perro perro : lista2) {
                                        if (perro.getId() == idPerro) {
                                            estaDisponible = true;
                                            break;
                                        }
                                    }
                                    if (estaDisponible) {
                                        valido2 = true;
                                    } else {
                                        System.out.println("Ingresá el ID de un perrito disponible de la lista.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Dato inválido, ingresá solo números.");
                                    entradaTeclado.next();
                                } catch (DatoInvalidoException e) {
                                    System.out.println("Dato inválido: " + e.getMessage());
                                }
                            } while (!valido2);

                            try {
                                gestorSolicitud.registrarSolicitud(solicitud);
                                System.out.println("¡Solicitud registrada correctamente!");
                            } catch (ElementoDuplicadoExcepcion e) {
                                System.out.println("La solicitud ya está registrada.");
                            } catch (AccesoDatosException e) {
                                System.out.println("Error al guardar la solicitud: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Está bien. Podés realizar una solicitud más adelante.");
                        }
                    } catch (ElementoDuplicadoExcepcion e) {
                        System.out.println("Ups! Ya hay un adoptante registrado con ese DNI.");
                    }catch (AccesoDatosException e) {
                        System.out.println("Error con la base de datos: " + e.getMessage());
                    }
                    break;
                case 2:
                    List<Perro> lista = null;
                        try {
                            lista = gestorPerros.listarDisponibles();
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
                    break;
                        //agrego para que solicite adopcion si ya esta registrado el adoptante por el admin,
                        // el adoptante se busca por dni, no por id.
                case 3:
                    System.out.println("Ingresá tu DNI:");
                    try {
                        int dni = entradaTeclado.nextInt();
                        Adoptante existente = null;
                        for (Adoptante a : gestorAdoptantes.listarDesdeBD()) {
                            if (a.getDni() == dni) {
                                existente = a;
                                break;
                            }
                        }
                        if (existente == null) {
                            System.out.println("No hay un adoptante registrado con ese DNI. Registrate con la opción 1.");
                            break;
                        }

                        System.out.println("¡Hola " + existente.getNombre() + "! Estos son los perritos disponibles:");
                        for (Perro p : gestorPerros.listarDisponibles()) {
                            System.out.println(p);
                        }
                        System.out.println("Ingresá el ID del perrito que querés solicitar:");
                        Perro perro = gestorPerros.buscarEnBD(entradaTeclado.nextInt());

                        if (!perro.puedeSerAdoptado()) {
                            System.out.println("Ese perrito no está disponible para adopción.");
                        } else {
                            gestorSolicitud.registrarSolicitud(new Solicitud(existente.getId(), perro.getId()));
                            System.out.println("¡Solicitud registrada correctamente!");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Dato inválido, ingresá solo números.");
                        entradaTeclado.nextLine();
                    } catch (ElementoNoEncontradoExcepcion e) {
                        System.out.println("No existe un perrito con ese ID.");
                    } catch (ElementoDuplicadoExcepcion e) {
                        System.out.println("La solicitud ya está registrada.");
                    } catch (AccesoDatosException e) {
                        System.out.println("Error con la base de datos: " + e.getMessage());
                    }
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

