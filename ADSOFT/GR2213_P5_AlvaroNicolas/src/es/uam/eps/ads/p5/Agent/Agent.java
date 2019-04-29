package es.uam.eps.ads.p5.Agent;

import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.Predicate;

public class Agent extends BasicAgent implements IAgent, Cloneable {

    LinkedList<Comportamiento> comportamientos;

    public Agent(String tipo) {
        super(tipo);
        comportamientos = new LinkedList<>();
    }

    @Override
    public void moveTo(Cell destination) {
        this.cell.getAgentes().remove(cell);
        this.setCell(destination);
        destination.add(this);
    }

    // Ejecutar comportamiento del agente
    public void exec(){
        for(Comportamiento c : comportamientos) {
            if(c.getTrigger().test(this)) {
                c.getBehaviour().apply(this);
            }
        }
    }
    public IAgent addBehaviour(Predicate<IAgent> trigger, Function<IAgent, Boolean> behaviour) {
        comportamientos.add(new Comportamiento(behaviour, trigger));
        return this;
    }

    public IAgent addBehaviour(Function<IAgent, Boolean> behaviour) {
        comportamientos.add(new Comportamiento(behaviour));
        return this;
    }

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
