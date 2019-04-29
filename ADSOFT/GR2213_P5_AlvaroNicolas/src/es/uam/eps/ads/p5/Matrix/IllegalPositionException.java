package es.uam.eps.ads.p5.Matrix;

public class IllegalPositionException extends Exception {
    public IllegalPositionException(int i, int j) {
        System.out.println("Indice fuera de la posicion maxima (i = "+i+", j = "+j+")");
    }
    public IllegalPositionException() {
        System.out.println("Indice fuera de la posicion maxima");
    }

    @Override
    public void printStackTrace() {
        System.out.println("Indice fuera de la posicion maxima");
    }
}
