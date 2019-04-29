package es.uam.eps.ads.p5.Simulator;

import java.util.List;
import java.util.Random;

import es.uam.eps.ads.p5.Agent.Cell;
import es.uam.eps.ads.p5.Agent.IAgent;
import es.uam.eps.ads.p5.Matrix.IMatrixElement;
import es.uam.eps.ads.p5.Matrix.IllegalPositionException;
import es.uam.eps.ads.p5.Matrix.MatrixElement;

public class Simulator extends GeneralSimulator{
	 private List<IAgent> agentes;
	
	public Simulator(int numFilas, int numColumnas) {
		super(numFilas, numColumnas);
	}
	
    public void create(IAgent agent, int numAgentes, int fila, int columna) {
        IAgent aux;
        Cell cell = new Cell();
        for(int i = 0; i < numAgentes; i++) {
            aux = agent.copy();
            cell.add(aux);
            agentes.add(aux);
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
			Boolean [] cogido = new Boolean [agentes.size()];	//Array que comprueba si un agente ya ha salido
			Random rand = new Random();
			int contador = 0;
			
			for (int j = 0; j < agentes.size(); j++){
				cogido[j] = false;
			}
			
			tiempo = i;

			
			while (contador < 20){
				int valor = rand.nextInt(agentes.size());
			     if (!cogido[valor]){
			           cogido[valor] = true;
			           agentes.get(valor).exec();
			           contador++;
			      }
			}
			
			System.out.println(this);
		}
	}

}
