package gestores;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAdmin {
    public static void menuGeneralAdmin(){
        Scanner entradaTeclado = new Scanner(System.in);
        int opcion;

        do{
            System.out.println("+-----------------------------------------+");
            System.out.println("| \t 1.- Gestionar Perrito            |");
            System.out.println("| \t 2.- Gestionar Adoptantes         |");
            System.out.println("| \t 3.- Gestionar Adopciones         |");
            System.out.println("| \t 4.- Ver Métricas                 |");
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
                    System.out.println("Me voy al menú de gestión de perritos");
                    break;
                case 2:
                    System.out.println("Me voy al menú de gestión de adoptantes");
                    break;
                case 3:
                    System.out.println("Me voy al menú de gestión de adopciones");
                    break;
                case 4:
                    System.out.println("Muestro las mértricas");
                    break;
                case 0:
                    System.out.println("Adios!! :D");
                    break;
                default:
                    System.out.println("Tenés que ingresar una opción válida");
                    break;
            }


        }while(opcion != 0);

    }
}
