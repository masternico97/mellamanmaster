package es.uam.ads.p2.figuras;

/**
* Clase rectangulo que nos permite obtener los
* datos de un rectangulo
* @author Nicolas Serrano y Alvaro Sanchez
* Grupo de practicas: 2213
*/
public class Rectangulo extends Figura {
    private double base;
    private double altura;

    /**
     * Constructor con la base y altura del rectangulo
     * @param a = altura 
     * @param b = base
     */
    public Rectangulo(double a, double b) {
        altura = a;
        base = b;
    }

    /**
    * Devuelve la base del rectangulo
    * @return base del rectangulo
    */
    public double getBase() {
        return base;
    }

    /**
    * Devuelve la altura del rectangulo
    * @return altura del rectangulo
    */
    public double getAltura() {
        return altura;
    }

    /**
    * Calcula el perimetro del rectangulo
    * @return perimetro del rectangulo
    */
    public double getPerimetro() {
        return 2 * altura + 2 * base;
    }

    /**
    * Calcula el area del rectangulo
    * @return area del rectangulo
    */
    public double getArea() {
        return base * altura;
    }

    /**
    * Comprueba si el rectangulo es un cuadrado
    * @return boolean que dice si es cuadrado o no.
    */
    public boolean isCuadrado() {
        return base == altura;
    }
}
