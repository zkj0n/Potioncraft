import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListaComerciante {
    private List<Comerciante> listaComerciantes= new ArrayList<>();


    public void cargaInicial() {
        final String archivo="comerciantes.csv";
        String line;
        final String separador=";";
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            while ((line = br.readLine()) != null) {

                String[] datos = line.split(separador);

                switch (datos[0]){
                    case "HERBORISTA" -> listaComerciantes.add(new Herborista(Integer.parseInt(datos[1]),datos[2]));
                    case "RECOLECTOR SETAS" -> listaComerciantes.add(new Recolector(Integer.parseInt(datos[1]),datos[2]));
                    case "MINERO" -> listaComerciantes.add(new Minero(Integer.parseInt(datos[1]),datos[2]));
                    case "MERCADER" -> listaComerciantes.add(new Mercader(Integer.parseInt(datos[1]),datos[2]));
                }

            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public List<Comerciante> getListaComerciantes() {
        return listaComerciantes;
    }

    public void imprimir(){
        for (Comerciante c: listaComerciantes){
            c.mostrar();
        }
    }
}
