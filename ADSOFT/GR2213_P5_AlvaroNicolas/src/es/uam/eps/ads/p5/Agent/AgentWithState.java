package es.uam.eps.ads.p5.Agent;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class AgentWithState extends BasicAgent implements IAgentWithState, Cloneable{
	List<AgentState> estados;

	public AgentWithState(String tipo, String ...states) {
		super(tipo);
		estados = new LinkedList<>();
		
		for(String currentState: states) {
			estados.add(new AgentState(currentState));
		}
	}

	@Override
	public IAgentState state(String name) {
		for(AgentState currentState: estados) {
			if(currentState.name().equals(name)) {
				return currentState;
			}
		}
		return null;
	}

	@Override
	public IAgentWithState addBehaviour(String state, Predicate<IAgent> trigger, Function<IAgent, Boolean> behaviour) {
		return null;
	}

	@Override
	public IAgentWithState addBehaviour(String state, Function<IAgent, Boolean> behaviour) {
		return null;
	}

}
