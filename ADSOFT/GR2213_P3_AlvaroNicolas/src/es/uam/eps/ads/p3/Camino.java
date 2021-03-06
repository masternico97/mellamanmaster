package es.uam.eps.ads.p3;

import java.util.*;

/**
 * Clase Camino que nos sirve para conectar posadas
 * y por la cual pueden desplazarse exploradores
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
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

    public Posada getDestino() {
        return destino;
    }

    public Posada getDestinoReal() {
        return destino;
    }

    public int getCoste() {
        return coste;
    }

    public static int getCosteTotal() {
        return costeTotal;
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

    /**
     * Funcion que devuelve el coste adicional del camino
     * @return coste adicional
     */
    public int costeEspecial() {
        return 0;
    }

    /**
     * Funcion que devuelve el coste real del camino
     * @return coste real
     */
    public int costeReal() {
        return coste + costeEspecial();
    }

    /**
     * Funcion que devuelve si el camino es una trampa
     */
    public boolean esTrampa() {
        return false;
    }
}
