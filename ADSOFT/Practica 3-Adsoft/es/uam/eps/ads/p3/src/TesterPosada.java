package es.uam.eps.ads.p3.src;

/**
 * Tester de la clase Posada
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de prácticas: 2213
 */

public class TesterPosada {
    public static void main(String[] args) {
        /*Creamos tres posadas que iremos uniendo con caminos*/
        Posada solana = new Posada("Solana", 3);
        Posada romeral = new Posada("Romeral", 5);
        Posada tomelloso = new Posada("Tomelloso"); // Por defecto energía recuperada 2

        /*Creamos un par de caminos para unir las posadas*/
        Camino cam1 = new Camino(tomelloso, romeral, 35);
        Camino cam2 = new Camino(romeral, solana, 27);
        Camino cam3 = new Camino(romeral, tomelloso, 35);

        /*Anadimos los caminos a las posadas*/
        tomelloso.addCamino(cam1);
        romeral.addCamino(cam2);
        romeral.addCamino(cam3);

        /*Imprimimos el camino que va de Romeral a Solana*/
        System.out.println("Camino de Romeral a Solana: "+romeral.getCamino(solana));
        System.out.println();

        /*Comprobamos el numero de caminos en cada una de las posadas*/
        System.out.println("Numero de caminos de Solana = "+solana.getNumCaminos()+ ", tiene que haber 0. ¿Resultado correcto?: "+(solana.getNumCaminos() == 0));
        System.out.println("Numero de caminos de Romeral = "+romeral.getNumCaminos()+ ", tiene que haber 2. ¿Resultado correcto?: "+(romeral.getNumCaminos() == 2));
        System.out.println("Numero de caminos de Tomelloso = "+tomelloso.getNumCaminos()+ ", tiene que haber 1. ¿Resultado correcto?: "+(tomelloso.getNumCaminos()==1));
        System.out.println();

        /*Imprimimos las 3 posadas*/
        System.out.println(solana);
        System.out.println(romeral);
        System.out.println(tomelloso);

    }
}
