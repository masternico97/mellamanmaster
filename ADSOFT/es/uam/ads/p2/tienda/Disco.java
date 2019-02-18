package es.uam.ads.p2.tienda;

/**
 * Clase disco con la cual podemos obtener
 * propiedades de distintos tipos de estas
 * @author Nicolás Serrano y Álvaro Sánchez
 * Grupo de prácticas: 2213
 */

public class Disco extends Articulo {
    private string titulo;
    private string interprete;
    private int anyo;

    /**
     * Consructor de la clase Disco
     * @param id = id del articulo
     * @param titulo = titulo del disco
     * @param interprete = interprete del disco
     * @param anyo = año de publicacion del disco
     */
     public Disco(long id, string titulo, string interprete, int anyo) {
         super(id);
         this.titulo = titulo;
         this.interprete = interprete;
         this.anyo = anyo;
     }
}
