package es.uam.ads.p2.Apartado5;

import java.util.*;

/**
 * Clase carrito que nos permite obtener los
 * datos del articulo carrito
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class Carrito extends Articulo {
    private static final int MAX_ACCESORIOS = 3;

    private String marca;
    private String modelo;
    private String color;
    private boolean devolucion;
    private Accesorio [] accesorios = new Accesorio[MAX_ACCESORIOS];
    private List<Registro> registros = new ArrayList<>();


    /**
     * Constructor de la clase Carrito
     * @param codigo = codigo del articulo
     * @param marca = marca del Carrito
     * @param modelo = modelo del Carrito
     * @param color = color del Carrito
     * @param numPiezas = numero de piezas del articulo
     */
    public Carrito(long codigo, String marca, String modelo, String color, int numPiezas) {
        super(codigo, numPiezas);
        Random random = new Random();

        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.devolucion = !(comprobarAnclaje() && comprobarAcolchado() && comprobarEnganche());
        // Si el producto se devuelve no se autoriza el pago
        super.setAutorizarPago(!this.devolucion);

        //Crea un numero alaetorio de accesorios
        for(int i = 0; i < random.nextInt(MAX_ACCESORIOS); i++) {
            this.accesorios[i] = new Accesorio(this.marca, this.modelo, this.color, "accesorio");
        }
    }

    public void setDevolucion(boolean bool) {
        devolucion = bool;
        super.setAutorizarPago(!devolucion);
    }

    /**
     * Crea un registro de repacion
     * @param actuacion = actuacion realizada
     */
    public void setRegistro(String actuacion) {
        registros.add(new Registro(actuacion));
    }

    /**
     * Indica si el carro tiene problemas
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
     * Indica si el carro tiene problemas
     * de acolchado
     * @return si tiene problemas de acolchado
     * devuelve false, en caso opuesto, devuelve
     * true
     */
    public boolean comprobarAcolchado() {
        Random random = new Random();
        return random.nextBoolean();
    }

    /**
     * Indica si el carro o sus accesorios
     * tiene problemas de enganche
     * @return si tiene problemas de enganche
     * devuelve falso, en caso opuesto, devuelve
     * true
     */
    public boolean comprobarEnganche() {
        Random random = new Random();

        //Si alguno de los accesorios falla se retorna el error
        for(Accesorio accesorio : accesorios){
            if(!random.nextBoolean()){
                return false;
            }
        }
        return random.nextBoolean();
    }

    public String toString() {
        return getClass().getSimpleName()+", Codigo:"+super.getCodigo()+", Marca: "+marca+", Modelo: "+modelo+", Color: "+color;
    }
 }
