package es.uam.eps.ads.p5;

import java.util.Comparator;
import java.util.List;

/**
 * Interfaz IMatrix que contiene los metodos de Matrix
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public interface IMatrix<T> {
    int getCols();
    int getRows();
    boolean isLegalPosition(int i, int j);
    void addElement(IMatrixElement<T> element) throws IllegalPositionException;
    IMatrixElement<T> getElementAt(int i, int j) throws IllegalPositionException;
    List<IMatrixElement<T>> getNeighboursAt(int i, int j) throws IllegalPositionException;
    List<IMatrixElement<T>> asList();
    List<IMatrixElement<T>> asListSortedBy(Comparator<IMatrixElement<T>> c);
}
