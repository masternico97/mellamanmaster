package es.uam.ads.p2.Apartado5;

/**
 * Clase artículo con la cual podemos obtener
 * propiedades de distintos tipos de estas
 * @author Nicolás Serrano y Álvaro Sánchez
 * Grupo de prácticas: 2213
 */

public abstract class Articulo {
    private long codigo;
    private boolean autorizarPago;
    private int numPiezas;
    private boolean errorPintura;

    /**
     * Consructor de la clase Articulo
     * @param id = id de articulo
     */
     public Articulo(long codigo, int numPiezas, boolean errorPintura) {
         this.codigo = codigo;
         this.numPiezas = numPiezas;
         this.errorPintura = errorPintura;

         //Desactivamos el pago hasta que se hagan las pruebas
         this.autorizarPago = false;
     }

     /**
      * Indica si el articulo tiene problemas
      * de pintura
      * @param articulo articulo a comprobar
      * @return si tiene problemas de pintura
      * devuelve true, en caso opuesto, devuelve
      * false
      */
     public boolean comprobarPintura(Articulo articulo) {
         return carrito.errorPintura;
     }


}
