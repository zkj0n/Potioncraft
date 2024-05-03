import java.sql.*;
import java.text.Normalizer;
import java.util.Comparator;
import java.util.List;

public class BD {

    private static final String URL = "jdbc:mysql://localhost/Potioncraft";
    private static final String USER = "root";
    private static final String PASS = "1234";
    private static String quitarTildes(String cadena) {
        String cadenaNormalizada = Normalizer.normalize(cadena, Normalizer.Form.NFD);
        return cadenaNormalizada.replaceAll("[^\\p{ASCII}]", "");
    }
    public static void rellenarListaIngredientes(List<Ingrediente> listaIngredientes){
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String query = "select * from Ingrediente";

            Statement stmt = con.createStatement();
            ResultSet tabla = stmt.executeQuery(query);

            while (tabla.next()) {
                switch (tabla.getString("tipo")) {
                    case "HIERBA" -> {
                        listaIngredientes.add(new Ingrediente(
                                tabla.getInt("identificador"),
                                TipoIngrediente.valueOf(tabla.getString("tipo")),
                                tabla.getString("nombre"),
                                tabla.getString("descripcion"),
                                tabla.getDouble("precioCompra"),
                                Efecto.valueOf(quitarTildes(tabla.getString("efectoPositivo"))),
                                0, 0));
                    }
                    case "SETA" -> {
                        listaIngredientes.add(new Ingrediente(
                                tabla.getInt("identificador"),
                                TipoIngrediente.valueOf(tabla.getString("tipo")),
                                tabla.getString("nombre"),
                                tabla.getString("descripcion"),
                                tabla.getDouble("precioCompra"),
                                Efecto.valueOf(quitarTildes(tabla.getString("efectoNegativo"))),
                                tabla.getInt("nivelToxicidad"), 0));
                    }
                    case "MINERAL" -> {
                        listaIngredientes.add(new Ingrediente(
                                tabla.getInt("identificador"),
                                TipoIngrediente.valueOf(tabla.getString("tipo")),
                                tabla.getString("nombre"),
                                tabla.getString("descripcion"),
                                tabla.getDouble("precioCompra"),
                                Efecto.SIN_EFECTO, 0,
                                tabla.getDouble("dureza")));
                    }
                }
            }
            listaIngredientes.sort(Comparator.comparing(Ingrediente::getId));

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
