import java.util.Scanner;

public class Potioncraft {

    private static String menu(Scanner scanner){


        System.out.println("\t\t\tPOTIONCRAFT\t\t\t");
        System.out.println("-----------------------------------");
        System.out.println("1. CREAR POCIONES");
        System.out.println("2. VENDER POCIONES");
        System.out.println("3. COMPRAR INGREDIENTES");
        System.out.println("4. REABASTECER COMERCIANTES");
        System.out.println("5. MOSTRAR ESTADÍSTICAS DEL JUEGO");
        System.out.println("6. MOSTRAR COMERCIANTES");
        System.out.println("7. MOSTRAR POCIONES DISPONIBLES");
        System.out.println("8. SALIR");
        System.out.print("\nSeleccione una opción... ");
        return scanner.next();
    }


    public static void main(String[] args) {

        Juego j;
        boolean cargarEstado = false;
        Scanner scanner = new Scanner(System.in);


        System.out.println("¿Desea cargar un estado previo del juego? [S/N]");
        String respuesta = scanner.next();
        if (respuesta.equalsIgnoreCase("S")) {
            cargarEstado = true;
        }

        if (cargarEstado) {

            j = Juego.cargarEstado("estado.bin");
            if (j == null) {

                j = new Juego();
            }
        } else {

            j = new Juego();
        }


        boolean salir=false;
        while (!salir){
            switch (menu(new Scanner(System.in))){
                case "1" -> {
                    j.crearPocion(false);
                }
                case "2" -> {
                    j.venderPocion(false);
                }
                case "3"->{
                    j.comprarIngredientes();
                }
                case "4" -> {
                    j.reabastecerComerciantes();
                    j.getJugador().restarReputacion();
                }
                case "5" -> j.getJugador().mostrarEstadisticas();
                case "6" -> j.getListaComerciante().imprimir();
                case "7"-> j.mostrarPociones();
                case "8"->{
                    String s;
                    boolean b = false;

                    while (!b) {
                        System.out.println("¿Desea guardar el estado? [S/N]");
                        s = scanner.next();

                        if (s.equalsIgnoreCase("S")) {
                            j.guardarEstado("estado.bin");
                            b = true;
                            salir = true;
                        } else if (s.equalsIgnoreCase("N")) {
                            b = true;
                            salir = true;
                        } else {
                            System.out.print("Selecciona una opción válida. Pulsa INTRO para continuar...");
                            new Scanner(System.in).nextLine();
                        }
                    }

                    scanner.close();
                }
                default -> {
                    System.out.print("Selecciona una opción válida. Pulsa INTRO para continuar...");
                    new Scanner(System.in).nextLine();
                }
            }
        }
    }
}