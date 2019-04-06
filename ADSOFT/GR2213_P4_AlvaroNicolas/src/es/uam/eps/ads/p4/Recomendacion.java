package es.uam.eps.ads.p4;

import java.util.*;

/**
 * Clase Recomendacion con tuplas de un usuario
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class Recomendacion {
    private Long usuario;
    private List<Tupla> recomendaciones = new ArrayList<>();

    /**
     * Constructor de la clase Recomendacion
     * @param usuario = usuario
     */
    public Recomendacion(Long usuario){
        this.usuario = usuario;
    }

    /**
     * Metodo para anyadir una recomendacion a la lista
     * @param recomendacion = recomendacion a anyadir
     */
    public void addRecomendacion(Tupla recomendacion){
        recomendaciones.add(recomendacion);
    }

    public String toString() {
      return "Recomendacion [usuario="+usuario+", recomendaciones="+recomendaciones+"]";
    }

    public List<Tupla> getRecomendaciones() {
        return recomendaciones;
    }

    public Long getUsuario() {
        return usuario;
    }
}
