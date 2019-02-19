package es.uam.ads.p2.figuras;

/**
 * Clase figura con la cual podemos obtener
 * propiedades de distintos tipos de estas
 * @author Nicolas Serrano y Alvaro Sanchez
 * Grupo de practicas: 2213
 */
public abstract class Figura {

    public String toString() {
        return getClass().getSimpleName() + " [area=" + getArea() + ", perimetro=" + getPerimetro() + "]";
    }

    /**
     * Devuelve el perimetro de la figura
     * @return perimetro de la figura
     */
    abstract double getPerimetro();


    /**
     * Devuelve el area de la figura
     * @return area de la figura
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
