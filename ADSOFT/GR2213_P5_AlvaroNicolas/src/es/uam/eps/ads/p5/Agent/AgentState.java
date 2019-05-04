package es.uam.eps.ads.p5.Agent;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class AgentState implements IAgentState, Cloneable {
	private String name;
	private IAgentWithState owner;
	List<Comportamiento> comportamientos;
	Map<String, Predicate<IAgent>> cambiosEstado;
	
	public AgentState(String name) {
		this.name = name;
		comportamientos = new LinkedList<>();
		cambiosEstado = new TreeMap<>();
	}

	public String name() {
		return name;
	}

	public void toState(String target, Predicate<IAgent> trigger) {
		cambiosEstado.put(target, trigger);
	}

	public IAgentState changeState() {
		for(String nombreEstado: cambiosEstado.keySet()) {
			if(cambiosEstado.get(nombreEstado).test(owner)) {
				((AgentWithState) owner).setState(name);
				return owner.state(nombreEstado);
			}
		}
		return null;
	}

	public IAgentWithState addBehaviour(Predicate<IAgent> trigger, Function<IAgent, Boolean> behaviour) {
		comportamientos.add(new Comportamiento(behaviour, trigger));
		return owner;
	}

	public IAgentWithState addBehaviour(Function<IAgent, Boolean> behaviour) {
		comportamientos.add(new Comportamiento(behaviour));
		return owner;
	}

	public void exec() {
		for(Comportamiento c : comportamientos) {
			if(c.getTrigger().test(owner)) {
				c.getBehaviour().apply(owner);
			}
		}
	}

	public void setOwner(IAgentWithState aws) {
		owner = aws;
	}

	@Override
	public IAgentState copy() {
		try {
            AgentState estado = (AgentState)this.clone();
            estado.comportamientos = this.comportamientos;
            estado.cambiosEstado = this.cambiosEstado;
            return estado;
        }
        catch(CloneNotSupportedException e) {
            System.out.println("No se puede duplicar.");
        }
        return null;
	}

}
