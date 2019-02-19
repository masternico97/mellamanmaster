package es.uam.ads.p2.tienda;

/**
 * Clase articulo con la cual podemos obtener
 * propiedades de distintos tipos de estas
 * @author Nicolas Serrano y Alvaro Sanchez
 * Grupo de practicas: 2213
 */
public abstract class Articulo {
    private long id;
    private int stock;

    /**
     * Constructor de la clase Articulo
     * @param id = identificador del articulo
     */
     public Articulo(long id) {
         this.id = id;
         stock = 1;
     }

     public long getId(){
       return id;
     }
}
