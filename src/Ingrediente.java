import java.util.Objects;

public class Ingrediente {
    private int id;
    private TipoIngrediente tipo;
    private String nombre;
    private String descripcion;
    private double precio;
    private Efecto efecto;
    private int nivelToxicidad;
    private double dureza;

    public Ingrediente(int id, TipoIngrediente tipo, String nombre, String descripcion, double precio, Efecto efecto, int nivelToxicidad, double dureza) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.efecto = efecto;
        this.nivelToxicidad = nivelToxicidad;
        this.dureza = dureza;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", efecto=" + efecto +
                ", nivelToxicidad=" + nivelToxicidad +
                ", dureza=" + dureza +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingrediente that)) return false;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
