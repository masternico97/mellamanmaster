package es.uam.eps.ads.p5.Matrix;

import java.util.Comparator;

/**
 * Clase del MatrixElementComparator<T> con el proposito de comparar dos IMatrixElement<T>
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class MatrixElementComparator<T> implements Comparator<IMatrixElement<T>> {

    /**
     * Metodo para comparar dos IMatrixElement<T>
     * @param e1 = elemento 1 a comparar
     * @param e2 = elemento 2 a comparar
     * @return int = resultado de la comparacion
     */
    @Override
    public int compare(IMatrixElement<T> e1, IMatrixElement<T> e2) {
        if(e1 == e2) {
            return 0;
        }
        if(e1 == null) {
            return 1;
        }
        if(e2 == null) {
            return -1;
        }
        return e1.compareTo(e2);
    }
}
