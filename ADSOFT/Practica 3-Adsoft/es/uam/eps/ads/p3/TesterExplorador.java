package es.uam.eps.ads.p3;

/**
 * Tester de la clase Explorador
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de prácticas: 2213
 */

public class TesterExplorador {
    public static void main(String[] args) {
        /*Creamos tres posadas que iremos uniendo con caminos*/
        Posada solana = new Posada("Solana", 3);
        Posada romeral = new Posada("Romeral", 15);
        Posada tomelloso = new Posada("Tomelloso"); // Por defecto energía recuperada 2

        /*Creamos un par de caminos para unir las posadas*/
        Camino cam1 = new Camino(tomelloso, romeral, 35);
        Camino cam2 = new Camino(romeral, solana, 27);
        Camino cam3 = new Camino(romeral, tomelloso, 33);
        Camino cam4 = new Camino(solana, tomelloso, 23);


        /*Creamos un explorador*/
        Explorador donQuijote = new Explorador("Don Quijote", 51, tomelloso);

        /*Anadimos los caminos a las posadas*/
        tomelloso.addCamino(cam1);
        romeral.addCamino(cam2);
        romeral.addCamino(cam3);
        solana.addCamino(cam4);

        /*Imprimimos al explorador con su posicion inicial, encontrandose
        en tomelloso con energia 51*/
        System.out.print("Posición inicial: ");
        System.out.println(donQuijote);

        /*Ahora se desplazará a romeral quedandose con energia = 51-35+15 = 31*/
        donQuijote.recorre(cam1);
        System.out.print("Se desplaza a Romeral: ");
        System.out.println(donQuijote);
        System.out.println();


        /*Intentamos desplazarnos por el camino 4 pero al no estar en romeral,
        nos mantenemos en la misma posicion*/
        donQuijote.recorre(cam4);
        System.out.print("Nos mantenemos donde estamos: ");
        System.out.println(donQuijote);
        System.out.println();


        /*Tratamos de desplazarnos primero a Tomelloso pero no disponemos de
        energia suficiente asi que vamos a solana. Si la funcion
        recorre devuelve false se imprimira exito.*/
        if(donQuijote.recorre(tomelloso, solana) == true){
            System.out.println(donQuijote);
            System.out.println("Fracaso en el retorno.");
        }
        else {
            System.out.println(donQuijote);
            System.out.println("Éxito en el retorno.");
        }
    }
}
