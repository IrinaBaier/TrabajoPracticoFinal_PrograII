package gestores;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAdopciones {
    public static void menuAdopciones(){
        Scanner entradaTeclado = new Scanner(System.in);
        int opcion;

        do{
            System.out.println("+-----------------------------------------+");
            System.out.println("| \t 1.- Cargar Adopcion              |");
            System.out.println("| \t 2.- Mostrar Adopcion             |");
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
                    System.out.println("Me voy a Cargar Adopcion");
                    break;
                case 2:
                    System.out.println("Me voy a Mostrar Adopcion");
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
