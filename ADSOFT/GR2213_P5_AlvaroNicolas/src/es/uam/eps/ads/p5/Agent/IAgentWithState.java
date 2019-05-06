package es.uam.eps.ads.p5.Agent;

import java.util.function.Function;
import java.util.function.Predicate;


/**
 * Interfaz IAgentWithState con el comportamiento de un agente con estado
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public interface IAgentWithState extends IAgent {
	IAgentState state(String name);
	IAgentWithState addBehaviour(String state, Predicate<IAgent> trigger, Function<IAgent, Boolean> behaviour);
	IAgentWithState addBehaviour(String state, Function<IAgent, Boolean> behaviour);
}
