package es.uam.eps.ads.p3;

import java.io.*;

/**
 * Clase EjemploDeUsoSimulacion por el cual se prueba la clase Simulacion
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class EjemploDeUsoSimulacion {
    public static void main(String[] args) {
        try {
            Simulacion s;

            s = new Simulacion("../txt/POSADAS.txt", "../txt/CAMINOS.txt", "../txt/EXPLORADOR.txt");
            s = new Simulacion("../txt/POSADAS.txt", "../txt/CAMINOS.txt", "../txt/EXPLORADOR2.txt");
        } catch(IOException e) { System.out.println ("Error en archivos"); }
    }
}
