package es.uam.eps.ads.p3.src;

public class TesterMago {
    public static void main(String[] args) {
        Posada solana = new Posada("Solana", 1, Luz.NEGRA);
        Posada romeral = new Posada("Romeral", 5);
        Posada tomelloso = new Posada("Tomelloso", 5, Luz.TENEBROSA);
        Posada alicante = new Posada("Alicante", 7, Luz.GRIS);
        Posada pontevedra = new Posada("Pontevedra", 7, Luz.DIVINA);


        Trampa t = new Trampa(solana, tomelloso, 21, 2, (float) 0);//probRetorno = 0, será comprobada en TesterTrampa
        Mago julio = new Mago("Julio", 50, solana, 1, Mago.TipoDeMago.HECHICERO);
        Mago maria = new Mago("Maria", 40, romeral, 1, Mago.TipoDeMago.HADA);

        solana.addCamino(t);
        solana.addCamino(new Camino(solana, romeral, 14));
        solana.addCamino(new Camino(solana, alicante, 33));
        romeral.addCamino(new Camino(romeral, pontevedra, 12));


        System.out.println(julio);
        System.out.println("Creamos un camino trampa ("+t+") y hacemos que se desplace por él.");
        System.out.println("Y al tratarse de un mago, no se puede desplazar por la trampa y se queda en el mismo lugar.");
        julio.recorre(tomelloso);
        System.out.println(julio);

        System.out.println();
        System.out.println(romeral);
        System.out.println("Al tener iluminación blanca y el mago no tener suficiente poder se queda donde está:");
        julio.recorre(romeral);
        System.out.println(julio);

        System.out.println();
        System.out.println(alicante);
        julio.recorre(alicante);
        System.out.println("Pese a ser gris, puede desplazarse a ella porque tiene poder 1:");
        System.out.println(julio);

        System.out.println();
        System.out.println(maria);
        System.out.println("Al tratarse de un hada puede desplazarse desde "+romeral+" a "+pontevedra);
        maria.recorre(romeral, pontevedra);
        System.out.println(maria);
    }
}
