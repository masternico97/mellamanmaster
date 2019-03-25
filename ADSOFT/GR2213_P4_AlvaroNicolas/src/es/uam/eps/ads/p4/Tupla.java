package es.uam.eps.ads.p4;

public class Tupla implements Comparable<Tupla> {
    private long id;
    private double score;

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
}
