package es.uam.eps.ads.p4;

import java.util.*;

/**
 * Clase RecomendadorVecinos que recomienda usuarios por similitud
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class RecomendadorVecinos extends RecomendadorGeneral {
    private int vecinos;

    /**
     * Constructor de la clase RecomendadorVecinos
     * @param datos = ModeloDatos que usaremos en esta clase
     * @param numVecinos = numero de vecinos usados
     */
    public RecomendadorVecinos(ModeloDatos datos, int numVecinos){
        super(datos);
        if(numVecinos <= 0){
            vecinos = 5;
        } else {
            vecinos = numVecinos;
        }
    }

    /**
     * Metodo que aconseja al usuario segun los gustos similares de otros usuarios
     * @param u = usuario u
     * @param longitudRecomendacion = longitud que tendra la recomendacion producida
     * @return double = numero decimal de 0 a 1
     */
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

    private double userGetItemScore(Long user, Long item){
        if(datos.getPreferenciasUsuario(user).containsKey(item)) {
            return datos.getPreferenciasUsuario(user).get(item);
        }
        return 0;
    }
}
