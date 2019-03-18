package es.uam.eps.ads.p3;

import java.util.*;

public class ModeloDatosRecomendacion implements ModeloDatos {
    Map<Long, Map<Long, Double>> preferenciasAllUsuarios = new TreeMap<>(); //Id usuario y mapa preferenciasItem
    Map<Long, Map<Long, Double>> preferenciasAllItems = new TreeMap<>(); //Id item y mapa preferenciasUsuario

    public ModeloDatosRecomendacion(String ruta) {
        leeFicheroPreferencias(ruta);
    }

    public void leeFicheroPreferencias(String ruta) {
        String linea;
        BufferedReader buffer =
            new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(ruta)
                )
            );

        while(((linea) = buffer.readLine()) != null) {
            double rate;

            String[] split = linea.split("\\s+");
            rate = Double.parseDouble(split[2]);
            if(rate < 1) {
                rate = 1;
            }
            else if(rate > 5) {
                rate = 5;
            }
            //Escribe los valores de preferencia de los usuarios
            if(!preferenciasAllUsuarios.containsKey(Long.parseLong(split[0]))) {
                preferenciasAllUsuarios.put(Long.parseLong(split[0]), new TreeMap<Long, Double>().put(Long.parseLong(split[1]), rate));
            } else {
                preferenciasAllUsuarios.get(Long.parseLong(split[0])).put(Long.parseLong(split[1]), rate));
            }

            //Escribe los valores de preferencia sobre los items
            if(!preferenciasAllItems.containsKey(Long.parseLong(split[1]))) {
                preferenciasAllItems.put(Long.parseLong(split[1]), new TreeMap<Long, Double>().put(Long.parseLong(split[0]), rate));
            } else {
                preferenciasAllItems.get(Long.parseLong(split[1])).put(Long.parseLong(split[0]), rate));
            }
        }
        buffer.close();
    }

    public Map<Long, Double> getPreferenciasUsuario(Long usuario) {
        return  Collections.unmodifiableMap(preferenciasAllUsuarios.get(usuario));
    }

    public Map<Long, Double> getPreferenciasItem(Long item) {
        return  Collections.unmodifiableMap(preferenciasAllItems.get(item));
    }

    public Set<Long> getUsuariosUnicos() {
        return Collections.unmodifiableSet(preferenciasAllUsuarios.keySet());
    }

    public Set<Long> getItemsUnicos() {
        return Collections.unmodifiableSet(peferenciasAllItems.keySet());
    }
}
