package es.uam.eps.ads.p3;

import java.util.*;

public class RecomendadorVecinos implements Recomendador {
    private ModeloDatos datos;
    private Long usuario;
    private List<Tupla> recomendaciones = new ArrayList<>();


    public RecomendadorVecinos(ModeloDatos datos, Long usuario){
        this.datos = datos;
        this.usuario = usuario;
    }

    public Recomendacion recomienda(Long u, int longitudRecomendacion){
        int i = 0;
        Set<Long> userItems;

        userItems = itemsInUsuario();
        while(i < longitudRecomendacion){
            for(Long currentItem: datos.getItemsUnicos()){
                if(!currentItem.contains(currentItem)){
                    
                }
            }
        }
    }

    /** Devuelve los items que tiene un usuario
    */
    private Set<Long> itemsInUsuario (Long u){
        return datos.getPreferenciasUsuario(u).keySet();
    }
}
