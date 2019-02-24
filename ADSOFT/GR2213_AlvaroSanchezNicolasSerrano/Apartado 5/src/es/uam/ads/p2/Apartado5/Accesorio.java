package es.uam.ads.p2.Apartado5;

/**
 * Clase accesorio que se incluye en los
 * carritos
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class Accesorio {
    private String marca;
    private String modelo;
    private String color;
    private String tipo;

    public Accesorio(String marca, String modelo, String color, String tipo){
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.tipo = tipo;
    }
}
