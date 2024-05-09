import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class Pocion implements Serializable {
    private int id;
    private String nombre;
    private Map<Ingrediente, Integer> ingredientes;

    public Pocion(int id, String nombre, Map<Ingrediente, Integer> ingredientes) {
        this.id = id;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public Map<Ingrediente, Integer> getIngredientes() {
        return ingredientes;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pocion pocion)) return false;
        return id == pocion.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
