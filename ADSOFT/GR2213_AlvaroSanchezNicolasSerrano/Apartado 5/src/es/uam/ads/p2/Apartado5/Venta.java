package es.uam.ads.p2.Apartado5;

import java.util.*;

/**
 * Clase venta donde se reciben y entregan
 * productos, realizan comprobaciones
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */

public class Venta {
    private List<Articulo> articulos = new ArrayList<Articulo>();

    /**
     * Autoriza el pago de un articulo
     * @param articulo articulo a permitir
     */
    public void autorizarPago(Articulo articulo){
        articulo.setAutorizarPago(true);
    }

    /**
     * Elimina el articulo del inventario
     * @param articulo articulo a eliminar
     */
    public void venderArticulo(Articulo articulo){
        articulos.remove(articulo);
    }

    /**
     * Manda a arreglar un carrito
     * @param carrito carrito a arreglar
     */
    public void mandarArreglarC(Carrito carrito){
        carrito.setRegistro("Arreglando");
        carrito.setDevolucion(false);
    }

    /**
     * Manda a arreglar un silla
     * @param silla silla a arreglar
     */
    public void mandarArreglarS(Silla silla){
        silla.setRegistro("Arreglando");
        silla.setDevolucion(false);
    }

    /**
     * Anyade un articulo al inventario
     * @param articulo articulo a anyadir
     */
    public void anadirProducto(Articulo articulo){
        articulos.add(articulo);
    }

    /**
     * Imprime el inventario
     */
    public void mostrarInventario(){
        for(Articulo a: articulos){
            System.out.println(a);
        }
    }
}
