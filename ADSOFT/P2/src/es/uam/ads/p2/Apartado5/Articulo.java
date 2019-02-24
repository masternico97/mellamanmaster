package es.uam.ads.p2.Apartado5;

import java.util.*;

/**
 * Clase articulo con la cual podemos obtener
 * propiedades de distintos tipos de estas
 * @author {@link "mailto:nicolas.serranos@estudiante.uam.es" "Nicolas Serrano"}
 * @author {@link "mailto:alvaro.sanchezromero@estudiante.uam.es" "Alvaro Sanchez"}
 * Grupo de practicas: 2213
 */
public abstract class Articulo {
    private long codigo;
    private int numPiezas;
    private boolean outlet;
    private boolean autorizarPago;

    /**
     * Constructor de la clase Articulo
     * @param codigo = id de articulo
     * @param numPiezas = numero de piezas del articulo
     */
    public Articulo(long codigo, int numPiezas) {
        this.codigo = codigo;
        this.numPiezas = numPiezas;
        /* Si las comprobaciones nos devuelven true significa
         * Que el articulo es correcto, por eso de niega
         * !(true AND true) = false
         */
        outlet = !(comprobarPintura() && comprobarTapiceria());

        /*Desactivamos el pago hasta que se hagan las pruebas*/
        autorizarPago = false;
    }

    public String toString() {
        return "Articulo: "+codigo+" de tipo "+getClass().getSimpleName();
    }

    public void setAutorizarPago(boolean bool) {
        autorizarPago = bool;
    }

    public boolean tienePiezas() {
        //Consideramos 4 como minimo numero de piezas
        if(numPiezas < 4) {
            return false;
        }
        return true;
    }

    public boolean comprobarPintura() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public boolean comprobarTapiceria() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
