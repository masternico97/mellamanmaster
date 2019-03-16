package es.uam.eps.ads.p3;

/**
 * Tester de la clase Trampa
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */

public class TesterTrampa {
    public static void main(String[] args) {
        Posada solana = new Posada("Solana", 1);
        Posada tomelloso = new Posada("Tomelloso", 1);
        Posada alicante = new Posada("Alicante", 7, Luz.GRIS);
        Camino t1 = new Trampa(solana, tomelloso, 1, 2, (double) 1/2);
        Camino t2 =  new Trampa(alicante, solana, 49, 2, (double) 0);
        Explorador julio = new Explorador("Julio", 100, solana);
        int cont = 0;

        solana.addCamino(t1);
        alicante.addCamino(t2);
        Camino c = new Camino(tomelloso, solana, 1);
        tomelloso.addCamino(c);
        System.out.println(solana);

        System.out.println("Vamos a comprobar que funciona el azar con el que no nos podemos mover por un camino realizando el siguiente trayecto 100 veces.");
        for(int i = 0; i < 100; i++) {
            if(julio.recorre(tomelloso)) {
              cont++;
              julio.recorre(solana);
            }
        }
        System.out.println("Con una probRetorno = 50%, hemos obtenido un "+cont+"% de casos en los que se desplazó.");

        System.out.println();
        julio.setPosicion(alicante);
        julio.setEnergia(50);
        System.out.println(julio);
        System.out.println(t2);
        System.out.println("Comprobamos el factorCoste, Julio tiene 50 de energia, y el camino cuesta 49, pero al tener factor 2 no podrá pasar.");
        julio.recorre(solana);
        System.out.println(julio);
    }
}
