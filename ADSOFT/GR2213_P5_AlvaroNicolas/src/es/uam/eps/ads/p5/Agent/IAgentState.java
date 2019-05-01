package es.uam.eps.ads.p5.Agent;

import java.util.function.Function;
import java.util.function.Predicate;

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
