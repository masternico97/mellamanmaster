package es.uam.eps.ads.p3;

import java.util.*;

public interface ModeloDatos {
    public void leeFicheroPreferencias(String ruta);
    public Map<Long, Double> getPreferenciasUsuario(Long usuario);
    public Map<Long, Double> getPreferenciasItem(Long item);
    public Set<Long> getUsuariosUnicos();
    public Set<Long> getItemsUnicos();
    //Otros metodos que los/las estudiantes crean oportuno
}
