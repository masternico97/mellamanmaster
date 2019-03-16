package es.uam.eps.ads.p3;

import java.util.*;
import java.io.*;

/**
 * Clase simulacion que simula la ejecucion del porgrama
 * pasandole unos ficheros que lee y guarda sus datos
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class Simulacion {
    List<Posada> posadas = new ArrayList<>();
    Explorador explorador = null;

    /**
     * Constructor de la clase simulacion
     * @param arg1 ruta del fichero con la informacion de posadas
     * @param arg2 ruta del fichero con la informacion de caminos
     * @param arg3 ruta del fichero con la informacion del explorador
     */
    public Simulacion(String arg1, String arg2, String arg3) throws IOException {
        leerPosadas(arg1);
        leerCaminos(arg2);
        leerExplorador(arg3);
    }
    /**
     * Abre y lee el fichero de posadas
     * @param arg ruta del fichero con la informacion de posadas
     */
    private void leerPosadas(String arg) throws IOException {
        String linea;
        BufferedReader buffer =
            new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(arg)
                )
            );

        while(((linea) = buffer.readLine()) != null) {
            String[] split = linea.split("\\s+");
            Posada reading = new Posada(split[0], Integer.parseInt(split[1]));
            posadas.add(reading);
        }
        buffer.close();
    }

    /**
     * Abre y lee el fichero de caminos
     * @param arg ruta del fichero con la informacion de caminos
     */
    private void leerCaminos(String arg) throws IOException {
        String linea;
        BufferedReader buffer =
            new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(arg)
                )
            );
        while(((linea) = buffer.readLine()) != null) {
            String[] split = linea.split("\\s+");
            Posada origen = buscarPosada(split[0]);
            Posada destino = buscarPosada(split[1]);
            Camino reading = new Camino(origen, destino, Integer.parseInt(split[2]));

            origen.addCamino(reading);
        }
        buffer.close();

    }

    /**
     * Abre y lee el fichero de explorador
     * @param arg ruta del fichero con la informacion de explorador
     */
    private void leerExplorador(String arg) throws IOException {
        String linea;
        BufferedReader buffer =
            new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(arg)
                )
            );
        /*Leemos la primera linea del fichero explorador creandolo*/
        if(((linea) = buffer.readLine()) != null) {
            String[] split = linea.split("\\s+");
            explorador = new Explorador(split[0], Integer.parseInt(split[1]), buscarPosada(split[2]));
        }
        while(((linea) = buffer.readLine()) != null){
            explorador.recorre(buscarPosada(linea));
            System.out.println(explorador);
        }
        buffer.close();
    }

    /**
     * Busca una posada con el nombre que se pasa por argumento
     * Si hay mas de una que se llaman igual se devuelve la primera
     * que se encontro
     * @param busqueda nombre de la posada a buscar
     * @return posada encontrada
     */
    private Posada buscarPosada(String busqueda) {
        for(Posada current: posadas){
            if(current.getNombre().equals(busqueda)){
                return current;
            }
        }
        return null;
    }
}
