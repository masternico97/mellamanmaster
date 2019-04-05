package es.uam.eps.ads.p4;

public class UsuarioNoRelevante extends Exception {
    public UsuarioNoRelevante(Recomendacion rec){
        super("Error en la recomendación ("+rec+")");
    }
    public UsuarioNoRelevante(int n){
        super("El numero de artículos ha de ser mayor que 0 (n = "+n+")");
    }
}
