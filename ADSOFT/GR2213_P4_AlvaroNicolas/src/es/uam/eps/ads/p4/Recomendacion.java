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

}
