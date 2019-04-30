package es.uam.eps.ads.p5.Agent;

import es.uam.eps.ads.p5.Matrix.*;

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
        IMatrixElement<Cell> element = new MatrixElement<>(fila, columna, cell, entorno);
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
