package es.uam.eps.ads.p5;

public class BasicAgent implements IBasicAgent, Cloneable {
    private String tipo;
    private Cell cell;

    public BasicAgent(String tipo) {
        this.tipo = tipo;
    }

    public Cell cell() {
        return cell;
    }

    public IBasicAgent copy() {
        try {
             return (BasicAgent)this.clone();
        }
        catch(CloneNotSupportedException e) {
            System.out.println("No se puede duplicar.");
        }
        return null;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
