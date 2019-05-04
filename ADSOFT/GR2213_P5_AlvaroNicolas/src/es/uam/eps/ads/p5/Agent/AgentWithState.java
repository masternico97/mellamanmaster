package es.uam.eps.ads.p5.Agent;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class AgentWithState extends Agent implements IAgentWithState, Cloneable{
	private List<AgentState> estados;
	private AgentState estadoActual;

	public AgentWithState(String tipo, String ...states) {
		super(tipo);
		estados = new LinkedList<>();
		
		for(String currentState: states) {
			estados.add(new AgentState(currentState));
		}
	}

	public void setState(String state) {
		for(AgentState currentState: estados) {
			if(currentState.name().equals(state)) {
				estadoActual = currentState;
			}
		}
	}
	
	public IAgentState state(String name) {
		for(AgentState currentState: estados) {
			if(currentState.name().equals(name)) {
				return currentState;
			}
		}
		return null;
	}

	public IAgentWithState addBehaviour(String state, Predicate<IAgent> trigger, Function<IAgent, Boolean> behaviour) {
		for(AgentState currentState: estados) {
			if(currentState.name().equals(state)) {
				return currentState.addBehaviour(trigger, behaviour);
			}
		}
		return this;
	}

	public IAgentWithState addBehaviour(String state, Function<IAgent, Boolean> behaviour) {
		for(AgentState currentState: estados) {
			if(currentState.name().equals(state)) {
				return currentState.addBehaviour(behaviour);
			}
		}
		return this;
	}
	
	@Override
	public void exec(){
        for(Comportamiento c : comportamientos) {
            if(c.getTrigger().test(this)) {
                c.getBehaviour().apply(this);
            }
        }
        estadoActual.exec();
    }
	

}
