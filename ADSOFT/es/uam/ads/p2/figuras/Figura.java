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

    abstract double getPerimetro();

    abstract double getArea();

    /**
    *
    *
    * @return boolean que dice si es mayor o no.
    */
    public boolean esMayor(Figura figura) {
        return getArea() > figura.getArea();
    }

 }
