package es.uam.eps.ads.p4;

import java.util.*;

public class RecomendadorVecinos implements Recomendador {
    private ModeloDatos datos;
    private int vecinos;



    public RecomendadorVecinos(ModeloDatos datos, int numVecinos){
        this.datos = datos;
        if(numVecinos <= 0){
            vecinos = 5;
        } else {
            vecinos = numVecinos;
        }
    }

    public Recomendacion recomienda(Long u, int longitudRecomendacion) throws RecomendacionInvalida {

        List<Tupla> similitudDeVecinos = new ArrayList<>();
        List<Tupla> recomendaciones = new ArrayList<>();
        Recomendacion recomendacion = new Recomendacion(u);
        Similitud similitud = new SimilitudCoseno(datos);
        Set<Long> userItems;

        if(longitudRecomendacion <= 0){
            throw new RecomendacionInvalida(longitudRecomendacion);
        } else if (!datos.getUsuariosUnicos().contains(u)){
            throw new RecomendacionInvalida(u);
        }

        /** Se calcula la similitud entre todos los vecinos */
        for(Long v : datos.getUsuariosUnicos()){
            if(!u.equals(v)){
                similitudDeVecinos.add(new Tupla(v, similitud.sim(u, v)));
            }
        }
        /** Se ordena esa similitud de mayor a menor */
        Collections.sort(similitudDeVecinos, Collections.reverseOrder());

        userItems = itemsInUsuario(u);
        for(Long currentItem: datos.getItemsUnicos()){
            if(!userItems.contains(currentItem)){
                double itemScore = 0;
                for(int i = 0; i < vecinos; i++){
                    Tupla currentTuple = similitudDeVecinos.get(i);
                    itemScore += currentTuple.getScore() * userGetItemScore(currentTuple.getId(), currentItem);
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

    /** Devuelve los items que tiene un usuario
    */
    private Set<Long> itemsInUsuario(Long u){
        return datos.getPreferenciasUsuario(u).keySet();
    }

    private double userGetItemScore(Long user, Long item){
        if(datos.getPreferenciasUsuario(user).containsKey(item)) {
            return datos.getPreferenciasUsuario(user).get(item);
        }
        return 0;
    }
}
