package es.uam.eps.ads.p4;

public class TesterSimilitud {
    public static void main(String[] args) {
        ModeloDatosRecomendacion modelo = new ModeloDatosRecomendacion("../txt/PruebaTraining.txt");
        SimilitudCoseno similitudCos = new SimilitudCoseno(modelo);
        RecomendadorVecinos recomendador = new RecomendadorVecinos(modelo, 2);
        Recomendacion recomendacion = new Recomendacion((long) 190);
        double similitud;

        for(Long u : modelo.getUsuariosUnicos()) {
            for(Long v : modelo.getUsuariosUnicos()) {
                if(u != v) {
                    similitud = similitudCos.sim(u, v);
                    System.out.println("Similitud entre "+u+" y "+v+": "+similitud);
                }
            }
        }
        System.out.println();
        try {
          recomendacion = recomendador.recomienda((long) 190, 3);
        } catch(RecomendacionInvalida e) {
          System.out.println(e);
        }
        System.out.println(recomendacion);
    }
}
