package es.uam.ads.p2.figuras;

/**
 * Clase circulo que nos permite obtener los
 * datos de un circulo
 * @author Nicolas Serrano y Alvaro Sanchez
 * Grupo de prácticas: 2213
 */
public class Circulo extends Figura {
    private double radio;

    /**
     * Consructor de la clase circulo
     * @param rad = radio seleccionado para el circulo
     */
    public Circulo(double rad) {
        radio = rad;
    }

    /**
     * Devuelve el radio
     * @return radio del circulo
     */
    public double getRadio() {
        return radio;
    }

    /**
     * Devuelve el perimetro del circulo
     * @return perimetro del circulo
     */
    public double getPerimetro() {
        return 2 * Math.PI * radio;
    }

    /**
     * Devuelve el area del circulo
     * @return area del circulo
     */
    public double getArea() {
        return Math.PI * radio * radio;
    }
}
