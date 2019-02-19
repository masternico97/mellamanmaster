package es.uam.ads.p2.tienda;

/**
 * Clase libro que nos permite obtener los
 * datos de un libro
 * @author Nicolas Serrano y Alvaro Sanchez
 * Grupo de practicas: 2213
 */
public class Libro extends Articulo{
    private String titulo;
    private String autor;
    private String editorial;

    /**
     * Constructor de la clase libro
     * @param titulo titulo del libro
     * @param autor autor del libro
     * @param editorial editorial del libro
     * @param id identificador del libro
     */
    public Libro(long id, String titulo, String autor, String editorial){
      super(id);
      this.titulo = titulo;
      this.autor = autor;
      this.editorial = editorial;
    }

    public String toString() {
       return "["+super.getId()+"] LIBRO: "+titulo+". "+autor+". "+editorial;
    }
}
