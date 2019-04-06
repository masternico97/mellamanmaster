package es.uam.eps.ads.p4;

import java.util.*;

/**
 * Clase RecomendadorAleatorio que recomienda usuarios con aleatoriedad
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class RecomendadorAleatorio extends RecomendadorGeneral {

    /**
     * Constructor de la clase RecomendadorAleatorio
     * @param datos = ModeloDatos que usaremos en esta clase
     */
    public RecomendadorAleatorio(ModeloDatos datos){
        super(datos);
    }

    /**
     * Metodo que aconseja al usuario con aleatoriedad
     * @param u = usuario u
     * @param longitudRecomendacion = longitud que tendra la recomendacion producida
     * @return double = numero decimal de 0 a 1
     */
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
}
