package es.uam.ads.p2.Apartado5;

import java.util.*;

/**
 * Clase articulo con la cual podemos obtener
 * propiedades de distintos tipos de estas
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
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

    /**
     * Autoriza el pago de un articulo
     * @param bool indica si se activa el pago (true)
     * o viceversa (false)
     */
    public void setAutorizarPago(boolean bool) {
        autorizarPago = bool;
    }

    /**
     * Indica si tiene piezas suficientes para la venta
     * @return boolean que indica si tiene piezas suficientes
     */
    public boolean tienePiezas() {
        //Consideramos 4 como minimo numero de piezas
        if(numPiezas < 4) {
            return false;
        }
        return true;
    }

    /**
     * Comprueba si la pintura está bien
     * @return boolean que viene dado por
     * un valor random
     */
    public boolean comprobarPintura() {
        Random random = new Random();
        return random.nextBoolean();
    }

    /**
     * Comprueba si la tapiceria está bien
     * @return boolean que viene dado por
     * un valor random
     */
    public boolean comprobarTapiceria() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public long getCodigo() {
        return this.codigo;
    }
}
