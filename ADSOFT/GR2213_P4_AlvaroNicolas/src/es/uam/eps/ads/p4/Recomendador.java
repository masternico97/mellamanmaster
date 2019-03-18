package es.uam.eps.ads.p3;

public interface Recomendador {
    public Recomendacion recomienda(Long u, int longitudRecomendacion) throws RecomendacionInvalida;
    //Otros metodos que loslas estudiantes crean oportuno
}
