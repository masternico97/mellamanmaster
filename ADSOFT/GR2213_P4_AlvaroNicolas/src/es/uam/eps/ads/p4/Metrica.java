package es.uam.eps.ads.p4;

import java.util.*;

public interface Metrica {
    public double evalua(Recomendacion rec, int n) throws UsuarioNoRelevante;
    public Set<Long> getItemsRelevantes(Long u);
}
