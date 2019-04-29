package es.uam.eps.ads.p5.Simulator;

import es.uam.eps.ads.p5.Matrix.*;

public abstract class GeneralSimulator {

	protected IMatrix entorno;
    protected int tiempo;

    public GeneralSimulator(int numFilas, int numColumnas) {
        entorno = new Matrix(numFilas, numColumnas);
    }

    @Override
    public String toString() {
        return "++++++++++++++++++++++++++++++++++++++++++++++\n" +
               "Time = "+tiempo+"\n"+entorno.toString();
    }
}
