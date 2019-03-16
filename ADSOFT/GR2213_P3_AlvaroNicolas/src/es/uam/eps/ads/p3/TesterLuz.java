package es.uam.eps.ads.p3;

/**
 * Tester de la clase Luz
 * Bastante corto dado que gran parte de las comprobaciones
 * de las funcionalidades de la luz fueron realizadas en TesterMago.
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */

public class TesterLuz {
    public static void main(String[] args) {
        Posada solana = new Posada("Solana", 1, Luz.DIABOLICA);
        Posada pontevedra = new Posada("Pontevedra", 3);
        System.out.println(solana);
        System.out.println("Modificamos la luz de Solana:");
        solana.cambiarLuz(Luz.TENEBROSA);
        System.out.println(solana);
        System.out.println("Puede tener otras iluminaciones:");
        System.out.println(pontevedra);

    }
}
