package es.uam.eps.ads.p4;

class RecomendacionInvalida extends Exception{
    public RecomendacionInvalida(Long user){
        super("El usuario "+user+" no existe");
    }
    public RecomendacionInvalida(int longitud){
        super("La longitud de la recomendacion tiene que ser mayor que 0");
    }
}
