package es.uam.eps.ads.p3;

import java.util.*;

/**
 * Clase Mago que se trata de un explorador con
 * propiedades especiales
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class Mago extends Explorador {

    /**
     * Enumeración TipoDeMago con los distintos
     * tipos de mago existentes
     */
    public enum TipoDeMago {
      HADA,
      HECHICERO;
    }

    private int poder;
    private TipoDeMago tipo;

    /**
     * Constructor de la clase explorador
     * @param nombre nombre del explorador
     * @param energia energia base del explorador
     * @param posicion posada de inicio
     * @param poder poder del mago
     * @param tipo tipo de mago
     */
    public Mago(String nombre, int energia, Posada posicion, int poder, TipoDeMago tipo){
        super(nombre, energia, posicion);
        this.poder = poder;
        this.tipo = tipo;
    }

    /**
     * Funcion que indica si un mago puede alojarse en una posada
     * @param posada a estudiar
     * @return booleano indicando si se puede alojar
     */
    @Override
    public boolean puedeAlojarseEn(Posada posada) {
        if(tipo == TipoDeMago.HADA) {
            if(posada.getLuz().ordinal() > 3){
                return true;
            }
        } else {    //Es hechicero
            if(posada.getLuz().ordinal() <= 2 + poder) {
                return true;
            }
        }
        return false;
    }

    /**
     * Funcion que hace al explorador recorrer un camino
     * @param camino a recorrer
     * @return booleano indicando si se ha podido recorrer el camino
     */
    @Override
    public boolean recorre(Camino camino) {

        Posada p;
        Posada posadaAux;

        if(camino == null) {
            return false;
        }

        for(int i = 0; i < getPosicion().getNumCaminos(); i++) {
            if(camino == getPosicion().getCamino(i) ) {
                p = camino.getDestino();
                if(puedeRecorrerCamino(camino) && puedeAlojarseEn(p) && !camino.esTrampa()){
                    posadaAux = getPosicion();
                    setPosicion(p);
                    if(posadaAux != getPosicion()) {
                        setEnergia(getEnergia() - camino.costeReal());
                        setEnergia(getEnergia() + p.getRecuperacion());
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public TipoDeMago getTipo(){
        return tipo;
    }

    public int getPoder() {
        return poder;
    }

    @Override
    public String toString() {
        return getNombre()+" ("+getTipo()+" con energia = "+getEnergia()+" y poder = "+getPoder()+") en "+getPosicion().getNombre();
    }
}
