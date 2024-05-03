import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class Comerciante {
    protected int id;
    protected String nombre;
    protected static final Random random=new Random();
    protected Map<Ingrediente,Integer> inventario=new HashMap<>();

    public Comerciante(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public static Ingrediente obtenerIngrediente(TipoIngrediente tipoIngrediente, List<Ingrediente> listaIngredientes){
        if (tipoIngrediente==TipoIngrediente.HIERBA){
            return listaIngredientes.get(random.nextInt(1,26));
        } else if (tipoIngrediente == TipoIngrediente.SETA) {
            return listaIngredientes.get(random.nextInt(27,39));
        } else if (tipoIngrediente==TipoIngrediente.MINERAL) {
            return listaIngredientes.get(random.nextInt(40,48));
        } else if (tipoIngrediente == null) {
            return listaIngredientes.get(random.nextInt(1,48));
        }else {
            return null;
        }
    }
    public String getNombre() {
        return nombre;
    }
    public void mostrar(){
        System.out.println("\n"+this.getClass().getName().toUpperCase()+": "+this.getNombre());
        for (Map.Entry<Ingrediente, Integer> entrada : inventario.entrySet()) {
            if (!entrada.getValue().equals(0)){
                System.out.println("--> "+entrada.getKey().getNombre().toUpperCase()+": " + entrada.getKey().getId() + ", CANTIDAD: " + entrada.getValue());

            }
        }
    }
}
