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

    public String toString() {
        return getNombre()+" (e:"+getEnergia()+") en "+getPosicion().getNombre();
    }

    public boolean recorre(Camino camino) {

        Posada posadaAux;

        if(camino == null) {
            return false;
        }

        for(int i = 0; i < getPosicion().getNumCaminos(); i++) {
            if(camino == getPosicion().getCamino(i)) {
                if(puedeRecorrerCamino(camino) && puedeAlojarseEn(camino.getDestino())){
                  posadaAux = this.posicion;
                  this.posicion = camino.getDestino();
                  if(posadaAux != this.posicion) {
                      this.energia -= camino.costeReal();
                      this.energia += camino.getDestino().getRecuperacion();
                  }
                  return true;
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

    private boolean puedeRecorrerCamino(Camino camino) {
        if(this.energia > camino.costeReal()) {
            return true;
        }
        return false;
    }

    public boolean puedeAlojarseEn(Posada posada) {
        return true;
    }
}
