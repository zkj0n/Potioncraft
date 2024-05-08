import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Juego {
    private ListaComerciante listaComerciante;
    private List<Ingrediente> listaIngredientes;
    private List<Pocion> listaPociones;
    private Jugador jugador;

    public Juego() {
        this.listaComerciante= new ListaComerciante();
        this.listaIngredientes=new ArrayList<>();
        this.listaComerciante.cargaInicial();
        this.listaPociones=new ArrayList<>();
        BD.rellenarListaIngredientes(listaIngredientes);
        BD.rellenarListaPociones(listaPociones, listaIngredientes);
        this.jugador=new Jugador();

    }

    public void reabastecerComerciantes(){
        Path rutaLog= Paths.get("abastecimientos.log");
        List<String> lineas=new ArrayList<>();
        try{
            if (Files.notExists(rutaLog)){
                Files.createFile(rutaLog);
            }
        } catch (Exception e){
            System.out.println("No se ha podido crear el archivo.");
        }

        try{
            lineas.add(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+
                    " "+LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) +
                    " - INICIO REABASTECIMIENTO COMERCIANTE");
            try {
                Files.write(rutaLog, lineas, StandardOpenOption.APPEND);
            } catch (IOException ex) {
                System.out.println("No se ha podido escribir el archivo: "+ex.getMessage());
            }
        }catch (Exception e){
            System.out.println("Ocurrió un error al escribir un archivo: " + e.getMessage());
        }
        for (Comerciante c: this.getListaComerciante().getListaComerciantes()){
            if (c instanceof Minero){
                ((Minero) c).reabastecer();
            } else if (c instanceof Herborista) {
                ((Herborista) c).reabastecer();
            } else if (c instanceof Recolector) {
                ((Recolector) c).reabastecer();
            } else if (c instanceof Mercader) {
                ((Mercader) c).reabastecer();
            }
        }
    }

    public ListaComerciante getListaComerciante() {
        return listaComerciante;
    }

    public void mostrarPociones(){
        Set<String> impresas=new HashSet<>();
        for (Pocion p: listaPociones){

            if (!impresas.contains(p.getNombre())){
                System.out.println("- "+p.getNombre().toUpperCase());
                impresas.add(p.getNombre());
            }
            for (Map.Entry<Ingrediente, Integer> e: p.getIngredientes().entrySet()){
                System.out.println("\t--> NOMBRE: " + e.getKey().getNombre()+ " | UNIDADES: "+e.getValue());
            }
        }
    }

    public void crearPocion(){

        Scanner scanner = new Scanner(System.in);

        for (Map.Entry<Ingrediente, Integer> entry : jugador.getMapaIngredientes().entrySet()) {
            entry.setValue(99);
        }
        Set<String> impresas=new HashSet<>();

        // Mostrar pociones
        for (Pocion p : listaPociones){
            boolean puede=true;

            for (Map.Entry<Ingrediente, Integer> mapaP : p.getIngredientes().entrySet()){
                if (jugador.getMapaIngredientes().get(mapaP.getKey()) < mapaP.getValue() ){
                    puede=false;
                    break;
                }
            }

            if (puede){
                if (!impresas.contains(p.getNombre())){
                    System.out.println(p.getId()+"-"+p.getNombre().toUpperCase());
                    impresas.add(p.getNombre());
                }
                for (Map.Entry<Ingrediente, Integer> e: p.getIngredientes().entrySet()){
                    System.out.println("\t--> NOMBRE: " + e.getKey().getNombre()+ " | UNIDADES: "+e.getValue());
                }
            }
        }


        try{

            System.out.print("Elige el ID de la poción que deseas crear: ");
            int idSeleccionado = scanner.nextInt();
            boolean puede=true;
            Pocion pocionSalida = null;
            for (Pocion p: listaPociones){
                if (p.getId()==idSeleccionado){
                    for (Map.Entry<Ingrediente, Integer> mapaP : p.getIngredientes().entrySet()){
                        if (jugador.getMapaIngredientes().get(mapaP.getKey()) < mapaP.getValue() ){
                            puede=false;
                        }
                    }

                    if (puede){
                        for (Map.Entry<Ingrediente, Integer> m : p.getIngredientes().entrySet()){
                            jugador.getMapaIngredientes().put(
                                    m.getKey(),
                                    jugador.getMapaIngredientes().get(m.getKey())-m.getValue()
                            );
                        }
                        pocionSalida=p;
                    }

                    break;
                }

            }

            if (puede) {
                int cantidadAnterior=jugador.getMapaPociones().get(pocionSalida);
                jugador.getMapaPociones().put(pocionSalida, cantidadAnterior+1);
            }

        }catch (Exception e){
            System.out.println("Has seleccionado una opción incorrecta.");
        }
    }
