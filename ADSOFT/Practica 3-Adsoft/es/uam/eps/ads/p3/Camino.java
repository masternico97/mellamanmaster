package es.uam.eps.ads.p3;

import java.util.*;

/**
 * Clase Camino que nos sirve para conectar posadas
 * y por la cual pueden desplazarse exploradores
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de prácticas: 2213
 */
public class Camino {
    private Posada origen;
    private Posada destino;
    private int coste;
    private static int costeTotal = 0;

    /**
     * Constructor de la clase camino
     * @param origen posada de origem
     * @param destino posada de destino
     * @param coste entero con el coste de
     * energia necesario para recorrer el camino
     */
    public Camino(Posada origen, Posada destino, int coste) {
        this.origen = origen;
        this.destino = destino;
        if(coste <= 0) {
            this.coste = 1;
        }
        else {
            this.coste = coste;
        }
        this.costeTotal += coste;
    }

    public Posada getOrigen(){
        return origen;
    }

    public Posada getDestino(){
        return destino;
    }

    public int getCoste(){
        return coste;
    }

    /**
     * Funcion que modifica el destino y coste de un
     * camino
     * @param destino nueva posada de destino
     * @param coste nuevo entero con el coste de
     * energia necesario para recorrer el camino
     */
    public void cambiarDestino(Posada destino, int coste) {
        this.destino = destino;
        this.costeTotal -= this.coste;
        this.costeTotal += coste;
        this.coste = coste;
    }

    public String toString() {
        return "("+origen.getNombre()+"--"+getCoste()+"-->"+destino.getNombre()+")";
    }

    int costeEspecial() {
        return 0;
    }

    int costeReal() {
        return coste;
    }

    boolean esTrampa() {
        return false;
    }
}
