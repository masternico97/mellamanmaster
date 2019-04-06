package es.uam.eps.ads.p4;

/**
 * Excepcion para usuario no relevante, detectada cuando no hay itemsRelevantes
 * al realizar evalua
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class UsuarioNoRelevante extends Exception {
    public UsuarioNoRelevante(int n){
        super("El conjunto de items relevantes ha de ser mayor que 0 (n = "+n+")");
    }
}
