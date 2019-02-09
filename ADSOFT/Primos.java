/**
 * Programa destinado a la comprobación de
 * números primos
 * @author Nicolás Serrano y Álvaro Sánchez
 * Grupo de prácticas: 2213
 */

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Esta clase mantiene una caché de números primos, para
 * evitar cálculos repetidos, y calcula si un número es primo
 * La cache conserva los primos calculados
 */
public class Primos {
    // usamos un conjunto ordenado, que implementa TreeSet
    private SortedSet<Integer> primos= new TreeSet<>();
    private int max=1;

    /**
     *
     * @return cache con los primos calculados
     */
    public SortedSet<Integer> getPrimos(){
        return primos;
    }

    public String toString(){
        return "Primos hasta "+ max+ " = "+primos;
    }

    /**
     *
     * @param n un número entero
     * @return si n es primo
     */
    public boolean esPrimo(int n){
        if (n<2) return false;
        if (n>max) actualizaPrimos(n);
        return primos.contains(n);
    }


    /*
    Completa los métodos actualizaPrimos y compruebaPrimo
     */

    /**
     * Este método llama a compruebaPrimo, y lo añade, si es primo,
     * para todos los números entre max+1 y n. Actualiza max al terminar.
     * @param n
     */
    private void actualizaPrimos(int n) {
        for(int i = 2; i <= n; i++) {
            if (compruebaPrimo(i) == true) { 
                primos.add(i);
            }
        }
        max = n;
    }

    /**
     * Comprueba si n es primo, asumiendo que el conjunto primos
     * está actualizado con todos los anteriores
     * @param n valor a comprobar
     * @return si n es primo
     */
    private boolean compruebaPrimo(int n) {
        for (int p: primos) {
            if ((n % p) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calcula los divisores primos de n, sin asumir
     * que ya se han calculado los primos menores a dicho numero
     * @param n valor a comprobar
     * @return array ordenado de divisores
     */
    public SortedSet<Integer> divisoresPrimos(int n) {
      SortedSet<Integer> divisores = new TreeSet<>();

      actualizaPrimos(n);
      for (int p: primos) {
          if ((n % p) == 0) {
            divisores.add(p);
          }
      }
      return divisores;
    }

    /**
     * Recibe un número como parámetro y se comprueba si
     * es primo, además de sus divisores primos
     * @param args número a comprobar
     */
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Se espera un número como parámetro");
        } else {
            int max = Integer.parseInt(args[0]);

            Primos p = new Primos();
            System.out.println("¿"+max+" es primo? Respuesta: "+p.esPrimo(max));
            System.out.println(p);
            System.out.println("Divisores primos de "+max+" = "+p.divisoresPrimos(max));
        }
    }

}
