package es.uam.eps.ads.p5;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BasicSimulator {

    private IMatrix entorno;

    public BasicSimulator(int numFilas, int numColumnas) {
        entorno = new Matrix(numFilas, numColumnas);
    }

    public void create(IBasicAgent agent, int numAgentes, int fila, int columna) {
        IBasicAgent aux;
        Cell cell = new Cell();
        for(int i = 0; i < numAgentes; i++) {
            aux = agent.copy();
            cell.add(aux);
        }
        IMatrixElement<Cell> element = new MatrixElement<>(fila, columna, cell);
        try {
            entorno.addElement(element);
        }
        catch(IllegalPositionException e) {
            e.printStackTrace();
        }
    }

    public void run(int pasos) {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "++++++++++++++++++++++++++++++++++++++++++++++\n" +
               "Time = 0\n"+entorno.toString();
    }
}
