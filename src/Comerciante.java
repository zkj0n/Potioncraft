import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class Comerciante {
    protected int id;
    protected String nombre;
    protected static final Random random=new Random();
    protected Map<Ingrediente,Integer> inventario;

    public Comerciante(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.inventario=new LinkedHashMap<>();
    }
    public static Ingrediente obtenerIngrediente(TipoIngrediente tipoIngrediente, List<Ingrediente> listaIngredientes){
        if (tipoIngrediente==TipoIngrediente.HIERBA){
            boolean salir=false;
            Ingrediente i=null;
            while (!salir){
               i=listaIngredientes.get(random.nextInt(1,listaIngredientes.size()));
                if (i.getTipo()==TipoIngrediente.HIERBA){
                    salir=true;
                }
            }
            return i;
        } else if (tipoIngrediente == TipoIngrediente.SETA) {
            boolean salir=false;
            Ingrediente i=null;
            while (!salir){
                i=listaIngredientes.get(random.nextInt(1,listaIngredientes.size()));
                if (i.getTipo()==TipoIngrediente.SETA){
                    salir=true;
                }
            }
            return i;
        } else if (tipoIngrediente==TipoIngrediente.MINERAL) {
            boolean salir=false;
            Ingrediente i=null;
            while (!salir){
                i=listaIngredientes.get(random.nextInt(1,listaIngredientes.size()));
                if (i.getTipo()==TipoIngrediente.MINERAL){
                    salir=true;
                }
            }
            return i;
        } else if (tipoIngrediente == null) {
            return listaIngredientes.get(random.nextInt(1,listaIngredientes.size()));
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
