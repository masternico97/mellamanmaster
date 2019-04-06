package es.uam.eps.ads.p4;

/**
 * Interfaz similitud con los metodos de SimilitudCoseno
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public interface Similitud {
    public double sim(Long u1, Long u2);
}
