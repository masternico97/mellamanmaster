package es.uam.eps.ads.p3;

import java.util.*;

/**
 *
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de prácticas: 2213
 */
public class Simulacion throws IOException {

    public Simulacion(String arg1, String arg2, String arg3) {
        List<Posada> posadas = new ArrayList<>();
        Explorador explorador = null;
        /*Leemos el fichero de posadas*/
        BufferedReader buffer =
            new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(arg1)
                )
            );

        while(((String linea) = buffer.readLine()) != null) {
            String[] split = read.split("\\s+");
            Posada reading = new Posada(split[0], split[1]);
            posadas.add(reading);
        }
        buffer.close();

        /*Leemos el fichero de caminos*/
        buffer =
            new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(arg2)
                )
            );
        while(((String linea) = buffer.readLine()) != null) {
            String[] split = read.split("\\s+");
            Posada origen = buscarPosada(split[0]);
            Posada destino = buscarPosada(split[1]);
            Camino reading = new Camino(origen, destino, split[2]);

            origen.addCamino(reading);
        }
        buffer.close();

        /*Leemos el fichero de explorador*/
        buffer =
            new BufferedReader(
                new InputStreamReader(
                    new FileInputStream("arg3")
                )
            );
        /*Leemos la primera linea del fichero explorador creandolo*/
        if(((String linea) = buffer.readLine()) != null) {
            String[] split = read.split("\\s+");
            explorador = new Explorador(split[0], split[1], buscarPosada(split[3]));
        }
        while(((String linea) = buffer.readLine()) != null){
            recorre(buscarPosada(linea));
        }
    }


    public Posada buscarPosada(String busqueda){
        for(Posada busqueda: posadas){
            if(busqueda.getNombre().equals(split[0])){
                return busqueda;
            }
        }
    }
}
