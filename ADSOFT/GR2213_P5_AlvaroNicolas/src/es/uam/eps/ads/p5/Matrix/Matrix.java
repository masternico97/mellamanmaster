package es.uam.eps.ads.p5.Matrix;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase Matrix que contiene una lista con los valores no nulos de esta
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class Matrix<T> implements IMatrix<T> {

    private List<IMatrixElement<T>> matrix = new LinkedList<>();
    private int numColumnas;
    private int numFilas;


    /**
     * Constructor de la clase Matrix que nos crea una matriz vacia
     * @param numFilas = numero de filas de la matriz
     * @param numColumnas = numero de columnas de la matriz
     */
    public Matrix(int numFilas, int numColumnas) {
        this.numColumnas = numColumnas;
        this.numFilas = numFilas;
    }

    /**
     * Constructor de la clase Matrix que nos crea una matriz con elementos
     * @param numFilas = numero de filas de la matriz
     * @param numColumnas = numero de columnas de la matriz
     * @param elementos ... = elementos que se van añadiendo en orden a la matriz
     */
    public Matrix(int numFilas, int numColumnas, IMatrixElement<T> ... elementos) {
        this(numColumnas, numFilas);
        for(IMatrixElement<T> e: elementos) {
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

    /**
     * Metodo que comprueba si la posicion es legal
     * @param i = fila i
     * @param j = columna j
     * @return boolean = true, si es una posicion legal y false, si no lo es
     */
    public boolean isLegalPosition(int i, int j) {
        if(i >= getRows() || j >= getCols()) {
            return false;
        }
        return true;
    }

    /**
     * Metodo para anyadir un elemento a la matriz, reemplazando el que este en esa
     * posicion si existe
     * @param element = elemento a anyadir
     */
    public void addElement(IMatrixElement<T> element) throws IllegalPositionException {
        if(!isLegalPosition(element.getI(), element.getJ())) {
            throw new IllegalPositionException(element.getI(), element.getJ());
        }

        for(IMatrixElement<T> e : matrix) {
            if(e.getI() == element.getI() && e.getJ() == element.getJ()) {
                e.setElement(element.getElement());
                return;
            }
        }
        matrix.add(element);
    }

    /**
     * Metodo para conseguir un elemento de la matriz
     * @param i = fila i
     * @param j = columna j
     * @return IMatrixElement<T>  = elemento de esa posicion, null si no existe
     */
    public IMatrixElement<T> getElementAt(int i, int j) throws IllegalPositionException {
        if(!isLegalPosition(i, j)) {
            throw new IllegalPositionException(i, j);
        }

        for(IMatrixElement<T> e : matrix) {
            if(e.getI() == i && e.getJ() == j) {
                return e;
            }
        }
        return null;
    }

    /**
     * Metodo para obtener los vecinos del elemento en la posicion(i,j)
     * @param i = fila i
     * @param j = columna j
     * @return List<IMatrixElement<T>> = lista de elementos vecinos del elemento de la posicion(i,j)
     */
    public List<IMatrixElement<T>> getNeighboursAt(int i, int j) throws IllegalPositionException {
        List<IMatrixElement<T>> listaVecinos = new LinkedList<>();
        IMatrixElement<T> elementoAux;

        if(!isLegalPosition(i, j)) {
            throw new IllegalPositionException(i, j);
        }

        //Vecino del norte
	    if(isLegalPosition(i-1, j)) {
	        elementoAux = getElementAt(i-1, j);
	        if(elementoAux != null) {
	            listaVecinos.add(elementoAux);
	        }
        }

        //Vecino del sur
	    if(isLegalPosition(i+1, j)) {
	        elementoAux = getElementAt(i+1, j);
	        if(elementoAux != null) {
	            listaVecinos.add(elementoAux);
	        }
	    }
	    
        //Vecino del oeste
	    if(isLegalPosition(i, j-1)) {
	        elementoAux = getElementAt(i, j-1);
	        if(elementoAux != null) {
	            listaVecinos.add(elementoAux);
	        }
	    }
	    
        //Vecino del este
	    if(isLegalPosition(i, j+1)) {
	        elementoAux = getElementAt(i, j+1);
	        if(elementoAux != null) {
	            listaVecinos.add(elementoAux);
	        }
	    }

        return listaVecinos;

    }

    /**
     * Metodo que devuelve nuestra matriz como lista (en nuestro caso ya lo era originalmente)
     * @return List<IMatrixElement<T>> = devuelve lista
     */
    public List<IMatrixElement<T>> asList() {
        return matrix;
    }

    @Override
    public String toString(){
        try {
            String salida = "";
            for(int i = 0; i < numFilas; i++) {
                for(int j = 0; j < numColumnas; j++) {
                    salida = salida.concat("\t");	//Insertamos una tabulacion
                    IMatrixElement<T> currentElement = getElementAt(i, j);
                    if(currentElement == null) {
                        salida = salida.concat("0|");	//Si la casilla esta vacia se imprime un 0
                    } else {
                        salida = salida.concat(currentElement.toString()+"|");
                    }
                }
                salida = salida.concat("\n");
            }
            return salida;
        } catch (IllegalPositionException e) {
        	System.out.println(e);
            return null;
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (obj==this) return true;
        if (!(obj instanceof Matrix)) return false;

        Matrix<T> comparing = (Matrix<T>)obj;

        if(getRows() == comparing.getRows() && getCols() == comparing.getCols()) {
        	for(int i = 0; i < getRows(); i++) {
                for(int j = 0; j < getCols(); j++) {
                    try {
                        if(!getElementAt(i, j).equals(comparing.getElementAt(i, j))) {
                            return false;
                        }
                    }
                    catch (IllegalPositionException e) {
                        System.out.println(e);
                        return false;
                    }
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hashValue = 11;
        hashValue = 31*hashValue+getRows();
        hashValue = 31*hashValue+getCols();
        hashValue = 31*hashValue+matrix.hashCode();
        return hashValue;
    }

    /**
     * Metodo para ordenar la list de la matriz con un comparator
     * @param c = comparator que usamos como criterio para ordenar la lista
     * @return List<IMatrixElement<T>> = devolvemos una lista inmutable ordenada
     */
    public List<IMatrixElement<T>> asListSortedBy(Comparator<IMatrixElement<T>> c) {
        Collections.sort(matrix, c);
        return Collections.unmodifiableList(matrix);
    }


}