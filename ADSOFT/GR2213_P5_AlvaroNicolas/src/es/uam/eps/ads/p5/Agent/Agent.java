package es.uam.eps.ads.p5.Agent;

import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Clase Agent con el comportamiento de un agente
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class Agent extends BasicAgent implements IAgent, Cloneable {

    protected LinkedList<Comportamiento> comportamientos;

    /**
     * Constructor de la clase Agent
     *
     * @param tipo tipo de agente
     */
    public Agent(String tipo) {
        super(tipo);
        comportamientos = new LinkedList<>();
    }

    /**
     * Metodo para mover un agente a una celda destino
     *
     * @param destination celda destino
     */
    @Override
    public void moveTo(Cell destination) {
        this.cell.agents().remove(this);
        this.setCell(destination);
        destination.getMatrixElement();
        destination.add(this);
    }

    /**
     * Metodo para ejecutar comportamiento del agente
     */
    @Override
    public void exec(){
        for(Comportamiento c : comportamientos) {
            if(c.getTrigger().test(this)) {
                c.getBehaviour().apply(this);
            }
        }
    }
    
    /**
     * Metodo para anyadir un comportamiento con condicion
     *
     * @param trigger condicion del comportamiento
     * @param behaviour comportamiento del agente
     * @return IAgent agente modificado, lo retornamos para poder encadenar
     * estas funciones
     */
    @Override
    public IAgent addBehaviour(Predicate<IAgent> trigger, Function<IAgent, Boolean> behaviour) {
        comportamientos.add(new Comportamiento(behaviour, trigger));
        return this;
    }
    
    /**
     * Metodo para anyadir un comportamiento sin condicion
     *
     * @param behaviour comportamiento del agente
     * @return IAgent agente modificado, lo retornamos para poder encadenar
     * estas funciones
     */
    @Override
    public IAgent addBehaviour(Function<IAgent, Boolean> behaviour) {
        comportamientos.add(new Comportamiento(behaviour));
        return this;
    }

    /**
     * Metodo para hacer una copia del agente
     *
     * @return IAgent copia del agente
     */
    @Override
    public IAgent copy() {    
        Agent agent = new Agent(tipo);
        agent.comportamientos = this.comportamientos;
        return agent;
    }
}
