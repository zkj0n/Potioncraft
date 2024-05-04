import java.util.*;

public class Jugador {
    private int oro;
    private int reputacion;
    private List<Pocion> listaPociones;
    private Map<Ingrediente, Integer> mapaIngredientes;

    public Jugador() {
        this.oro = 1000;
        this.reputacion=0;
        listaPociones=new ArrayList<>();
        mapaIngredientes=new HashMap<>();
        mapaIngredientes=inicioListaIngredientes();

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
    public void mostrar(){
         for (Map.Entry<Ingrediente, Integer> entrada : mapaIngredientes.entrySet()) {

                System.out.println("--> "+entrada.getKey().getNombre().toUpperCase()+": " + entrada.getKey().getId() + ", CANTIDAD: " + entrada.getValue());


        }
    }
}
