package es.uam.eps.ads.p5;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase Matrix que contiene una lista con los valores no nulos de esta
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class Matrix<T> implements IMatrix<T>, Comparable<Matrix<T>>, Comparator<Matrix<T>> {

    private List<IMatrixElement<T>> matrix = new LinkedList<>();
    private int numColumnas;
    private int numFilas;


    /**
     * Constructor de la clase Matrix que nos crea una matriz vacía
     * @param numColumnas = numero de columnas de la matriz
     * @param numFilas = numero de filas de la matriz
     */
    public Matrix(int numColumnas, int numFilas) {
        this.numColumnas = numColumnas;
        this.numFilas = numFilas;
    }

    /**
     * Constructor de la clase Matrix que nos crea una matriz con elementos
     * @param numColumnas = numero de columnas de la matriz
     * @param numFilas = numero de filas de la matriz
     */
    public Matrix(int numColumnas, int numFilas, IMatrixElement ... elementos) {
        this(numColumnas, numFilas);
        for(IMatrixElement e: elementos) {
            try {
                addElement(e);
            }catch(IllegalPositionException exception){
                exception = new IllegalPositionException();
            }
        }
    }

    //Getters
    public int getCols() {
        return numColumnas;
    }
    public int getRows() {
        return numFilas;
    }

    public boolean isLegalPosition(int i, int j) {
        if(i >= getRows() || j >= getCols()) {
            return false;
        }
        return true;
    }

    public void addElement(IMatrixElement<T> element) throws IllegalPositionException {
        if(isLegalPosition(element.getI(), element.getJ())) {
            throw new IllegalPositionException(element.getI(), element.getJ());
        }

        for(IMatrixElement e : matrix) {
            if(e.equals(element)) {
                e.setElement(element);
                return;
            }
        }
        matrix.add(element);
    }

    public IMatrixElement<T> getElementAt(int i, int j) throws IllegalPositionException {
        if(isLegalPosition(i, j)) {
            throw new IllegalPositionException(i, j);
        }

        for(IMatrixElement e : matrix) {
            if(e.getI() == i && e.getJ() == j) {
                return e;
            }
        }
        return null;
    }

    public List<IMatrixElement<T>> getNeighboursAt(int i, int j) throws IllegalPositionException {
        List<IMatrixElement<T>> listaVecinos = new LinkedList<>();
        IMatrixElement<T> elementoAux;

        if(isLegalPosition(i, j)) {
            throw new IllegalPositionException(i, j);
        }

        //Vecino del norte
        elementoAux = getElementAt(i-1, j);
        if(elementoAux != null) {
            listaVecinos.add(elementoAux);
        }

        //Vecino del sur
        elementoAux = getElementAt(i+1, j);
        if(elementoAux != null) {
            listaVecinos.add(elementoAux);
        }

        //Vecino del oeste
        elementoAux = getElementAt(i, j-1);
        if(elementoAux != null) {
            listaVecinos.add(elementoAux);
        }

        //Vecino del este
        elementoAux = getElementAt(i, j+1);
        if(elementoAux != null) {
            listaVecinos.add(elementoAux);
        }

        return listaVecinos;

    }
    public List<IMatrixElement<T>> asList() {
        return matrix;
    }

    @Override
    public int compareTo(Matrix<T> compMatrix) {
        if(getRows() == compMatrix.getRows() && getCols() == compMatrix.getCols()) {
            int ret = 0;
            for(int i = 0; i < getRows(); i++) {
                for(int j = 0; j < getCols(); j++) {
                    try {
                        if(!getElementAt(i, j).equals(compMatrix.getElementAt(i, j))) {
                           ret++;
                        }
                    }
                    catch (IllegalPositionException e) {
                        System.out.println(e);
                        ret++;
                    }
                }
            }
            return ret;
        }
        else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==this) return true;
        if (!(obj instanceof Matrix)) return false;
        return (this.compareTo((Matrix) obj) == 0);
    }

    public int compare(Matrix<T> matrix1, Matrix<T> matrix2) {

    }

    public List<IMatrixElement<T>> asListSortedBy(Comparator<IMatrixElement<T>> c) {
    }

}
