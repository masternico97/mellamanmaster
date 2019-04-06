package es.uam.eps.ads.p4;

/**
 * Interfaz de la clase RecomendadorGeneral
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public interface Recomendador {
    public Recomendacion recomienda(Long u, int longitudRecomendacion) throws RecomendacionInvalida;
}
