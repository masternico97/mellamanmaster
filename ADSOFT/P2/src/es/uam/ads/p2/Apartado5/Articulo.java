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

    /**
     * Consructor de la clase Articulo
     * @param id = id de articulo
     */
     public Articulo(long codigo, int numPiezas) {
         this.codigo = codigo;
         this.numPiezas = numPiezas;

         //Desactivamos el pago hasta que se hagan las pruebas
         this.autorizarPago = false;
     }



}
