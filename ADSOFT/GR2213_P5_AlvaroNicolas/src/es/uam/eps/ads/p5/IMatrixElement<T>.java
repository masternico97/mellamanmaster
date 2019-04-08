package es.uam.eps.ads.p5;

/**
 * Interfaz del elemento de la matriz generico de la practica 
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
 public interface IMatrixElement<T> {
     int getI();
     int getJ();
     T getElement();
     void setElement(T element);
     String toString();
     boolean equals(Object obj);
 }
