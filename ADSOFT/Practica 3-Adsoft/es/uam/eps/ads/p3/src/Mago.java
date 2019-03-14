package es.uam.eps.ads.p3.src;

import java.util.*;

/**
 * Clase Mago que se trata de un explorador con
 * propiedades especiales
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de prácticas: 2213
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

    public Mago(String nombre, int energia, Posada posicion, int poder, TipoDeMago tipo){
        super(nombre, energia, posicion);
        this.poder = poder;
        this.tipo = tipo;
    }

    /**
     *
     */
    @Override
    public boolean puedeAlojarseEn(Posada posada) {
      System.out.println(posada.getLuz().ordinal());

        if(tipo == TipoDeMago.HADA) {
            if(posada.getLuz().ordinal() > 3){
                return true;
            }
        }
        else {
            if(posada.getLuz().ordinal() <= 2 + poder) {
                return true;
            }
        }
        return false;
    }

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
