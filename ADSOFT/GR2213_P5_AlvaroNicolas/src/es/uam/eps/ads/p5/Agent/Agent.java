package es.uam.eps.ads.p5.Agent;

import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.Predicate;

public class Agent extends BasicAgent implements IAgent, Cloneable {

    LinkedList<Function<IAgent, Boolean>> comportamientos;

    public Agent(String tipo) {
        super(tipo);
        comportamientos = new LinkedList<>();
    }

    @Override
    public void moveTo(Cell destination) {

    }
    void exec(); // Ejecutar comportamiento del agente
    IAgent addBehaviour(Predicate<IAgent> trigger, Function<IAgent, Boolean> behaviour);
    IAgent addBehaviour(Function<IAgent, Boolean> behaviour);

    @Override
    public IAgent copy() {
        Agent agent = null;
        agent.tipo = this.tipo; /*Consideramos que al ser inmutable el String, seria indiferente
                                  agent.tipo = new String(this.tipo), que seria redundante*/
        agent.cell = this.cell.copy();
        agent.comportamientos = this.comportamientos;

        return agent;
    }
}
