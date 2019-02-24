package es.uam.ads.p2.Apartado5;

import java.util.*;

/**
 * Clase cuna que nos permite obtener los
 * datos del articulo cuna
 * @author {@link "mailto:nicolas.serranos@estudiante.uam.es" "Nicolas Serrano"}
 * @author {@link "mailto:alvaro.sanchezromero@estudiante.uam.es" "Alvaro Sanchez"}
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
        chatarra = !(comprobarPlegable() && super.tienePiezas());
        super(codigo, numPiezas);
        super.setAutorizarPago(!chatarra);
    }

    public boolean comprobarPlegable() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
