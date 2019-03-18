package es.uam.eps.ads.p3;

public class RecomendadorPopularidad implements Recomendador {
    Long usuario;
    List<Tupla> recomendaciones;

    public Recomendacion recomienda(Long u, int longitudRecomendacion) throws RecomendacionInvalida;
}
