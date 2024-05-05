public class Herborista extends Comerciante{


    public Herborista(int id, String nombre) {
        super(id, nombre);
        BD.rellenarListaIngredientes(listaIngredientes);
        for (Ingrediente i:listaIngredientes) {
            inventario.put(i, 0);
        }
        this.inicio();
    }
    public void inicio(){
        for (int i=0; i<5; i++){
        Ingrediente e= Comerciante.obtenerIngrediente(TipoIngrediente.HIERBA, listaIngredientes);
            if (inventario.get(e) == 0) {
                inventario.put(e,random.nextInt(5,11));
            } else {
                i--;
            }
        }
    }
    public void reabastecer(){
        reabastecer(TipoIngrediente.HIERBA);
    }

}

