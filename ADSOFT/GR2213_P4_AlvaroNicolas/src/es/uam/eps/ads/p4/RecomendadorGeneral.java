package es.uam.eps.ads.p4;

import java.util.*;

public abstract class RecomendadorGeneral implements Recomendador {

  protected ModeloDatos datos;

  public RecomendadorGeneral(ModeloDatos datos){
      this.datos = datos;
  }

  /** Devuelve los items que tiene un usuario
   */
  public Set<Long> itemsInUsuario(Long u){
      return datos.getPreferenciasUsuario(u).keySet();
  }
}
