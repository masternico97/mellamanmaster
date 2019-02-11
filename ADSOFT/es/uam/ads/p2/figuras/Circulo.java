package es.uam.ads.p2.figuras;

/**
 * Clase círculo que nos permite obtener los
 * datos de un círculo
 * @author Nicolás Serrano y Álvaro Sánchez
 * Grupo de prácticas: 2213
 */

public class Circulo extends Figura {
    private double radio;

    /*
     * Consructor de la clase círculo
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
     * Devuelve el perímetro del circulo
     * @return perímetro del circulo
     */
    public double getPerimetro() {
        return 2 * Math.PI * radio;
    }

    /**
     * Devuelve el área del circulo
     * @return área del circulo
     */
    public double getArea() {
        return Math.PI * radio * radio;
    }
}
