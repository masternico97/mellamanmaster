package es.uam.eps.ads.p5.Test;

import es.uam.eps.ads.p5.Agent.BasicAgent;
import es.uam.eps.ads.p5.Simulator.BasicSimulator;

/**
 * Clase MainApartado3 con el main del ejercicio 3
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class MainApartado3 {

    public static void main(String args[]) {
    	BasicSimulator s = new BasicSimulator(10,10);
    	BasicAgent random = new BasicAgent("random");
    	BasicAgent outer = new BasicAgent("outer");
    	
    	s.create(random, 10, 5, 5);
        s.create(outer, 10, 7, 7);
        s.run(2);
    }
}
