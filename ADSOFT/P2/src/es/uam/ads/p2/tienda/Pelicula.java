package es.uam.ads.p2.tienda;

/**
 * Clase pelicula que nos permite obtener los
 * datos de un pelicula
 * @author Nicolas Serrano y Alvaro Sanchez
 * Grupo de practicas: 2213
 */
public class Pelicula extends Articulo{
    private String titulo;
    private String genero;
    private String director;

    /**
     * Constructor de la clase libro
     * @param titulo titulo de la pelicula
     * @param genero genero de la pelicula
     * @param director director de la pelicula
     * @param id identificador de la pelicula
     */
    public Pelicula(long id, String titulo, String genero, String director){
      super(id);
      this.titulo = titulo;
      this.genero = genero;
      this.director = director;
    }

    public String toString() {
       return "["+super.getId()+"] PELICULA: "+titulo+" ("+genero+"). Dir: "+director;
    }
}
