package es.uam.eps.ads.p5.Agent;

/**
 * Clase BasicAgent con el comportamiento de un agente basico
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class BasicAgent implements IBasicAgent, Cloneable {
    protected String tipo;
    protected Cell cell;

    /**
     * Constructor de la clase BasicAgent
     *
     * @param tipo tipo de agente
     */
    public BasicAgent(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public Cell cell() {
        return cell;
    }

    /**
     * Metodo para hacer una copia del agente basico
     *
     * @return IBasicAgent copia del agente
     */
    @Override
    public IBasicAgent copy() {
        try {
             return (BasicAgent)this.clone();
        }
        catch(CloneNotSupportedException e) {
            System.out.println("No se puede duplicar.");
        }
        return null;
    }

    @Override
    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
