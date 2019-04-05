package es.uam.eps.ads.p4;

import java.util.*;

public class RecomendadorPopularidad extends RecomendadorGeneral implements Recomendador {

    public RecomendadorPopularidad(ModeloDatos datos){
        super(datos);
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
                double itemScore = 0;
                for(Long v : datos.getUsuariosUnicos()){
                    if(u.equals(v)){
                        if(itemsInUsuario(v).contains(currentItem)){
                            itemScore++;
                        }
                    }
                }
                recomendaciones.add(new Tupla (currentItem, itemScore));
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
}
