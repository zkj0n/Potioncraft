import java.util.ArrayList;
import java.util.List;

public class Minero extends Comerciante{

    private List<Ingrediente> listaIngredientes= new ArrayList<>();

    public Minero(int id, String nombre) {
        super(id, nombre);
        BD.rellenarListaIngredientes(listaIngredientes);
        for (Ingrediente i:listaIngredientes) {
            inventario.put(i, 0);
        }
        this.inicio();

    }
    public void inicio(){
        for (int i=0; i<5; i++){
            Ingrediente e= Comerciante.obtenerIngrediente(TipoIngrediente.MINERAL, listaIngredientes);
            if (inventario.get(e) == 0) {
                inventario.put(e,random.nextInt(5,11));
            } else {
                i--;
            }
        }
    }
    public void reabastecer(){
        for (int i=0; i<5; i++){
            Ingrediente e= Comerciante.obtenerIngrediente(TipoIngrediente.MINERAL, listaIngredientes);
            if (inventario.containsKey(e)){
                int valorActual=inventario.get(e);
                int valorSuma=random.nextInt(5,11);
                int nuevoValor=valorActual+valorSuma;
                inventario.put(e,nuevoValor);
            }
        }
    }
}
