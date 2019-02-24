package es.uam.ads.p2.Apartado5;

/**
 * Clase venta donde se reciben y entregan
 * productos, realizan comprobaciones
 * @author {@link "mailto:nicolas.serranos@estudiante.uam.es" "Nicolas Serrano"}
 * @author {@link "mailto:alvaro.sanchezromero@estudiante.uam.es" "Alvaro Sanchez"}
 * Grupo de practicas: 2213
 */

public class Venta {
    private List<Articulo> articulos = new ArrayList<>();

    public void autorizarPago(Articulo articulo){
        articulo.setAutorizarPago(true);
    }

    public void mandarArreglarC(Carrito carrito){
        carrito.setRegistro("Arreglando");
        carrito.setDevolucion(false);
    }

    public void mandarArreglarS(Silla silla){
        silla.setRegistro("Arreglando");
        silla.setDevolucion(false);
    }

    public void anadirProducto(Articulo articulo){
        articulos.add(articulo);
    }

    public void mostrarInventario(){
        for(Articulo a: articulos){
            System.out.println(a);
        }
    }
}
