package es.uam.eps.ads.p4;

import java.util.*;

public class MetricaRecall implements Metrica {

    private ModeloDatos modeloTest;
    private double notaMinima;

    public MetricaRecall (ModeloDatos modeloTest, double notaMinima) {
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

    @Override
    public double evalua(Recomendacion rec, int n) throws UsuarioNoRelevante {
        Set<Long> itemsRelevantes = getItemsRelevantes(rec.getUsuario());
        List<Tupla> recomendaciones = rec.getRecomendaciones();
        double contador = 0;

        for(int i = 0; i < recomendaciones.size() && i < n; i++) {
            if(itemsRelevantes.contains(recomendaciones.get(i).getId())) {
                contador++;
            }
        }

        return contador/itemsRelevantes.size();
    }
}
