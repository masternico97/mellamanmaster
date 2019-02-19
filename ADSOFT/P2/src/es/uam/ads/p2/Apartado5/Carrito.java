package es.uam.ads.p2.Apartado5;

/**
 * Clase carrito que nos permite obtener los
 * datos del artículo carrito
 * @author Nicolás Serrano y Álvaro Sánchez
 * Grupo de prácticas: 2213
 */
 public class Carrito extends Articulo{
    private String marca;
    private String modelo;
    private String color;
    private boolean devolucion;
    private boolean outlet;


    /**
     * Consructor de la clase Carrito
     * @param codigo = codigo del producto
     * @param marca = marca del Carrito
     * @param modelo = modelo del Carrito
     * @param color = color del Carrito
     * @param devolucion = indica si se ha de devolver el articulo
     * @param numPiezas = numero de numPiezas
     */
    public Carrito(long codigo, String marca, String modelo, String color, boolean devolucion, int numPiezas){
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.devolucion = false;
        this.outlet = false;
        super(codigo, numPiezas);
    }

    /**
     * Indica si el carro tiene problemas
     * de anclaje
     * @param carrito articulo a comprobar
     * @return si tiene problemas de anclaje
     * devuelve true, en caso opuesto, devuelve
     * false
     */
    public boolean comprobarAnclaje(Carrito carrito) {
        return carrito.errorAnclaje;
    }

    /**
     * Indica si el carro tiene problemas
     * de acolchado
     * @param carrito articulo a comprobar
     * @return si tiene problemas de acolchado
     * devuelve true, en caso opuesto, devuelve
     * false
     */
    public boolean comprobarAcolchado(Carrito carrito) {
        return carrito.errorAcolchado;
    }
 }
