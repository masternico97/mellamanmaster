package es.uam.eps.ads.p4;

import java.util.*;

/**
 * Interfaz modelo de datos con los metodos correspondientes a ModeloDatosRecomendacion
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public interface ModeloDatos {
    public void leeFicheroPreferencias(String ruta);
    public Map<Long, Double> getPreferenciasUsuario(Long usuario);
    public Map<Long, Double> getPreferenciasItem(Long item);
    public Set<Long> getUsuariosUnicos();
    public Set<Long> getItemsUnicos();
}
