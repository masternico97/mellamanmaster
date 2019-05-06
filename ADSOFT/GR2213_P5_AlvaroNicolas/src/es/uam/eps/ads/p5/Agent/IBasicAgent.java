package es.uam.eps.ads.p5.Agent;

/**
 * Interfaz IBasicAgent con los metodos de un agente basico
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public interface IBasicAgent {
    Cell cell();
    IBasicAgent copy();
    void setCell(Cell cell); //README
}
