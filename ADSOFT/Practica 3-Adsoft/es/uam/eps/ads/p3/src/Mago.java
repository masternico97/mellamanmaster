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
     * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
     * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
     * Grupo de practicas: 2213
     */
    public enum TipoDeMago {
      HADA,
      HECHICERO;
    }

    int poder;
    TipoDeMago tipo;

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
