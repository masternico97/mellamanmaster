package es.uam.eps.ads.p3.src;

import java.util.*;

/**
 * Clase Explorador que se desplaza por caminos hacia posadas
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de prácticas: 2213
 */
public class Explorador {
    private String nombre;
    private int energia;
    private Posada posicion;

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

    public boolean recorre(Posada... posadas) {
        boolean error = false;
        for(Posada p: posadas) {
            if(recorre(getPosicion().getCamino(p)) == false) {
                error = true;
            }
        }

        if(error == true) {
            return false;
        }
        return true;
    }

    public boolean puedeRecorrerCamino(Camino camino) {
        if(this.energia > camino.costeReal()) {
            return true;
        }
        return false;
    }

    public boolean puedeAlojarseEn(Posada posada) {
        return true;
    }
}
