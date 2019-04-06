package es.uam.eps.ads.p4;

import java.util.*;
import java.io.*;

/**
 * Interfaz modelo de datos con los metodos correspondientes a ModeloDatosRecomendacion
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class ModeloDatosRecomendacion implements ModeloDatos {
    private Map<Long, Map<Long, Double>> preferenciasAllUsuarios = new TreeMap<>(); //Id usuario y mapa preferenciasItem
    private Map<Long, Map<Long, Double>> preferenciasAllItems = new TreeMap<>(); //Id item y mapa preferenciasUsuario

    /**
     * Constructor de la clase ModeloDatosRecomendacion
     * @param ruta = ruta del fichero a leer
     */
    public ModeloDatosRecomendacion(String ruta) {
        leeFicheroPreferencias(ruta);
    }

    /**
     * Lee un archivo y guarda las preferencias de usuarios e items
     * @param ruta = ruta del fichero a leer
     */
    public void leeFicheroPreferencias(String ruta) {
        final int rightColumn = 2;
        final int centerColumn = 1;
        final int leftColumn = 0;

        try {
            String linea;
            Long idu; //Id usuario
            Long idi; //Id item
            double rate;

            BufferedReader buffer =
                new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(ruta)
                    )
                );

            while(((linea) = buffer.readLine()) != null) {

                String[] split = linea.split("\\s+");

                rate = Double.parseDouble(split[rightColumn]);
                idu = Long.parseLong(split[leftColumn]);
                idi = Long.parseLong(split[centerColumn]);
                if(rate < 1) {
                    rate = 1;
                }
                else if(rate > 5) {
                    rate = 5;
                }
                //Escribe los valores de preferencia de los usuarios
                if(!preferenciasAllUsuarios.containsKey(idu)) {
                    preferenciasAllUsuarios.put(idu, new TreeMap<Long, Double>());
                }
                preferenciasAllUsuarios.get(idu).put(idi, rate);

                //Escribe los valores de preferencia sobre los items
                if(!preferenciasAllItems.containsKey(idi)) {
                    preferenciasAllItems.put(idi, new TreeMap<Long, Double>());
                }
                preferenciasAllItems.get(idi).put(idu, rate);
            }
            buffer.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Obtiene las preferencias de un usuario
     * @param usuario = usuario el cual buscaremos su informacion
     * @return Map = mapa con las preferencias de un usuario
     */
    public Map<Long, Double> getPreferenciasUsuario(Long usuario) {
        return  Collections.unmodifiableMap(preferenciasAllUsuarios.get(usuario));
    }

    /**
     * Obtiene las preferencias de usuarios sobre un item
     * @param item = item el cual buscaremos su informacion
     * @return Map = mapa con las preferencias sobre un item
     */
    public Map<Long, Double> getPreferenciasItem(Long item) {
        return  Collections.unmodifiableMap(preferenciasAllItems.get(item));
    }

    /**
     * Obtiene los usuarios del sistema
     * @return Set = set con todos los usuarios del sistema
     */
    public Set<Long> getUsuariosUnicos() {
        return Collections.unmodifiableSet(preferenciasAllUsuarios.keySet());
    }

    /**
     * Obtiene los items del sistema
     * @return Set = set con todos los items del sistema
     */
    public Set<Long> getItemsUnicos() {
        return Collections.unmodifiableSet(preferenciasAllItems.keySet());
    }
}
