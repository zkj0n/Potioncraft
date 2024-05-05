import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Juego {
    private ListaComerciante listaComerciante= new ListaComerciante();
    private List<Ingrediente> listaIngredientes;
    private List<Pocion> listaPociones;
    private Jugador jugador;

    public Juego() {
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
            lineas.add(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+" - INICIO REABASTECIMIENTO COMERCIANTE");
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

    public List<Ingrediente> getListaIngredientes() {
        return listaIngredientes;
    }

    public void mostrarPociones(){
        Set<String> impresas=new HashSet<>();
        for (Pocion p: this.listaPociones){

            if (!impresas.contains(p.getNombre())){
                System.out.println("- "+p.getNombre().toUpperCase());
                impresas.add(p.getNombre());
            }

           for (Map.Entry<Ingrediente, Integer> entrada: p.getIngredientes().entrySet()){
               System.out.println("\t--> NOMBRE: " + entrada.getKey().getNombre()+ " | UNIDADES: "+entrada.getValue());
           }

        }
    }

    public Jugador getJugador() {
        return jugador;
    }

    public List<Pocion> getListaPociones() {
        return listaPociones;
    }
}
