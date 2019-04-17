package es.uam.eps.ads.p5;

import java.util.Comparator;

public class MatrixElementComparator<T> implements Comparator<IMatrixElement<T>> {

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
