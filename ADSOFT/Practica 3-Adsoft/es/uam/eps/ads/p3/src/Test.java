package es.uam.eps.ads.p3;

public class Test {
    public static void main(String[] args) {
        Posada solana = new Posada("Solana", 1);
        Posada romeral = new Posada("Romeral", 5);
        Trampa t = new Trampa(solana, romeral, 68, 2, (float) 1.2);
        Mago m = new Mago("Sancho", 50, solana, 1, Mago.TipoDeMago.HECHICERO);
        solana.addCamino(t);
        System.out.println(t);
        System.out.println(m);
    }
}
