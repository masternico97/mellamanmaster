package es.uam.eps.ads.p5.Simulator;

import es.uam.eps.ads.p5.Agent.Cell;
import es.uam.eps.ads.p5.Agent.IBasicAgent;
import es.uam.eps.ads.p5.Matrix.*;

/**
 * Clase BasicSimulator empleada en el apartado 3
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class BasicSimulator extends GeneralSimulator {

    /**
     * Constructor de la clase BasicSimulator
     * @param numFilas numero de filas de la matriz
     * @param numColumnas numero de columnas de la matriz
     */
    public BasicSimulator(int numFilas, int numColumnas) {
        super(numFilas, numColumnas);
    }

    /**
     * Metodo para crear un simulador basico a partir de los parametros
     *
     * @param agent agente a usar
     * @param numAgentes numero de agentes
     * @param fila fila usada
     * @param columna columna usada
     */
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

    /**
     * Metodo para ejecutar la simulacion
     *
     * @param pasos numero de pasos a ejecutar
     */
    public void run(int pasos) {
    	for(int i = 0; i < pasos; i++) {
    		tiempo = i;
    		System.out.println(this);
    	}
    		
    }

}
