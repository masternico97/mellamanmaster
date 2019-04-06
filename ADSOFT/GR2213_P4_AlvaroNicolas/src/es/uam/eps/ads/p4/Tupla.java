package es.uam.eps.ads.p4;

/**
 * Clase Tupla formada por el id de un item y una puntuacion dada por un usuario
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class Tupla implements Comparable<Tupla> {
    private long id;
    private double score;

    /**
     * Constructor de la clase tupla
     * @param id = id del item
     * @param score = score del item
-    */
    public Tupla(long id, double score){
        this.id = id;
        this.score = score;
    }

    @Override
    public int compareTo(Tupla comparacion){
        if(score > comparacion.getScore()){
            return 1;
        } else if (score < comparacion.getScore()) {
            return -1;
        } else {
            return 0;
        }
    }

    public Long getId(){
        return id;
    }

    public Double getScore(){
        return score;
    }

    public String toString(){
      return "Tupla [elemento="+id+", valor="+score+"]";
    }
}
