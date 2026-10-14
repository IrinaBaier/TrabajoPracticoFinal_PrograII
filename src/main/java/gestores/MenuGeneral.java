package gestores;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuGeneral {
    public static void menuGeneral(){
        Scanner entradaTeclado = new Scanner(System.in);
        int opcion;

        System.out.println("                     .");
        System.out.println("                    / V\\");
        System.out.println("                  / `  /");
        System.out.println("                 <<   |");
        System.out.println("                 /    |");
        System.out.println("               /      |");
        System.out.println("             /        |");
        System.out.println("           /    \\  \\ /");
        System.out.println("          (      ) | |");
        System.out.println("  ___|   _/  | |");
        System.out.println("<___\\__)\\-)\n");

        do{
            System.out.println("+-----------------------------------------+");
            System.out.println("| ¿Quién nos visita?                   |");
            System.out.println("+-----------------------------------------+");
            System.out.println("| \t 1.- Administrador                |");
            System.out.println("| \t 2.- Adoptante                    |");
            System.out.println("| \t 3.- Perrito                      |");
            System.out.println("| \t 0.- Salir                        |");
            System.out.println("+-----------------------------------------+");

            System.out.println("Elige la opcion correcta: ");
            try{
                opcion = entradaTeclado.nextInt();
            }catch (InputMismatchException e){
                opcion = 4;
                entradaTeclado.next();
            }

            switch (opcion){
                case 1:
                    MenuAdmin.menuGeneralAdmin();
                    break;
                case 2:
                    System.out.println("Me voy al menú de Adoptante");
                    break;
                case 3:
                    System.out.println("La suplantación de identidad perruna en una ofensa grave. Su accionar ha sido reportado al consejo canino");
                    break;
                case 0:
                    System.out.println("¡Nos olemos luego!");
                    break;
                default:
                    System.out.println("Tenés que ingresar una opción válida  (¬_¬)");
                    break;
            }
        }while (opcion > 3);


    }
}
