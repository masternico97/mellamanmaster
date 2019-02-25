package es.uam.eps.ads.p3;

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

    public Posada(String nombre) {
        this.nombre = nombre;
        this.recuperacion = 2;
    }

    public Posada(String nombre, int recuperacion) {
        this.nombre = nombre;
        this.recuperacion = recuperacion;
    }

    public String toString() {
        return nombre+"("+recuperacion+")"+caminos.toString();
    }

    public String getNombre() {
        return nombre;
    }

    public int getRecuperacion() {
        return recuperacion;
    }

    public Camino getCamino(int posicion) {
        return caminos.get(posicion);
    }

    public int getNumCaminos() {
        return caminos.size();
    }

    public Camino getCamino(Posada destino){
        for(Camino current: caminos){
            if(current.getDestino() == destino)
                return current;
        }
        return null;
    }

    public boolean addCamino(Camino camino) {
        if(nombre.equals(getOrigen(camino)) && !(nombre.equals(getDestino(camino)))){
            caminos.add(camino);
            return true;
        }
        return false;
    }
