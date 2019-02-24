package es.uam.ads.p2.Apartado5;

import java.util.*;

/**
 * Clase registro que nos permite obtener los
 * datos de los registros de cuna y silla
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de prácticas: 2213
 */
public class Registro {
    private String actuacion;
    private Date fecha = new Date();

    /**
     * Constructor de la clase Registro
     * @param actuacion = actuacion realizada
     */
    public Registro(String actuacion) {
        this.actuacion = actuacion;
    }

    public String getActuacion() {
        return actuacion;
    }

    public Date getFecha() {
        return fecha;
    }
}
