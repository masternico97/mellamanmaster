package es.uam.eps.ads.p5.Agent;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Clase AgentWithState con el comportamiento de un agente con estados
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class AgentWithState extends Agent implements IAgentWithState, Cloneable{
	private List<IAgentState> estados;
	private IAgentState estadoActual;
	
	/**
     * Constructor privado de la clase AgentWithState para las copias
     */
	private AgentWithState(String tipo) {
		super(tipo);
		estados = new LinkedList<>();
	}

	/**
     * Constructor de la clase AgentWithState
     */
	public AgentWithState(String tipo, String ...states) {
		super(tipo);
		estados = new LinkedList<>();
		
		for(String currentState: states) {
			IAgentState addState = new AgentState(currentState);
			addState.setOwner(this);
			estados.add(addState);
		}
		setState(states[0]);
	}

	/**
     * Metodo para cambiar el estado actual
     *
     * @param state nombre del estado al que cambiar 
     */
	public void setState(String state) {
		for(IAgentState currentState: estados) {
			if(currentState.name().equals(state)) {
				estadoActual = currentState;
			}
		}
	}
	
	/**
     * Metodo obtener un estado del agente
     *
     * @param name nombre del estado a obtener
     * @return IAgentState estado encontrado
     */
	@Override
	public IAgentState state(String name) {
		for(IAgentState currentState: estados) {
			if(currentState.name().equals(name)) {
				return currentState;
			}
		}
		return null;
	}

	/**
     * Metodo para anyadir un comportamiento con condicion a un estado
     *
     * @param state nombre del estado al que añadir el comportamiento
     * @param trigger condicion del comportamiento
     * @param behaviour comportamiento del agente
     * @return IAgentWithState agente modificado, lo retornamos para poder encadenar
     * estas funciones
     */
	@Override
	public IAgentWithState addBehaviour(String state, Predicate<IAgent> trigger, Function<IAgent, Boolean> behaviour) {
		for(IAgentState currentState: estados) {
			if(currentState.name().equals(state)) {
				return currentState.addBehaviour(trigger, behaviour);
			}
		}
		return this;
	}

	/**
     * Metodo para anyadir un comportamiento sin condicion
     *
     * @param state nombre del estado al que añadir el comportamiento
     * @param behaviour comportamiento del agente
     * @return IAgentWithState agente modificado, lo retornamos para poder encadenar
     * estas funciones
     */
	@Override
	public IAgentWithState addBehaviour(String state, Function<IAgent, Boolean> behaviour) {
		for(IAgentState currentState: estados) {
			if(currentState.name().equals(state)) {
				return currentState.addBehaviour(behaviour);
			}
		}
		return this;
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
        estadoActual.exec();
    }
	
	/**
     * Metodo para hacer una copia del agente
     *
     * @return IAgentWithState copia del agente
     */
	@Override
    public IAgentWithState copy() {
        AgentWithState agent = new AgentWithState(this.tipo);
        agent.comportamientos = this.comportamientos;
        for(IAgentState currentState : estados) {
        	IAgentState addState = currentState.copy();
			addState.setOwner(this);
			agent.estados.add(addState);
        }
        agent.setState(estadoActual.name());
        
        return agent;
    }
	

}
