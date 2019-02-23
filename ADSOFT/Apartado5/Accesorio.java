package es.uam.ads.p2.Apartado5;

/**
 * Clase accesorio que se incluye en los
 * carritos
 * @author Nicolás Serrano y Álvaro Sánchez
 * Grupo de prácticas: 2213
 */
public class Accesorio {

    private String marca;
    private String modelo;
    private String color;
    private String tipo;

    public Accesorio(String tipo){
        this.tipo = tipo;
        //¿Como conectar con carrito? El resto de atributos deberian de ser iguales
    }

}
