package es.uam.ads.p2.Apartado5;

/**
 * Clase artículo con la cual podemos obtener
 * propiedades de distintos tipos de estas
 * @author Nicolás Serrano y Álvaro Sánchez
 * Grupo de prácticas: 2213
 */
public abstract class Articulo {
    private long codigo;
    private boolean autorizarPago;
    private int numPiezas;
    private boolean errorPintura;

    /**
     * Consructor de la clase Articulo
     * @param id = id de articulo
     */
    public Articulo(long codigo, int numPiezas) {
        this.codigo = codigo;
        this.numPiezas = numPiezas;

        //Desactivamos el pago hasta que se hagan las pruebas
        this.autorizarPago = false;
    }

    //Getter functions

    public long getCodigo(Articulo articulo) {
        return articulo.codigo;
    }

    public boolean getAutorizarPago(Articulo articulo) {
        return articulo.autorizarPago;
    }

    public int getNumPiezas(Articulo articulo) {
        return articulo.numPiezas;
    }

    public boolean comprobarPintura(Articulo articulo){
        return articulo.errorPintura;
    }

    //Setter functions

    public void setCodigo(Articulo articulo, long codigo) {
        articulo.codigo = codigo;
    }

    public void getAutorizarPago(Articulo articulo, boolean autorizarPago) {
        articulo.autorizarPago = autorizarPago;
    }

    public void getNumPiezas(Articulo articulo, int numPiezas) {
        articulo.numPiezas = numPiezas;
    }


    public boolean tienePiezas(Articulo articulo){
        //Consideramos 4 como mínimo número de piezas
        if(numPiezas < 4) {
            return false;
        }
        return true;
    }


}
