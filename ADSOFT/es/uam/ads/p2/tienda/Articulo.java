package es.uam.ads.p2.tienda;

/**
 * Clase artículo con la cual podemos obtener
 * propiedades de distintos tipos de estas
 * @author Nicolás Serrano y Álvaro Sánchez
 * Grupo de prácticas: 2213
 */

public abstract class Articulo {
    private long id;
    private int stock;

    /**
     * Consructor de la clase Articulo
     * @param id = id de articulo
     */
     public Articulo(long id) {
         this.id = id;
         stock = 1;
     }
}
