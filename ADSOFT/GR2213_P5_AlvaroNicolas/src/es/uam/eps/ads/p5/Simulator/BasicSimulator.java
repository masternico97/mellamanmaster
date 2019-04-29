package es.uam.eps.ads.p5.Simulator;

import es.uam.eps.ads.p5.Agent.Cell;
import es.uam.eps.ads.p5.Agent.IBasicAgent;
import es.uam.eps.ads.p5.Matrix.*;

public class BasicSimulator extends GeneralSimulator {

    public BasicSimulator(int numFilas, int numColumnas) {
        super(numFilas, numColumnas);
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
    	for(int i = 0; i < pasos; i++) {
    		tiempo = i;
    		System.out.println(this);
    	}
    		
    }

}
