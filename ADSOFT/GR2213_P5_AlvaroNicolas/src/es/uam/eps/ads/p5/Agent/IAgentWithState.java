package es.uam.eps.ads.p5.Agent;

import java.util.function.Function;
import java.util.function.Predicate;

public interface IAgentWithState extends IAgent {
	IAgentState state(String name);
	IAgentWithState addBehaviour(String state, Predicate<IAgent> trigger, Function<IAgent, Boolean> behaviour);
	IAgentWithState addBehaviour(String state, Function<IAgent, Boolean> behaviour);
}
