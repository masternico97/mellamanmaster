package es.uam.ads.p2.Apartado5;

import java.util.*;

/**
 * Clase cuna que nos permite obtener los
 * datos del articulo cuna
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class Cuna extends Articulo {
    private boolean chatarra;

    /**
     * Constructor de la clase Carrito
     * @param codigo = codigo del articulo
     * @param numPiezas = numero de piezas del articulo
     */
    public Cuna(long codigo, int numPiezas) {
        super(codigo, numPiezas);
        chatarra = !(comprobarPlegable() && super.tienePiezas());
        super.setAutorizarPago(!chatarra);
    }

    /**
     * Comprueba si puede plegarse la cuna
     * @return boolean que viene dado por
     * un valor random
     */
    public boolean comprobarPlegable() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public String toString() {
        return getClass().getSimpleName()+", Codigo:"+super.getCodigo()+", Chatarra: "+chatarra;
    }
}
