package es.uam.ads.p2.figuras;

/**
 * Clase figura con la cual podemos obtener
 * propiedades de distintos tipos de estas
 * @author Nicolás Serrano y Álvaro Sánchez
 * Grupo de prácticas: 2213
 */

public abstract class Figura {

    public String toString() {
        return getClass().getSimpleName() + " [area=" + getArea() + ", perimetro=" + getPerimetro() + "]";
    }

    /**
     * Devuelve el perímetro de la figura
     * @return perímetro de la figura
     */
    abstract double getPerimetro();

    /**
     * Devuelve el área de la figura
     * @return área de la figura
     */
    abstract double getArea();

    /**
    * Devuelve si nuestra figura es mayor
    * a la figura que recibe la funcion.
    * @param figura figura a comparar
    * @return boolean que dice si es mayor o no.
    */
    public boolean esMayor(Figura figura) {
        return getArea() > figura.getArea();
    }

}
