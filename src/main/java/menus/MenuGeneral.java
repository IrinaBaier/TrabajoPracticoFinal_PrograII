package menus;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuGeneral {
    public static void menuGeneral(){
        Scanner entradaTeclado = new Scanner(System.in);
        int opcion;


        System.out.println("            /)-_-(\\        /)-_-(\\");
        System.out.println("             (o o)          (o o)");
        System.out.println("     .-----__/\\o/            \\o/\\__-----.");
        System.out.println("    /  __      /              \\      __  \\");
        System.out.println("\\__/\\ /  \\_\\ |/                \\| /_/  \\ /\\__/");
        System.out.println("     \\\\     ||                  ||      \\\\");
        System.out.println("     //     ||                  ||      //");
        System.out.println("     |\\     |\\                  /|     /|");

        System.out.println(" Baier Irina, Márquez Estefanía, Orellano Pablo ");



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
                    MenuAdopanteCliente.MenuAdopanteCliente();
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
        }while (opcion != 0);


    }
}
