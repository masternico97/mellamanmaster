package es.uam.eps.ads.p4;

import java.lang.Math;
import java.util.*;

/**
 * Clase SimilitudCoseno para calcular la similitud de dos usuarios a partir de
 * un modelo
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class SimilitudCoseno implements Similitud {

    private ModeloDatos modelo;

    /**
     * Constructor de la clase SimilitudCoseno
     * @param modelo = modelo que usaremos para la similitud
     */
    public SimilitudCoseno(ModeloDatos modelo) {
      this. modelo = modelo;
    }

    /**
     * Metodo que calcula la similitud de u y v
     * @param u = usuario u
     * @param v = usuario v
     */
    public double sim(Long u, Long v){
        double numerador = 0;
        double denomU = 0;
        double denomV = 0;
        double denominador;


        Map<Long, Double> uPref = modelo.getPreferenciasUsuario(u);
        Map<Long, Double> vPref = modelo.getPreferenciasUsuario(v);

        for(Long key : uPref.keySet()) {
            if(vPref.containsKey(key)) {
                numerador += uPref.get(key)*vPref.get(key);
            }
        }

        for(Long key : uPref.keySet()) {
            denomU += Math.pow(uPref.get(key), 2);
        }

        for(Long key : vPref.keySet()) {
            denomV += Math.pow(vPref.get(key), 2);
        }

        denominador = denomU * denomV;
        denominador = Math.sqrt(denominador);

        return numerador/denominador;
    }
}
