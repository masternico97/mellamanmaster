package es.uam.eps.ads.p3;

import java.util.*;
import java.lang.Math;

/**
 * Clase Trampa que hereda camino y que anyade funcionalidades
 * especiales a este ultimo.
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de prácticas: 2213
 */
public class Trampa extends to Camino {
    private float factorCoste;
    private float probRetorno;

    /**
     * Constructor de la clase trampa
     * @param origen posada de origem
     * @param destino posada de destino
     * @param coste entero con el coste de
     * energia necesario para recorrer el camino
     * @param factorCoste factor por el cual se multiplicara el
     * coste del camino trampa
     * @param probRetorno probabilidad (0,1] de que el camino no
     * te desplace
     */
    public Trampa(Posada origen, Posada destino, int coste, float factorCoste, float probRetorno) {
        super(origen, destino, coste);
        this.factorCoste = factorCoste;
        this.probRetorno = probRetorno;
    }

    public float getFactorCoste() {
        return factorCoste;
    }

    public float getProbRetorno() {
        return probRetorno;
    }

    public Posada getDestino() {
        if(Math.random() <= probRetorno) {
            return origen;
        }
        return destino;
    }

    public int costeEspecial() {
        return Math.round(coste*factorCoste);
    }
}
