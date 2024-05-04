import java.sql.*;
import java.text.Normalizer;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    public static void rellenarListaPociones(List<Pocion> lP, List<Ingrediente> lI){
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String query = "select p.nombre, p.identificador, ip.idIngrediente, ip.unidades\n" +
                    "from ingrediente i inner join ingredientes_pociones ip on i.identificador=ip.idIngrediente\n" +
                    "inner join pocion p on p.identificador=ip.idPocion;";

            Statement stmt = con.createStatement();
            ResultSet tabla = stmt.executeQuery(query);


            while (tabla.next()) {

                Map<Ingrediente, Integer> aux=new LinkedHashMap<>();

                for (Ingrediente i: lI){
                    if (tabla.getInt("idIngrediente")==i.getId()){
                        aux.put(i,tabla.getInt("unidades"));
                    }
                }

                lP.add(new Pocion(tabla.getInt("identificador"),
                        tabla.getString("nombre"),
                        aux));
            }




        }catch (SQLException e){
            System.out.println("[ERROR]: Conexión fallida...");
        }
    }
}
