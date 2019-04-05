package es.uam.eps.ads.p4;

import java.util.*;

public class RecomendadorAleatorio implements Recomendador {
    private ModeloDatos datos;

    public RecomendadorAleatorio(ModeloDatos datos){
        this.datos = datos;
    }

    public Recomendacion recomienda(Long u, int longitudRecomendacion) throws RecomendacionInvalida {
        List<Tupla> recomendaciones = new ArrayList<>();
        Recomendacion recomendacion = new Recomendacion(u);
        Set<Long> userItems;

        if(longitudRecomendacion <= 0){
            throw new RecomendacionInvalida(longitudRecomendacion);
        } else if (!datos.getUsuariosUnicos().contains(u)){
            throw new RecomendacionInvalida(u);
        }

        userItems = itemsInUsuario(u);
        for(Long currentItem: datos.getItemsUnicos()){
            if(!userItems.contains(currentItem)){
                recomendaciones.add(new Tupla (currentItem, Math.random()));
            }
        }

        /** Se ordena esas recomendaciones de mayor a menor */
        Collections.sort(recomendaciones, Collections.reverseOrder());

        /** Se van insertando de mayor a menos longitudRecomendacion elementos */
        for(int i = 0; i < longitudRecomendacion; i++){
            recomendacion.addRecomendacion(recomendaciones.get(i));
        }
        return recomendacion;
    }

    /** Devuelve los items que tiene un usuario
     */
    private Set<Long> itemsInUsuario(Long u){
        return datos.getPreferenciasUsuario(u).keySet();
    }
}