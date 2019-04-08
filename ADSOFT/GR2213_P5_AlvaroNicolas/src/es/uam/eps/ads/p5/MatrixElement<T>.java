package es.uam.eps.ads.p5;

/**
 * Interfaz del elemento de la matriz generico de la practica
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */
public class MatrixElement<T> {
    private int fila;
    private int columna;
    private T elemento;

    public MatrixElement(int fila, int columna, T elemento){
        this.fila = fila;
        this.columna = columna;
        this.elemento = elemento;
    }

    public int getI(){
        return fila;
    }

    public int getJ(){
        return columna;
    }

    public T getElement(){
        return elemento;
    }

    public void setElement(T element){
        elemento = T;
    }

    @Override
    public String toString(){
        return elemento.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if(!(obj instanceof MatrixElement<T>)){
            return false;
        }
        MatrixElement<T> comparing = (MatrixElement<T>)obj;
        return  comparing.getI() == this.fila &&
                comparing.getJ() == this.columna;
     }
}
