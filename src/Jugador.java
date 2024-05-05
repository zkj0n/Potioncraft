import java.util.*;

public class Jugador {
    private int oro;
    private int reputacion;
    private Map<Ingrediente, Integer> mapaIngredientes;
    private Map<Pocion, Integer> mapaPociones;

    public Jugador() {
        this.oro = 1000;
        this.reputacion=0;
        this.mapaIngredientes=inicioListaIngredientes();
        this.mapaPociones=inicioListaPociones();


    }

    public int getReputacion() {
        return reputacion;
    }

    public void restarReputacion(){
        if (this.reputacion>0){
            this.reputacion--;
        }
    }

    public Map<Ingrediente, Integer> inicioListaIngredientes(){
        List<Ingrediente> l=new ArrayList<>();
        Map<Ingrediente, Integer> m=new LinkedHashMap<>();
        BD.rellenarListaIngredientes(l);
        for (Ingrediente i:l) {
            m.put(i, 0);
        }
        return m;
    }
    public Map<Pocion, Integer> inicioListaPociones(){
        List<Ingrediente> lI=new ArrayList<>();
        List<Pocion> lP=new ArrayList<>();
        Map<Pocion, Integer> m=new HashMap<>();
        BD.rellenarListaIngredientes(lI);
        BD.rellenarListaPociones(lP, lI);
        for (Pocion p: lP){
            m.put(p, 0);
        }
        return m;
    }
    public void mostrarEstadisticas(){
        System.out.println("Saldo: "+ this.oro);
        System.out.println("Reputación: "+ this.reputacion);
        System.out.println("\n::Ingredientes:: ");
         for (Map.Entry<Ingrediente, Integer> entrada : this.mapaIngredientes.entrySet()) {
                System.out.println("--> "+entrada.getKey().getNombre().toUpperCase() + ": " + entrada.getValue());
        }
         System.out.println("\n::Pociones::");
         for(Map.Entry<Pocion, Integer> entrada : this.mapaPociones.entrySet()){
             System.out.println("--> "+entrada.getKey().getNombre().toUpperCase() + ": " + entrada.getValue());
         }
    }

}

