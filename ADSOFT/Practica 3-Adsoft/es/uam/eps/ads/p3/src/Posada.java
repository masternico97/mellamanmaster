package es.uam.eps.ads.p3.src;

import java.util.*;

/**
 * Clase posada donde se reciben y entregan
 * productos, realizan comprobaciones
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */

public class Posada {
    private String nombre;
    private int recuperacion;
    private List<Camino> caminos = new ArrayList<>();
    private Luz luz;

    /**
     * Constructor de la clase posada que inicializa la recuperacion
     * de la posada a 2
     * @param nombre nombre de la posada
     */
    public Posada(String nombre) {
        this.nombre = nombre;
        this.recuperacion = 2;
        this.luz = Luz.BLANCA;
    }

    /**
     * Constructor de la clase posada
     * @param nombre nombre de la posada
     * @param recuperacion recuperacion de la posada
     */
    public Posada(String nombre, int recuperacion) {
        this.nombre = nombre;
        this.recuperacion = recuperacion;
        this.luz = Luz.BLANCA;
    }

    public Posada(String nombre, int recuperacion, Luz luz) {
        this.nombre = nombre;
        this.recuperacion = recuperacion;
        this.luz = luz;
    }

    public String toString() {
        return nombre+"(Recuperacion: "+recuperacion+", Iluminacion: "+luz+")"+caminos.toString();
    }

    public String getNombre() {
        return nombre;
    }

    public int getRecuperacion() {
        return recuperacion;
    }

    /**
     * Devuelve el camino con destino una posada especifica
     * Si hay más de uno que llevan  a la misma posada
     * se devuelve el primero que se encontro
     * @param destino nombre de la posada
     * @return camino encontrado
     */
    public Camino getCamino(int posicion) {
        return caminos.get(posicion);
    }

    public int getNumCaminos() {
        return caminos.size();
    }

    public Camino getCamino(Posada destino){
        for(Camino current: caminos){
            if(current.getDestino() == destino) {
                return current;
            }
        }
        return null;
    }

    public Luz getLuz() {
      return luz;
    }

    /**
     * Añade un camino a la posada
     * @param destino nombre de la posada
     * @return camino encontrado
     */
    public boolean addCamino(Camino camino) {
        if(nombre.equals(camino.getOrigen().getNombre()) && !(nombre.equals(camino.getDestino().getNombre()))){
            caminos.add(camino);
            return true;
        }
        return false;
    }

    /**
     * Modifica la luz de una posada
     * @param luz luz
     */
    public void cambiarLuz(Luz luz) {
      this.luz = luz;
    }
}
