package es.uam.eps.ads.p5.Agent;

import java.util.Arrays;
import java.util.LinkedList;

public class Cell {

    private LinkedList<IBasicAgent> agentes;

    public Cell() {
        agentes = new LinkedList<>();
    }

    public Cell(IBasicAgent ... agentes) {
        super();
        add(agentes);
    }

    public void add(IBasicAgent ... agentes) {
        this.agentes.addAll(Arrays.asList(agentes));
        for(IBasicAgent a : this.agentes) {
            a.setCell(this);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(agentes.size());
    }

    public Cell copy() {
        try {
            return (Cell)this.clone();
        }
        catch(CloneNotSupportedException e) {
            System.out.println("No se puede duplicar.");
        }
        return null;
    }
}
