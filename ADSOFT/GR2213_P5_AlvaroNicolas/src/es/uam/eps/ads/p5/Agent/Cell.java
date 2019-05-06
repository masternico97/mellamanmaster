package es.uam.eps.ads.p5.Agent;

import es.uam.eps.ads.p5.Matrix.IMatrixElement;
import es.uam.eps.ads.p5.Matrix.IllegalPositionException;
import es.uam.eps.ads.p5.Matrix.MatrixElement;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase Cell, que representa un contenedor de agentes
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class Cell {

    private LinkedList<IBasicAgent> agentes;
    private IMatrixElement<Cell> iMatrixElement;

    /**
     * Constructor de la clase Cell
     */
    public Cell() {
        agentes = new LinkedList<>();
    }

    /**
     * Metodo para anyadir agentes
     *
     * @param agentes agentes a ayadir a nuestra celda
     */
    public void add(IBasicAgent ... agentes) {
        this.agentes.addAll(Arrays.asList(agentes));
        for(IBasicAgent a : this.agentes) {
            a.setCell(this);
        }
    }

    public void iniElement(IMatrixElement<Cell> element) {
        iMatrixElement = element;
    }

    @Override
    public String toString() {
        return String.valueOf(agentes.size());
    }

    /**
     * Metodo para hacer una copia de la celda
     *
     * @return Cell copia de la celda
     */
    public Cell copy() {
    	Cell cell = new Cell();
    	cell.agentes = this.agentes;
    	cell.iMatrixElement = this.iMatrixElement;
    	return cell;
      }

    public LinkedList<IBasicAgent> agents() {
        return agentes;
    }
    
    public LinkedList<IBasicAgent> getElement() {
        return agentes;
    }
    
    public IMatrixElement<Cell> getMatrixElement(){
		return iMatrixElement;
    	
    }
    
    /**
     * Metodo para obtener vecinos
     *
     * @return List<IMatrixElement<Cell>> lista de elementos de la matriz vecinos
     */
    private List<IMatrixElement<Cell>> getNeighbourMatrixElements() throws IllegalPositionException{
    	List<IMatrixElement<Cell>> listElements;
    	IMatrixElement<Cell> auxMatrixElement;
    	Cell auxCell = new Cell();
    	
    	listElements = iMatrixElement.getMatrix().getNeighboursAt(iMatrixElement.getI(), iMatrixElement.getJ());
    	if(listElements.size() == 4) {	
        	return listElements;
    	}
    	
    	//Comprobamos si falta el elemento al sur
    	if(iMatrixElement.getMatrix().isLegalPosition(iMatrixElement.getI()+1, iMatrixElement.getJ())) {
	    	auxMatrixElement = iMatrixElement.getMatrix().getElementAt(iMatrixElement.getI()+1, iMatrixElement.getJ());
	    	if(auxMatrixElement == null) {
	    		auxCell = new Cell();
	    		auxMatrixElement = new MatrixElement<Cell>(iMatrixElement.getI()+1, iMatrixElement.getJ(), auxCell, iMatrixElement.getMatrix());
	    		auxCell.iniElement(auxMatrixElement);
	    		iMatrixElement.getMatrix().addElement(auxMatrixElement);
	    		listElements.add(auxMatrixElement);
	    	}
    	}
    	
    	//Comprobamos si falta el elemento al norte
    	if(iMatrixElement.getMatrix().isLegalPosition(iMatrixElement.getI()-1, iMatrixElement.getJ())) {
	    	auxMatrixElement = iMatrixElement.getMatrix().getElementAt(iMatrixElement.getI()-1, iMatrixElement.getJ());
	    	if(auxMatrixElement == null) {
	    		auxCell = new Cell();
	    		auxMatrixElement = new MatrixElement<Cell>(iMatrixElement.getI()-1, iMatrixElement.getJ(), auxCell, iMatrixElement.getMatrix());
	    		auxCell.iniElement(auxMatrixElement);
	    		iMatrixElement.getMatrix().addElement(auxMatrixElement);
	    		listElements.add(auxMatrixElement);
	    	}
    	}
    	
    	//Comprobamos si falta el elemento al este
    	if(iMatrixElement.getMatrix().isLegalPosition(iMatrixElement.getI(), iMatrixElement.getJ()+1)) {
	    	auxMatrixElement = iMatrixElement.getMatrix().getElementAt(iMatrixElement.getI(), iMatrixElement.getJ()+1);
	    	if(auxMatrixElement == null) {
	    		auxCell = new Cell();
	    		auxMatrixElement = new MatrixElement<Cell>(iMatrixElement.getI(), iMatrixElement.getJ()+1, auxCell, iMatrixElement.getMatrix());
	    		auxCell.iniElement(auxMatrixElement);
	    		iMatrixElement.getMatrix().addElement(auxMatrixElement);
	    		listElements.add(auxMatrixElement);
	    	}
    	}
    	
    	//Comprobamos si falta el elemento al oeste
	    	if(iMatrixElement.getMatrix().isLegalPosition(iMatrixElement.getI(), iMatrixElement.getJ()-1)) {
	    	auxMatrixElement = iMatrixElement.getMatrix().getElementAt(iMatrixElement.getI(), iMatrixElement.getJ()-1);
	    	if(auxMatrixElement == null) {
	    		auxCell = new Cell();
	    		auxMatrixElement = new MatrixElement<Cell>(iMatrixElement.getI(), iMatrixElement.getJ()-1, auxCell, iMatrixElement.getMatrix());
	    		auxCell.iniElement(auxMatrixElement);
	    		iMatrixElement.getMatrix().addElement(auxMatrixElement);
	    		listElements.add(auxMatrixElement);
	    	}
    	}
    	
    	return listElements;
    }

    /**
     * Metodo para obtener celdas vecinas a partir de la funcion anterior
     *
     * @return List<Cell> lista de celdas vecinas
     */
    public List<Cell> neighbours() {
        List<Cell> cells = new LinkedList<>();
        try {
            List<IMatrixElement<Cell>> listElements = getNeighbourMatrixElements();

            for(IMatrixElement<Cell> element : listElements) {
                cells.add(element.getElement());
            }
           
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        } 
        return cells;
    }

    
}
