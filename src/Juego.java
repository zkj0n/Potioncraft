import java.util.ArrayList;
import java.util.List;

public class Juego {
    private ListaComerciante listaComerciante= new ListaComerciante();
    private List<Ingrediente> listaIngredientes;

    public Juego() {
        this.listaIngredientes=new ArrayList<>();
        this.listaComerciante.cargaInicial();
        BD.rellenarListaIngredientes(listaIngredientes);
    }

    public ListaComerciante getListaComerciante() {
        return listaComerciante;
    }

    public List<Ingrediente> getListaIngredientes() {
        return listaIngredientes;
    }
}
