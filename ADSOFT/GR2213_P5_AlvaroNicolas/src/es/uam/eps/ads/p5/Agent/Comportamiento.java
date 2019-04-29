package es.uam.eps.ads.p5.Agent;

import java.util.function.Function;
import java.util.function.Predicate;

public class Comportamiento {

    private Function<IAgent, Boolean> behaviour;
    private Predicate<IAgent> trigger;

    public Comportamiento(Function<IAgent, Boolean> behaviour,
                          Predicate<IAgent> trigger) {
        this.behaviour = behaviour;
        this.trigger = trigger;
    }

    public Comportamiento(Function<IAgent, Boolean> behaviour) {
        this.behaviour = behaviour;
        this.trigger = x -> true;
    }

    public Function<IAgent, Boolean> getBehaviour() {
        return behaviour;
    }

    public Predicate<IAgent> getTrigger() {
        return trigger;
    }
}
