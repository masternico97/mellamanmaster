package es.uam.ads.p3;

/**
 * Clase Explorador
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de prácticas: 2213
 */
public class Explorador {
    private String nombre;
    private int energia;
    private Posada posicion;

    public Explorador(String nombre, int energia, Posada posicion) {
        this.nombre = nombre;
        this.energia = energia;
        this.posicion = posicion;
    }

    public getNombre() {
        return nombre;
    }

    public getEnergia() {
        return energia;
    }

    public getPosicion(){
        return posicion;
    }

    public toString String() {
        return +getNombre()+" (e:"+getEnergia()+") en "+getPosicion().getNombre();
    }

    public boolean recorre(Camino camino) {
        for(int i = 0; i < getPosicion().getNumCaminos(); i++) {
            if(camino == getPosicion().getCamino(i)) {
                if(puedeRecorrerCamino(camino) && puedeAlojarseEn(camino.getDestino())){
                  this.energia -= camino.getCosteReal();
                  this.energia += camino.getDestino().getRecuperacion();
                  this.posicion = camino.getDestino();
                  return true;
                }
            }
        }
        return false;
    }

    private boolean recorre(Posada... posadas) {
        Camino c;
        boolean error = false;
        for(Posada p: posadas) {
            c = getCamino(p);
            if(recorre(c) == false) {
                error = true;
            }
        }

        if(error == true) {
          return false;
        }
        return true;
    }

    private boolean puedeRecorrerCamino(Camino camino) {
        if(this.energia > camino.getCosteReal()){
            return true;
        }
        return false;
    }

    private boolean puedeAlojarseEn(Posada posada) {
        return true;
    }
}
