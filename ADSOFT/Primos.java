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
    private void actualizaPrimos(int n){
        int i;
        for(i = 2; i < n; i++){
            if (compruebaPrimo(i) == true) primos.add(i);
        }
        if(compruebaPrimo(i) == true) {
            primos.add(i);
            System.out.println("El número "+n+" es primo.");
        }
        else{
            System.out.println("El número "+n+" no es primo."); 
        }
        max = n;
    }

    /**
     * Comprueba si n es primo, asumiendo que el conjunto primos
     * está actualizado con todos los anteriores
     * @param n valor a comprobar
     * @return si n es primo
     */
    private boolean compruebaPrimo(int n){
        for (int p: primos) {
            if ((n % p) == 0) {
                return false; 
            }
        }
        return true;
    }

    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Se espera al menos un número como parámetro");
        }
        else {
            int max = Integer.parseInt(args[0]); //convertimos a entero
            Primos p = new Primos();
            p.esPrimo(max);
            System.out.println(p);
        }
    }

}