package es.uam.eps.ads.p4;

import java.util.*;

public class Recomendacion {
    private Long usuario;
    private List<Tupla> recomendaciones = new ArrayList<>();

    public Recomendacion(Long usuario){
        this.usuario = usuario;
    }

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
