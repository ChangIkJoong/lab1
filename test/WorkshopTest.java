import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.List;

import static org.junit.Assert.*;

public class WorkshopTest {
    static Saab95 saab;
    static Volvo240 volvo;

    static VolvoVAH300 transport;

    static RepairShop verkstad;


    @Before
    public void initializer() {
        saab = new Saab95();
        volvo = new Volvo240();
        transport = new VolvoVAH300();
        verkstad = new RepairShop("test", 5, List.of(new String[]{"Volvo240"}), new Point2D.Double(100,200));
    }

    @Test
    public void unloadTransport() {
        verkstad.carStorage.clear();
        transport.addCar(saab);
        verkstad.unloadTransport(transport);
        assertFalse(verkstad.carStorage.contains(saab));


        transport.addCar(volvo);
        verkstad.unloadTransport(transport);
        assertTrue(verkstad.carStorage.contains(volvo));
    }
    @Test
    public void loadTransport() {
        verkstad.carStorage.clear();
        transport.addCar(volvo);
        verkstad.loadTransport(transport);

        assertTrue(transport.getLoadList().contains(volvo));
    }
}
