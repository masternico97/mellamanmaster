package es.uam.eps.ads.p4;

import java.util.*;

/**
 * Clase RecomendadorGeneral con los aspectos comunes de los distintos recomendadores
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
 public abstract class RecomendadorGeneral implements Recomendador {

  protected ModeloDatos datos;

  /**
   * Constructor de la clase RecomendadorGeneral
   * @param datos = ModeloDatos que usaremos en esta clase
   */
  public RecomendadorGeneral(ModeloDatos datos){
      this.datos = datos;
  }

  /**
   * Metodo que devuelve los items que tiene un usuario
   * @param u = usuario a obtener sus preferencias
   * @return Set = set de items con las preferencias
   */
  public Set<Long> itemsInUsuario(Long u){
      return datos.getPreferenciasUsuario(u).keySet();
  }
}
