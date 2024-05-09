import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public abstract class Comerciante implements Serializable {
    protected int id;
    protected String nombre;
    protected List<Ingrediente> listaIngredientes= new ArrayList<>();
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

    public Map<Ingrediente, Integer> getInventario() {
        return inventario;
    }

    public void mostrar(){
        System.out.println("\n"+this.getClass().getName().toUpperCase()+": "+this.getNombre());
        for (Map.Entry<Ingrediente, Integer> entrada : inventario.entrySet()) {
            if (!entrada.getValue().equals(0)){
                System.out.println("--> "+entrada.getKey().getNombre().toUpperCase()+": " + entrada.getKey().getId() + ", CANTIDAD: " + entrada.getValue());

            }
        }
    }
    protected void reabastecer(TipoIngrediente tp){


        Path rutaLog= Paths.get("abastecimientos.log");
        List<String> lineas=new ArrayList<>();
        try{
            lineas.add("COMERCIANTE: "+this.getNombre());
        }catch (Exception e){
            System.out.println("Ocurrió un error al escribir un archivo: " + e.getMessage());
        }

        for (int i=0; i<5; i++){
            Ingrediente e= Comerciante.obtenerIngrediente(tp, listaIngredientes);
            if (inventario.containsKey(e)){
                int valorActual=inventario.get(e);
                int valorSuma=random.nextInt(5,11);
                int nuevoValor=valorActual+valorSuma;
                if (e!=null){
                    lineas.add("\t--> "+e.getNombre()+". Cantidad Previa: "+valorActual+". Cantidad actual: "+nuevoValor);
                }
                inventario.put(e,nuevoValor);
            }
        }
        lineas.add("");
        try {

            Files.write(rutaLog, lineas, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.out.println("No se ha podido escribir el archivo: "+ex.getMessage());
        }

    }
}
