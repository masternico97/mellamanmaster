package es.uam.eps.ads.p3;

import java.util.*;

/**
 * Clase Explorador que se desplaza por caminos hacia posadas
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class Explorador {
    private String nombre;
    private int energia;
    private Posada posicion;

    /**
     * Constructor de la clase explorador
     * @param nombre nombre del explorador
     * @param energia energia base del explorador
     * @param posicion posada de inicio
     */
    public Explorador(String nombre, int energia, Posada posicion) {
        this.nombre = nombre;
        this.energia = energia;
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEnergia() {
        return energia;
    }

    public Posada getPosicion(){
        return posicion;
    }

    public void setPosicion(Posada p) {
        posicion = p;
    }

    public void setEnergia(int e) {
        energia = e;
    }

    public String toString() {
        return getNombre()+" (e:"+getEnergia()+") en "+getPosicion().getNombre();
    }

    /**
     * Funcion que hace al explorador recorrer un camino
     * @param camino a recorrer
     * @return booleano indicando si se ha podido recorrer el camino
     */
    public boolean recorre(Camino camino) {

        Posada p;
        Posada posadaAux;

        if(camino == null) {
            return false;
        }
        for(int i = 0; i < getPosicion().getNumCaminos(); i++) {
            if(camino == getPosicion().getCamino(i)) {
                p = camino.getDestino();
                if(puedeRecorrerCamino(camino) && puedeAlojarseEn(p)){
                    posadaAux = this.posicion;
                    this.posicion = p;
                    if(posadaAux != this.posicion) {
                        this.energia -= camino.costeReal();
                        this.energia += p.getRecuperacion();
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Funcion que hace al explorador ir de posada en posada
     * @param posadas a recorrer
     * @return booleano indicando si se ha podido recorrer todas las posadas
     */
    public boolean recorre(Posada... posadas) {
        boolean error = false;
        for(Posada p: posadas) {
            if(!recorre(getPosicion().getCamino(p))) {
                error = true;
            }
        }

        if(error) {
            return false;
        }
        return true;
    }

    /**
     * Funcion que indica al explorador si puede recorrer un camino
     * @param camino a recorrer
     * @return booleano indicando si se puede recorrer
     */
    public boolean puedeRecorrerCamino(Camino camino) {
        if(this.energia > camino.costeReal()) {
            return true;
        }
        return false;
    }

    /**
     * Funcion que indica si un explorador puede alojarse en una posada
     * @param posada a estudiar
     * @return booleano indicando si se puede alojar
     */
    public boolean puedeAlojarseEn(Posada posada) {
        return true;
    }
}
