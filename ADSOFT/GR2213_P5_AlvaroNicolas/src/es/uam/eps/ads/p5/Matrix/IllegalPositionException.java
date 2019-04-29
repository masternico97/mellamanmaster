package es.uam.eps.ads.p5.Matrix;

/**
 * Excepcion IllegalPositionException creada para reaccionar a posiciones no validas
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class IllegalPositionException extends Exception {

    /**
     * Constructor de IllegalPositionException
     * @param i = fila i
     * @param j = columna j
     */
    public IllegalPositionException(int i, int j) {
        System.out.println("Indice fuera de la posicion maxima (i = "+i+", j = "+j+")");
    }

    /**
     * Constructor de IllegalPositionException
     */
    public IllegalPositionException() {
        System.out.println("Indice fuera de la posicion maxima");
    }

    @Override
    public void printStackTrace() {
        System.out.println("Indice fuera de la posicion maxima");
    }
}
