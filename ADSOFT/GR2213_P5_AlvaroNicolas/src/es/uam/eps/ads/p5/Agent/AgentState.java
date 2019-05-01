package es.uam.eps.ads.p5.Agent;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class AgentState implements IAgentState, Cloneable {
	private String name;
	private IAgentWithState owner;
	List<Comportamiento> comportamientos;
	
	public AgentState(String name) {
		this.name = name;
		comportamientos = new LinkedList<>();
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public void toState(String target, Predicate<IAgent> trigger) {
		
	}

	@Override
	public IAgentState changeState() {
		return null;
	}

	@Override
	public IAgentWithState addBehaviour(Predicate<IAgent> trigger, Function<IAgent, Boolean> behaviour) {
		return null;
	}

	@Override
	public IAgentWithState addBehaviour(Function<IAgent, Boolean> behaviour) {
		return null;
	}

	@Override
	public void exec() {
		
	}

	@Override
	public void setOwner(IAgentWithState aws) {
		owner = aws;
	}

	@Override
	public IAgentState copy() {
		return null;
	}

}
