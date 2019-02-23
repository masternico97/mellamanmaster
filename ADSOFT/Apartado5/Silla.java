package es.uam.ads.p2.Apartado5;

/**
 * Clase silla que nos permite obtener los
 * datos del artículo silla
 * @author Nicolás Serrano y Álvaro Sánchez
 * Grupo de prácticas: 2213
 */
public class Silla extends Articulo {
    private String marca;
    private String modelo;
    private String color;
    private boolean devolucion;
    private boolean errorAnclaje;
    private boolean errorAcolchado;
    private int minPeso;
    private int maxPeso;
    private boolean isofix;
    private String comentarios;


    /**
     * Consructor de la clase Carrito
     * @param codigo = codigo del producto
     * @param marca = marca del Carrito
     * @param modelo = modelo del Carrito
     * @param color = color del Carrito
     * @param devolucion = indica si se ha de devolver el articulo
     */
    public Carrito(long codigo, String marca, String modelo, String color, int numPiezas, boolean errorAncleaje, boolean errorAcolchado) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.devolucion = false;
        this.errorAnclaje = errorAnclaje;
        this.errorAcolchado = errorAcolchado;
        super(codigo, numPiezas);
    }

    /**
     * Indica si el silla tiene problemas
     * de anclaje
     * @param silla articulo a comprobar
     * @return si tiene problemas de anclaje
     * devuelve true, en caso opuesto, devuelve
     * false
     */
    public boolean comprobarAnclaje(Silla silla) {
        return carrito.errorAnclaje;
    }

    /**
     * Indica si el silla tiene problemas
     * de acolchado
     * @param silla articulo a comprobar
     * @return si tiene problemas de acolchado
     * devuelve true, en caso opuesto, devuelve
     * false
     */
    public boolean comprobarAcolchado(Silla silla) {
        return carrito.errorAcolchado;
    }
 }
