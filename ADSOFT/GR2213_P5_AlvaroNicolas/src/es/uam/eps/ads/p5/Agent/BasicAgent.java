package es.uam.eps.ads.p5.Agent;

public class BasicAgent implements IBasicAgent, Cloneable {
    protected String tipo;
    protected Cell cell;

    public BasicAgent(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public Cell cell() {
        return cell;
    }

    @Override
    public IBasicAgent copy() {
        try {
             return (BasicAgent)this.clone();
        }
        catch(CloneNotSupportedException e) {
            System.out.println("No se puede duplicar.");
        }
        return null;
    }

    @Override
    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
