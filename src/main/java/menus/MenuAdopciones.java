package menus;

import enums.EstadoSolicitud;
import enums.ObservacionSolicitud;
import estructuras.Adopcion;
import estructuras.Observacion;
import estructuras.Perro;
import estructuras.Solicitud;
import excepciones.AccesoDatosException;
import excepciones.DatoInvalidoException;
import excepciones.ElementoDuplicadoExcepcion;
import excepciones.ElementoNoEncontradoExcepcion;
import gestores.GestorAdopciones;
import gestores.GestorObservaciones;
import gestores.GestorPerros;
import gestores.GestorSolicitud;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuAdopciones {
    public static void menuAdopciones(){
        Scanner entradaTeclado = new Scanner(System.in);
        GestorPerros gestorPerros =  new GestorPerros(Perro.class);
        GestorAdopciones gestorAdopciones =  new GestorAdopciones(Adopcion.class);
        GestorSolicitud gestor = new GestorSolicitud(Solicitud.class);
        GestorObservaciones gestorObservaciones = new GestorObservaciones(Observacion.class);

        int opcion;

        do{
            System.out.println("+-----------------------------------------+");
            System.out.println("| \t Menu Adopciones                  |");
            System.out.println("+-----------------------------------------+");
            System.out.println("| \t 1.- Cargar Adopción              |");
            System.out.println("| \t 2.- Mostrar Adopciones           |");
            System.out.println("| \t 3.- Mostrar Solicitudes          |");
            System.out.println("| \t 4.- Agregar Observación          |");
            System.out.println("| \t 0.- Salir                        |");
            System.out.println("+-----------------------------------------+");

            System.out.println("Elige la opción correcta");

            // Capturo el error de que el usuario pueda introducir algo diferente a un int por teclado
            try{
                opcion = entradaTeclado.nextInt();
            }catch (InputMismatchException e){
                opcion = 5;
                entradaTeclado.next();
            }

            switch (opcion){
                case 1:
                    Adopcion nuevaAdopcion = new Adopcion();
                    boolean valido = false;
                    nuevaAdopcion.setIdSolicitud(null);
                    do{
                        System.out.println("Ingresa el ID del perrito a adoptar");
                        try{
                            opcion = entradaTeclado.nextInt();
                            nuevaAdopcion.setIdPerro(opcion);
                            valido = true;
                        }catch (InputMismatchException e){
                            System.out.println("Debes ingresar el id numérico");
                            entradaTeclado.next();
                        }catch (DatoInvalidoException e){
                            System.out.println("Dato invalido " + e.getMessage());
                        }

                    }while(!valido);

                    valido = false;
                    do{
                        System.out.println("Ingresa el ID del adoptante");
                        try{
                            opcion = entradaTeclado.nextInt();
                            nuevaAdopcion.setIdAoptante(opcion);
                            valido = true;
                        }catch (InputMismatchException e){
                            System.out.println("Debes ingresar el id numérico");
                            entradaTeclado.next();
                        }catch (DatoInvalidoException e){
                            System.out.println("Dato invalido " + e.getMessage());
                        }

                    }while(!valido);

                    System.out.println("¿La adopción se está realizando ahora? S/N");
                    String op = entradaTeclado.next();
                    nuevaAdopcion.setFecha(LocalDateTime.now());

                    if(!(op.equalsIgnoreCase("S") || op.equalsIgnoreCase("Sí") || op.equalsIgnoreCase("si")))
                    {
                        do{
                            valido = false;
                            try {
                                System.out.println("Ingresá la fecha exacta de ingreso (dd/MM/yyyy HH:mm:ss):");
                                String fecha = entradaTeclado.nextLine();

                                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                                nuevaAdopcion.setFecha(LocalDateTime.parse(fecha, formato));
                                valido = true;

                            } catch (DateTimeParseException e) {
                                System.out.println("Formato de fecha inválido. Usá dd/MM/yyyy HH:mm:ss.");
                            }catch (DatoInvalidoException e){
                                System.out.println("Dato invalido " + e.getMessage());
                            }
                        }while (!valido);

                    }
                    try{
                        gestorAdopciones.registrarAdopcion(nuevaAdopcion);
                        System.out.println("Guau!! Otra adopción exitosa :D");
                    }catch (ElementoNoEncontradoExcepcion e){
                        System.out.println("Ups! Este perrito ya no existe.");
                    } catch (ElementoDuplicadoExcepcion e) {
                        System.out.println("ya registraste esta adopción anteriormente");
                    }
                    break;
                case 2:
                    System.out.println("Adopciones registradas:");


                    try {
                        List<Adopcion> adopciones = gestorAdopciones.listarDesdeBD();
                        if (adopciones.isEmpty()) {
                            System.out.println("No hay adopciones registradas.");
                        } else {
                            for (Adopcion a : adopciones) {
                                System.out.println(a);
                                if(gestorObservaciones.listarPorAdopcion(a.getId()).size() > 0){
                                    System.out.println("OBSERVACIONES");
                                    for(Observacion o : gestorObservaciones.listarPorAdopcion(a.getId())){
                                        System.out.println(o);
                                    }
                                }
                                System.out.println();
                                System.out.println("+--------------------------------------------+");
                                System.out.println();

                            }
                        }
                    } catch (AccesoDatosException e) {
                        System.out.println("Error al listar adopciones: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Solicitudes pendientes:");

                    try {
                        List<Solicitud> solicitudes = gestor.listarDesdeBD();
                        for (Solicitud s : solicitudes) {
                            if (s.getEstado() == EstadoSolicitud.PENDIENTE) {
                                System.out.println(s);
                            }
                        }
                        System.out.println("Elegir ID de solicitud a modificar:");
                        int idAmodificar = -1;
                        do {
                            try {
                                idAmodificar = entradaTeclado.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Tenés que ingresar un número.");
                                entradaTeclado.next();
                                idAmodificar = -1;
                            }
                        } while (idAmodificar < 0);
                        Solicitud solicitud = gestor.buscarEnBD(idAmodificar);
                        if (solicitud.getEstado() == EstadoSolicitud.PENDIENTE) {
                            System.out.println("+-----------------------------------------------+");
                            System.out.println("|          GESTIONAR SOLICITUD                  |");
                            System.out.println("+-----------------------------------------------+");
                            System.out.println("|  1.- Aprobar solicitud                       |");
                            System.out.println("|  2.- Rechazar solicitud                      |");
                            System.out.println("|  0.- Volver                                  |");
                            System.out.println("+-----------------------------------------------+");
                            int opcionSolicitud = -1;
                            do {
                                try {
                                    opcionSolicitud = entradaTeclado.nextInt();
                                    if (opcionSolicitud < 0 || opcionSolicitud > 2) {
                                        System.out.println("Opción inválida, vuelve a intentarlo.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Tenés que ingresar un número.");
                                    entradaTeclado.next();
                                    opcionSolicitud = -1;
                                }
                            } while (opcionSolicitud < 0 || opcionSolicitud > 2);
                            switch (opcionSolicitud) {
                                case 1:

                                    Perro perro = gestorPerros.buscarEnBD(solicitud.getIdPerro());
                                    if (!perro.puedeSerAdoptado()) {
                                        System.out.println("El perro ya no está disponible.");
                                        break;
                                    }
                                    Adopcion adopcion = new Adopcion(solicitud.getIdPerro(),solicitud.getIdAdoptante(),solicitud.getID(), LocalDateTime.now() );
                                    gestorAdopciones.registrarAprobacion(adopcion, perro, solicitud);
                                    System.out.println("¡Adopción registrada correctamente!");
                                    break;
                                case 2:
                                    ObservacionSolicitud[] motivos = ObservacionSolicitud.values();
                                    System.out.println("+-----------------------------------------------+");
                                    System.out.println("|          MOTIVO DE RECHAZO                    |");
                                    System.out.println("+-----------------------------------------------+");
                                    System.out.println("|  1.- Espacio insuficiente                     |");
                                    System.out.println("|  2.- Falta de tiempo                          |");
                                    System.out.println("|  3.- Incompatibilidad con otras mascotas      |");
                                    System.out.println("|  4.- No apto económicamente                   |");
                                    System.out.println("|  5.- Convivencia no permitida                 |");
                                    System.out.println("|  6.- Otro                                     |");
                                    System.out.println("+-----------------------------------------------+");

                                    int opcionMotivo = -1;
                                    do {
                                        try {
                                            opcionMotivo = entradaTeclado.nextInt();

                                            if (opcionMotivo < 1 || opcionMotivo > motivos.length) {
                                                System.out.println("Motivo inválido, vuelve a intentarlo.");
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("Tenés que ingresar un número.");
                                            entradaTeclado.next();
                                            opcionMotivo = -1;
                                        }
                                    } while (opcionMotivo < 1 || opcionMotivo > motivos.length);
                                    solicitud.setEstado(EstadoSolicitud.RECHAZADA);
                                    solicitud.setMotivoRechazo(motivos[opcionMotivo - 1]);
                                    gestor.actualizarEnBD(idAmodificar, solicitud);
                                    System.out.println("Solicitud rechazada correctamente.");
                                    break;
                                case 0:
                                    System.out.println("Volviendo al menú anterior...");
                                    break;
                            }
                        } else {
                            System.out.println("Ingresá el ID de una solicitud pendiente");
                        }
                    } catch (AccesoDatosException e) {
                        System.out.println("Error de acceso a la base de datos: "+ e.getMessage());

                    } catch (ElementoNoEncontradoExcepcion e) {
                        System.out.println("No se encontró la solicitud: " + e.getMessage());
                    }
                    break;
                case 4:
                   Observacion observacion = new Observacion();
                    valido = false;

                    do{
                        System.out.println("Ingresa el ID de la adopción a la que le deseas realizar una observación");
                        try{
                            opcion = entradaTeclado.nextInt();
                            observacion.setIdAdopcion(opcion);
                            valido = true;
                        }catch (InputMismatchException e){
                            System.out.println("Debes ingresar el id numérico");
                            entradaTeclado.next();
                        }catch (DatoInvalidoException e){
                            System.out.println("Dato invalido " + e.getMessage());
                        }

                    }while(!valido);

                    valido = false;
                    entradaTeclado.nextLine();
                    do{
                        System.out.println("Ingresa la descripción");
                        try{
                            observacion.setDescripcion(entradaTeclado.nextLine());
                            valido = true;
                        }catch (DatoInvalidoException e){
                            System.out.println("Dato invalido " + e.getMessage());
                        }

                    }while(!valido);

                    System.out.println("¿Desea ingresar una fecha específica para la observación? S/N");
                    op = entradaTeclado.next();
                    entradaTeclado.nextLine();
                    observacion.setFecha(LocalDateTime.now());

                    if((op.equalsIgnoreCase("S") || op.equalsIgnoreCase("Sí") || op.equalsIgnoreCase("si")))
                    {
                        do{
                            valido = false;
                            try {
                                System.out.println("Ingresá la fecha exacta (dd/MM/yyyy HH:mm:ss):");
                                String fecha = entradaTeclado.nextLine();

                                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                                observacion.setFecha(LocalDateTime.parse(fecha, formato));
                                valido = true;

                            } catch (DateTimeParseException e) {
                                System.out.println("Formato de fecha inválido. Usá dd/MM/yyyy HH:mm:ss.");
                            }catch (DatoInvalidoException e){
                                System.out.println("Dato invalido " + e.getMessage());
                            }
                        }while (!valido);

                    }
                    try{
                        gestorObservaciones.registrarObservacion(observacion);
                        System.out.println("Hemos registrado la observación");
                    } catch (ElementoDuplicadoExcepcion e) {
                        System.out.println("ya registraste esta observación");
                    } catch (AccesoDatosException e) {
                        System.out.println("Error " + e.getMessage());
                    }catch (ElementoNoEncontradoExcepcion e){
                        System.out.println("No es posible registrar una observación sobre una adopción inexistente");
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