public void mostrrtrt(){
        jugador.mostrarp();
}
    public void comprarIngredientes() {
        Scanner scanner = new Scanner(System.in);
        List<Comerciante> cola = listaComerciante.getListaComerciantes();


        boolean salir=false;

        while (!salir){
            Comerciante c = cola.get(0);
            System.out.printf("Tienes %.2f oros.\n",jugador.getOro());
            System.out.println(c.getClass().getName().toUpperCase() + ": " + c.getNombre());
            ArrayList<Integer> tiene=new ArrayList<>();
            for (Map.Entry<Ingrediente, Integer> m : c.getInventario().entrySet()) {

                if (m.getValue() > 0) {
                    System.out.printf("%d. %s Cantidad: %d Precio UD.: %.2f\n",
                            m.getKey().getId(),
                            m.getKey().getNombre(),
                            m.getValue(),
                            m.getKey().getPrecio());
                    tiene.add(m.getKey().getId());
                }
            }
            try{
                System.out.print("Ingrese el ID del ingrediente que desea seleccionar: ");
                int idSeleccionado = scanner.nextInt();

                Ingrediente ingredienteSeleccionado = null;
                if (tiene.contains(idSeleccionado)){
                    for (Map.Entry<Ingrediente, Integer> entry : c.getInventario().entrySet()) {
                        if (entry.getKey().getId() == idSeleccionado) {
                            ingredienteSeleccionado = entry.getKey();
                            break;
                        }
                    }
                }else {
                    System.out.println("El comerciante no tiene este ingrediente.");
                }

                if (ingredienteSeleccionado != null) {
                    System.out.print("Introduce la cantidad: ");
                    int cantidad = scanner.nextInt();
                    int cantidadComerciante=c.getInventario().get(ingredienteSeleccionado);
                    if (cantidadComerciante >= cantidad ){

                        if (jugador.getOro() >= ingredienteSeleccionado.getPrecio()*cantidad){

                            int cantidadJugador= jugador.getMapaIngredientes().get(ingredienteSeleccionado);
                            c.getInventario().put(ingredienteSeleccionado, cantidadComerciante-cantidad);
                            jugador.getMapaIngredientes().put(ingredienteSeleccionado, cantidadJugador+cantidad);
                            jugador.setOro(jugador.getOro()-(ingredienteSeleccionado.getPrecio()*cantidad));

                            Comerciante atras = cola.remove(0);
                            cola.add(atras);

                            System.out.println("Se ha comprado la poción con éxito.");
                        } else {
                            System.out.println("No tienes suficiente oro.");
                        }
                    }else {
                        System.out.println("El comerciante no tiene esta cantidad disponible.");
                    }

                }

            }catch (Exception e){
                System.out.println("Debes escribir un número...");
                scanner.nextLine();
            }
            String s;
            boolean b = false;
            while (!b) {
                System.out.print("¿Quieres continuar?[S/N]...");
                s = scanner.next();

                if (s.equalsIgnoreCase("S")) {
                    b = true;

                } else if (s.equalsIgnoreCase("N")) {
                    b = true;
                    salir = true;
                } else {
                    System.out.print("Selecciona una opción válida. Pulsa INTRO para continuar...");
                    new Scanner(System.in).nextLine();
                }
            }
        }
    }

    public Jugador getJugador() {
        return jugador;
    }

}

