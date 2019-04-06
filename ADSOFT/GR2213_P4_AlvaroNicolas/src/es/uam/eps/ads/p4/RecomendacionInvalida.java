package es.uam.eps.ads.p4;

/**
 * Excepcion para detectar una recomendacion no valida para un usuario
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class RecomendacionInvalida extends Exception{
    public RecomendacionInvalida(Long user){
        super("El usuario "+user+" no existe");
    }
    public RecomendacionInvalida(int longitud){
        super("La longitud de la recomendacion tiene que ser mayor que 0 (longitud = "+longitud+")");
    }
}
