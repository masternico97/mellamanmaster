package es.uam.eps.ads.p4;

import java.lang.Math;
import java.util.*;

public class SimilitudCoseno implements Similitud {

    private ModeloDatosRecomendacion modelo;

    public SimilitudCoseno(ModeloDatosRecomendacion modelo) {
      this. modelo = modelo;
    }

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
