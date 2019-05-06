package es.uam.eps.ads.p5.Agent;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Interfaz IAgentState con el estado del agente con estado
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public interface IAgentState {
	String name();
	void toState(String target, Predicate<IAgent> trigger);
	IAgentState changeState();
	IAgentWithState addBehaviour(Predicate<IAgent> trigger, Function<IAgent, Boolean> behaviour);
	IAgentWithState addBehaviour(Function<IAgent, Boolean> behaviour);
	void exec();
	void setOwner(IAgentWithState aws);
	IAgentState copy();
}
