package es.uam.ads.p2.tienda;

/**
 * Clase disco con la cual podemos obtener
 * propiedades de distintos tipos de estas
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class Disco extends Articulo {
    private String titulo;
    private String interprete;
    private int anyo;

    /**
     * Consructor de la clase Disco
     * @param id = id del articulo
     * @param titulo = titulo del disco
     * @param interprete = interprete del disco
     * @param anyo = anyo de publicacion del disco
     */
     public Disco(long id, String interprete, String titulo, int anyo) {
         super(id);
         this.titulo = titulo;
         this.interprete = interprete;
         this.anyo = anyo;
     }

     public String toString() {
        return "["+super.getId()+"] DISCO: "+interprete+". "+titulo+" ("+anyo+")";
    }

}
