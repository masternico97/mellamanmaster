package es.uam.eps.ads.p4;

public class UsuarioNoRelevante extends Exception {
    public UsuarioNoRelevante(int n){
        super("El conjunto de items relevantes ha de ser mayor que 0 (n = "+n+")");
    }
}
