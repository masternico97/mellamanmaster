package es.uam.eps.ads.p3.src;

import java.io.*;

public class EjemploDeUsoSimulacion {
    public static void main(String[] args) {
        try {
            Simulacion s;

            s = new Simulacion("/home/nicolas/Escritorio/Practica 3-Adsoft/es/uam/eps/ads/p3/txt/POSADAS.txt", "/home/nicolas/Escritorio/Practica 3-Adsoft/es/uam/eps/ads/p3/txt/CAMINOS.txt", "/home/nicolas/Escritorio/Practica 3-Adsoft/es/uam/eps/ads/p3/txt/EXPLORADOR.txt");
            s = new Simulacion("/home/nicolas/Escritorio/Practica 3-Adsoft/es/uam/eps/ads/p3/txt/POSADAS.txt", "/home/nicolas/Escritorio/Practica 3-Adsoft/es/uam/eps/ads/p3/txt/CAMINOS.txt", "/home/nicolas/Escritorio/Practica 3-Adsoft/es/uam/eps/ads/p3/txt/EXPLORADOR2.txt");
        } catch(IOException e) { System.out.println ("Error en archivos"); }
    }
}
