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
        this.cell.agents().remove(this);
        this.setCell(destination);
        destination.getMatrixElement();
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
        try {
            Agent agent = (Agent)this.clone();
            agent.comportamientos = this.comportamientos;
            return agent;
        }
        catch(CloneNotSupportedException e) {
            System.out.println("No se puede duplicar.");
        }
        return null;
    }
}
