package es.uam.ads.p2.Apartado5;

import java.util.*;

/**
 * Clase silla que nos permite obtener los
 * datos del articulo silla
 * @author {@link "mailto:nicolas.serranos@estudiante.uam.es" "Nicolas Serrano"}
 * @author {@link "mailto:alvaro.sanchezromero@estudiante.uam.es" "Alvaro Sanchez"}
 * Grupo de practicas: 2213
 */
public class Silla extends Articulo {
    private static final int MAX_PESO = 25

    private String marca;
    private String modelo;
    private String color;
    private boolean devolucion;
    private int minPeso;
    private int maxPeso;
    private boolean isofix;
    private String comentarios;
    private List<Registro> registros = new ArrayList<>();


    /**
     * Constructor de la clase Carrito
     * @param codigo = codigo del producto
     * @param marca = marca del Carrito
     * @param modelo = modelo del Carrito
     * @param color = color del Carrito
     * @param numPiezas = numero de piezas del articulo
     */
    public Carrito(long codigo, String marca, String modelo, String color, int numPiezas) {
        Random random = new Random();

        maxPeso = random.nextInt(MAX_PESO);
        minPeso = random.nextInt(maxPeso);
        isofix = random.nextBoolean();
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        devolucion = !(comprobarAnclaje() && comprobarAcolchado());
        super(codigo, numPiezas);
        super.setAutorizarPago(!devolucion);
    }

    public void setDevolucion(boolean bool) {
        devolucion = bool;
        super.setAutorizarPago(!devolucion);
    }

    public void setComentarios(String comentario) {
        comentarios = comentario;
    }

    /**
     * Crea un registro de repacion
     * @param actuacion = actuacion realizada
     */
    public void setRegistro(String actuacion) {
        registros.add(new Registro(actuacion));
    }

    /**
     * Indica si la silla tiene problemas
     * de anclaje
     * @return si tiene problemas de anclaje
     * devuelve false, en caso opuesto, devuelve
     * true
     */
    public boolean comprobarAnclaje() {
        Random random = new Random();
        return random.nextBoolean();
    }

    /**
     * Indica si la silla tiene problemas
     * de acolchado
     * @return si tiene problemas de acolchado
     * devuelve false, en caso opuesto, devuelve
     * true
     */
    public boolean comprobarAcolchado() {
        Random random = new Random();
        return random.nextBoolean();
    }
 }
