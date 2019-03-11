package es.uam.eps.ads.p3.src;

/**
 * Tester de la clase Camino
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de prácticas: 2213
 */

public class TesterCamino {
    public static void main(String[] args) {
        //Creamos tres posadas que iremos uniendo con caminos
        Posada solana = new Posada("Solana", 3);
        Posada romeral = new Posada("Romeral", 5);
        Posada tomelloso = new Posada("Tomelloso"); // Por defecto energía recuperada 2

        //Creamos un par de caminos para unir las posadas
        Camino cam1 = new Camino(tomelloso, romeral, 35);
        Camino cam2 = new Camino(romeral, solana, 27);

        //Anadimos los caminos a las posadas
        tomelloso.addCamino(cam1);
        romeral.addCamino(cam2);

        //Imprimimos los caminos que tenemos
        System.out.println("Caminos existentes:");
        System.out.println(cam1);
        System.out.println(cam2);
        System.out.println();


        /*Creamos un camino con energia negativa,
        se le debería asociar un 1 a la energia*/
        Camino cam3 = new Camino(solana, romeral, -5);

        solana.addCamino(cam3);

        //Debemos conseguir un camino de coste positivo sino, obtendremos error
        System.out.print("Camino de coste positivo: ");
        if(cam3.getCoste() < 0) {
            System.out.println("Error, camino de coste negativo.");
        }
        else {
            System.out.println("Éxito.");
        }
        System.out.println();


        /*Cambiamos al camino 1 , su destino y coste pasando de ser
        Romeral(35) a Solana(33)*/
        System.out.print("Camino original: ");
        System.out.println(cam1);

        cam1.cambiarDestino(solana ,33);

        System.out.print("Camino tras el cambio: ");
        System.out.println(cam1);
    }
}
