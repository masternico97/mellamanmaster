package es.uam.eps.ads.p4;

import java.util.*;

/**
 * Clase MetricaRecall con el metodo evalua especifico para este tipo
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class MetricaRecall extends MetricaGeneral implements Metrica {

    /**
     * Constructor de la clase MetricaRecall
     * @param modeloTest = ModeloDatos que usaremos en esta metrica
     * @param notaMinima = nota minima de los articulos que apareceran
     */
    public MetricaRecall (ModeloDatos modeloTest, double notaMinima) {
        super(modeloTest, notaMinima);
    }

    /**
     * Metodo que mide las diferencias entre dos listas y nos indica cuanto se
     * asemejan (0,1)
     * @param rec = recomendacion que usaremos para comparar con los itemsRelevantes
     * @param n = primeros n articulos de la lista obtenida del recomendador
     */
    @Override
    public double evalua(Recomendacion rec, int n) throws UsuarioNoRelevante {
        Set<Long> itemsRelevantes = getItemsRelevantes(rec.getUsuario());
        List<Tupla> recomendaciones = rec.getRecomendaciones();
        double contador = 0;

        if(itemsRelevantes.size() == 0) {
          throw new UsuarioNoRelevante(0);
        }

        for(int i = 0; i < recomendaciones.size() && i < n; i++) {
            if(itemsRelevantes.contains(recomendaciones.get(i).getId())) {
                contador++;
            }
        }

        return contador/itemsRelevantes.size();
    }
}
