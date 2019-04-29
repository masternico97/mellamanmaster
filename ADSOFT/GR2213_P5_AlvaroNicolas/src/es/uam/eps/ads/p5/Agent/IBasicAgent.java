package es.uam.eps.ads.p5.Agent;

public interface IBasicAgent {
    Cell cell();
    IBasicAgent copy();
    void setCell(Cell cell); //README
}
