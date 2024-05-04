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

    public ListaComerciante getListaComerciante() {
        return listaComerciante;
    }

    public List<Ingrediente> getListaIngredientes() {
        return listaIngredientes;
    }

    public void mostrar(){
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
