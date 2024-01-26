import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.List;

import static org.junit.Assert.*;

public class RepairShopTest {
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
        verkstad.carStorage.add(volvo);
        verkstad.loadTransport(transport);

        assertTrue(transport.getLoadList().contains(volvo));
    }

    @Test
    public void loadedTransportPositionValidate() {
        transport.decreasePlatformAngle();
        verkstad.carStorage.add(volvo);
        int cordX= (int) verkstad.carStorage.getLast().coordination.x;
        int cordY= (int) verkstad.carStorage.getLast().coordination.y;

        verkstad.loadTransport(transport);

        transport.increasePlatformAngle();
        transport.gas(1);
        transport.move();
        transport.turnLeft();
        transport.move();

        assert transport.cargo.peek() != null;
        assertNotEquals(cordX, transport.cargo.peek().coordination.x);
        assert transport.cargo.peek() != null;
        assertNotEquals(cordY, transport.cargo.peek().coordination.y);
    }

    @Test
    public void isWithinRadius() {
        transport.coordination = new Point2D.Double(100,200);
        verkstad.coordination=new Point2D.Double(100,200);
        assertTrue(verkstad.isWithinRectangle(transport.coordination));
    }
}
