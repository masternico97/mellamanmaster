package es.uam.eps.ads.p5.Simulator;

import es.uam.eps.ads.p5.Matrix.*;

/**
 * Clase abstracta GeneralSimulator de la cual extienden el resto de simuladores
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public abstract class GeneralSimulator {

	protected IMatrix entorno;
    protected int tiempo;

    /**
     * Constructor de la clase GeneralSimulator
     * @param numFilas = numero de filas de la matriz
     * @param numColumnas = numero de columnas de la matriz
     */
    public GeneralSimulator(int numFilas, int numColumnas) {
        entorno = new Matrix(numFilas, numColumnas);
    }

    @Override
    public String toString() {
        return "++++++++++++++++++++++++++++++++++++++++++++++\n" +
               "Time = "+tiempo+"\n"+entorno.toString();
    }
}
