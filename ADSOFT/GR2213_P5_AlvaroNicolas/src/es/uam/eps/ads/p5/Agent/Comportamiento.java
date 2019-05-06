package es.uam.eps.ads.p5.Agent;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Clase Comportamiento con la tupla comportamiento - condicion
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class Comportamiento {

    private Function<IAgent, Boolean> behaviour;
    private Predicate<IAgent> trigger;

    /**
     * Constructor de la clase comportamiento con condicion
     *
     * @param behaviour comportamiento del agente
     * @param trigger condicion del comportamiento
     */
    public Comportamiento(Function<IAgent, Boolean> behaviour,
                          Predicate<IAgent> trigger) {
        this.behaviour = behaviour;
        this.trigger = trigger;
    }

    /**
     * Constructor de la clase comportamiento sin condicion
     *
     * @param behaviour comportamiento del agente
     */
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
