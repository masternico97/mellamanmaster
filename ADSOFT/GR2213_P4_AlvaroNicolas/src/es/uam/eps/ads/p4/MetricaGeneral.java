package es.uam.eps.ads.p4;

import java.util.*;

/**
 * Clase abstracta MetricaGeneral con los campos comunes a las distintas metricas
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public abstract class MetricaGeneral implements Metrica {

    protected ModeloDatos modeloTest;
    protected double notaMinima;

    /**
     * Constructor de la clase MetricaGeneral
     * @param modeloTest = ModeloDatos que usaremos en esta metrica
     * @param notaMinima = nota minima de los articulos que apareceran
     */
    public MetricaGeneral (ModeloDatos modeloTest, double notaMinima) {
        this.modeloTest = modeloTest;
        if(notaMinima < 1) {
            this.notaMinima = 1;
        }
        else if(notaMinima > 5) {
            this.notaMinima = 5;
        }
        else {
            this.notaMinima = notaMinima;
        }
    }

    /**
     * Metodo para conseguir los items mas relevantes de un determinado usuario
     * @param u = usuario a obtener datos
     * @return Set = set de los items mas relevantes del usuario, con nota
     * mayor a notaMinima
     */
    @Override
    public Set<Long> getItemsRelevantes(Long u) {
        Set<Long> itemsRelevantes = new HashSet<>();
        Map<Long, Double> preferenciasUsuario = modeloTest.getPreferenciasUsuario(u);

        for(Map.Entry<Long, Double> entry : preferenciasUsuario.entrySet()) {
            if(entry.getValue() >= notaMinima) {
                itemsRelevantes.add(entry.getKey());
            }
        }
        return itemsRelevantes;
    }
}
