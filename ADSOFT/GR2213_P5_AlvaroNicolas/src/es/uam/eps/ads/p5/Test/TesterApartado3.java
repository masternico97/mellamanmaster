package es.uam.eps.ads.p5.Test;

import static org.junit.Assert.*;

import es.uam.eps.ads.p5.Agent.BasicAgent;
import es.uam.eps.ads.p5.Agent.BasicSimulator;
import org.junit.Before;
import org.junit.Test;

public class TesterApartado3 {

    BasicSimulator s;
    BasicAgent random;
    BasicAgent outer;

    @Before
    public void setUp() {
        s = new BasicSimulator(10, 10);
        random = new BasicAgent("random");
        outer = new BasicAgent("outer");
    }

    /**
     * Test del Apartado 3.
     */
    @Test
    public void testApartado3() {
        s.create(random, 10, 5, 5);
        s.create(outer, 10, 7, 7);
        s.run(2);

        String auxChain =
                "++++++++++++++++++++++++++++++++++++++++++++++\n" +
                "Time = 0\n" +
                "\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\n" +
                "\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\n" +
                "\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\n" +
                "\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\n" +
                "\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\n" +
                "\t0|\t0|\t0|\t0|\t0|\t10|\t0|\t0|\t0|\t0|\n" +
                "\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\n" +
                "\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t10|\t0|\t0|\n" +
                "\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\n" +
                "\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\t0|\n";

        assertEquals(s.toString(), auxChain);
    }
}
