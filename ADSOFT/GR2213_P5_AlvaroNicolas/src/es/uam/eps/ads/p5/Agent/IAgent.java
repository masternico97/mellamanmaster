package es.uam.eps.ads.p5.Agent;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Interfaz IAgent con los metodos de un agente
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public interface IAgent extends IBasicAgent{
    void moveTo(Cell destination); // Mover a una celda adyacente
    void exec(); // Ejecutar comportamiento del agente
    IAgent addBehaviour(Predicate<IAgent> trigger, Function<IAgent, Boolean> behaviour);
    IAgent addBehaviour(Function<IAgent, Boolean> behaviour);
    IAgent copy(); // Realiza una copia del agente
}
