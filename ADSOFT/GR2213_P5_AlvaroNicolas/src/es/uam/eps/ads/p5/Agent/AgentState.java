package es.uam.eps.ads.p5.Agent;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Clase AgentState con el comportamiento de un estado de agente
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class AgentState implements IAgentState, Cloneable {
	private String name;
	private IAgentWithState owner;
	private List<Comportamiento> comportamientos;
	private Map<String, Predicate<IAgent>> cambiosEstado;
	
	/**
     * Constructor de la clase AgentState
     *
     * @param name nombre del estado
     */
	public AgentState(String name) {
		this.name = name;
		comportamientos = new LinkedList<>();
		cambiosEstado = new TreeMap<>();
	}

	/**
     * Metodo getter del nombre del estado
     *
     * @return String nombre del estado
     */
	@Override
	public String name() {
		return name;
	}

	/**
     * Metodo para establecer una condicion de cambio de estado
     *
     * @param target nombre del estado al que cambiar
     * @param trigger condicion de cambio de estado
     */
	@Override
	public void toState(String target, Predicate<IAgent> trigger) {
		cambiosEstado.put(target, trigger);
	}

	/**
     * Metodo para establecer si se cambia de estado
     * @return IAgentState estado actual 
     */
	@Override
	public IAgentState changeState() {
		for(String nombreEstado: cambiosEstado.keySet()) {
			if(cambiosEstado.get(nombreEstado).test(owner)) {
				((AgentWithState) owner).setState(name);
				return owner.state(nombreEstado);
			}
		}
		return this;
	}

	/**
     * Metodo para anyadir un comportamiento con condicion
     *
     * @param trigger condicion del comportamiento
     * @param behaviour comportamiento del estado
     * @return IAgentWithState agente modificado, lo retornamos para poder encadenar
     * estas funciones
     */
	@Override
	public IAgentWithState addBehaviour(Predicate<IAgent> trigger, Function<IAgent, Boolean> behaviour) {
		comportamientos.add(new Comportamiento(behaviour, trigger));
		return owner;
	}

	/**
     * Metodo para anyadir un comportamiento sin condicion
     *
     * @param behaviour comportamiento del estado
     * @return IAgentWithState agente modificado, lo retornamos para poder encadenar
     * estas funciones
     */
	@Override
	public IAgentWithState addBehaviour(Function<IAgent, Boolean> behaviour) {
		comportamientos.add(new Comportamiento(behaviour));
		return owner;
	}

	/**
     * Metodo para ejecutar comportamiento del estado
     */
	@Override
	public void exec() {
		for(Comportamiento c : comportamientos) {
			if(c.getTrigger().test(owner)) {
				c.getBehaviour().apply(owner);
			}
		}
	}

	@Override
	public void setOwner(IAgentWithState aws) {
		owner = aws;
	}

	/**
     * Metodo para hacer una copia del estado
     *
     * @return IAgentState copia del estado
     */
	@Override
	public IAgentState copy() {	
        AgentState estado = new AgentState(this.name);
        estado.comportamientos = this.comportamientos;
        estado.cambiosEstado = this.cambiosEstado;
        return estado;
	}

}
