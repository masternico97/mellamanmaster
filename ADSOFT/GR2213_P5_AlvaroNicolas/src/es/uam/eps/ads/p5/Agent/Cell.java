package es.uam.eps.ads.p5.Agent;

import es.uam.eps.ads.p5.Matrix.IMatrixElement;
import es.uam.eps.ads.p5.Matrix.IllegalPositionException;
import es.uam.eps.ads.p5.Matrix.MatrixElement;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Cell {

    private LinkedList<IBasicAgent> agentes;
    private IMatrixElement<Cell> iMatrixElement;

    public Cell() {
        agentes = new LinkedList<>();
    }

    public Cell(IBasicAgent ... agentes) {
        super();
        add(agentes);
    }

    public void add(IBasicAgent ... agentes) {
        this.agentes.addAll(Arrays.asList(agentes));
        for(IBasicAgent a : this.agentes) {
            a.setCell(this);
        }
    }

    public void iniElement(IMatrixElement element) {
        iMatrixElement = element;
    }

    @Override
    public String toString() {
        return String.valueOf(agentes.size());
    }

    public Cell copy() {
        try {
            return (Cell)this.clone();
        }
        catch(CloneNotSupportedException e) {
            System.out.println("No se puede duplicar.");
        }
        return null;
    }

    public LinkedList<IBasicAgent> agents() {
        return agentes;
    }

    public List<Cell> neighbours() {
        List<Cell> cells = new LinkedList<>();
        try {
            List<IMatrixElement> listElements = new LinkedList<>();
            listElements.add((MatrixElement)iMatrixElement.getMatrix().getNeighboursAt(iMatrixElement.getI(), iMatrixElement.getJ()));
            for(IMatrixElement el : listElements) {
                cells.add((Cell) el.getElement());
            }
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        return cells;
    }

    public LinkedList<IBasicAgent> getElement() {
        return agentes;
    }
}
