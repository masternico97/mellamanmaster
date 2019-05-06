package es.uam.eps.ads.p5.Simulator;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import es.uam.eps.ads.p5.Agent.Cell;
import es.uam.eps.ads.p5.Agent.IAgent;
import es.uam.eps.ads.p5.Matrix.IMatrixElement;
import es.uam.eps.ads.p5.Matrix.IllegalPositionException;
import es.uam.eps.ads.p5.Matrix.MatrixElement;

/**
 * Clase Simulator empleada a partir del ejercicio 4
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class Simulator extends GeneralSimulator{
	private List<IAgent> agentes;

	/**
	 * Constructor de la clase Simulator
	 * @param numFilas = numero de filas de la matriz
	 * @param numColumnas = numero de columnas de la matriz
	 */
	public Simulator(int numFilas, int numColumnas) {
		super(numFilas, numColumnas);
		agentes = new LinkedList<>();
	}

	/**
	 * Metodo para crear un simulador a partir de los parametros
	 *
	 * @param agent agente a usar
	 * @param numAgentes numero de agentes
	 * @param fila fila usada
	 * @param columna columna usada
	 */

    public void create(IAgent agent, int numAgentes, int fila, int columna) {
        IAgent aux;
        Cell cell = new Cell();
        
        agent.setCell(cell);
        for(int i = 0; i < numAgentes; i++) {
			aux = agent.copy();
			cell.add(aux);
			agentes.add(aux);
        }
        IMatrixElement<Cell> element = new MatrixElement<>(fila, columna, cell, entorno);
        cell.iniElement(element);
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
	@Override
	public void run(int pasos) {
		for(int i = 0; i < pasos; i++) {
			Boolean [] cogido = new Boolean [agentes.size()];	//Array que comprueba si un agente ya ha salido
			Random rand = new Random();
			int contador = 0;
			
			for (int j = 0; j < agentes.size(); j++){
				cogido[j] = false;
			}
			
			tiempo = i;
			System.out.println(this);
		
			while (contador < agentes.size()){
				int valor = rand.nextInt(agentes.size());
			     if (!cogido[valor]){
			           cogido[valor] = true;
			           agentes.get(valor).exec();
			           contador++;
			      }
			}
		}
	}

}
