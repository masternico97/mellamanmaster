package es.uam.eps.ads.p3;

import java.util.*;
import java.lang.Math;

/**
 * Clase Trampa que hereda camino y que anyade funcionalidades
 * especiales a este ultimo.
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class Trampa extends Camino {
    private float factorCoste;
    private double probRetorno;

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
    public Trampa(Posada origen, Posada destino, int coste, float factorCoste, double probRetorno) {
        super(origen, destino, coste);
        this.factorCoste = factorCoste;
        if(probRetorno > 0 && probRetorno <= 1){
            this.probRetorno = probRetorno;
        }
        else {
            this.probRetorno = 0;
        }
    }

    public float getFactorCoste() {
        return factorCoste;
    }

    public double getProbRetorno() {
        return probRetorno;
    }

    /**
     * Funcion que devuelve el origen o el destino del
     * camino dependiendo de si se cae o no en la trampa
     * @return coste adicional
     */
    @Override
    public Posada getDestino() {
        if(Math.random() <= probRetorno) {
            return super.getOrigen();
        }
        return super.getDestino();
    }

    /**
     * Funcion que devuelve el coste adicional del camino
     * @return coste adicional
     */
    @Override
    public int costeEspecial() {
        return Math.round(super.getCoste()*factorCoste);
    }

    /**
     * Funcion que devuelve si el camino es una trampa
     */
    @Override
    public boolean esTrampa() {
        return true;
    }

    @Override
    public String toString() {
        return "(Ruta: "+super.getOrigen().getNombre()+"--"+getCoste()+"-->"+super.getDestino().getNombre()+". Factor coste: "+getFactorCoste()+", Probabilidad retorno: "+probRetorno+")";
    }
}
