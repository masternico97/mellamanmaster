package es.uam.eps.ads.p5;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase Matrix que contiene una lista con los valores no nulos de esta
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class Matrix {

    private LinkedList<IMatrixElement> matrix = new LinkedList<>();
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

    private boolean isLegalPosition(int i, int j) {
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
    }
    public List<IMatrixElement<T>> getNeighboursAt(int i, int j) throws IllegalPositionException;
    public List<IMatrixElement<T>> asList();


}
