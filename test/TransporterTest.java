import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.List;

import static org.junit.Assert.*;

public class TransporterTest {
    static Saab95 saab;
    static Volvo240 volvo;

    static VolvoVAH300 transport;

    static RepairShop verkstad;


    @Before
    public void initializer() {
        saab = new Saab95();
        volvo = new Volvo240();
        transport = new VolvoVAH300();
        verkstad = new RepairShop("hej", 5, List.of(new String[]{"Volvo240", "Saab95"}), new Point2D.Double(100,200));
    }

    @Test
    public void getPlatformAngle() {
        assertEquals(0, transport.getPlatformAngle());
    }

    @Test
    public void setPlatformAngle() {
        transport.setPlatformAngle(10);
        assertEquals(10, transport.getPlatformAngle());
    }

    @Test
    public void increasePlatformAngle() {
        int currentAngle = transport.getPlatformAngle();
        transport.increasePlatformAngle();
        assertTrue(transport.getPlatformAngle() > currentAngle);
        assertEquals(true, transport.platform);
    }

    @Test
    public void decreasePlatformAngle() {
        transport.setPlatformAngle(50);
        int currentAngle = transport.getPlatformAngle();
        transport.decreasePlatformAngle();
        assertEquals(false, transport.platform);

    }

    @Test
    public void move_gas_withRampAngleAndGas() {
        int cordX = (int) transport.coordination.x;
        int cordY = (int) transport.coordination.y;

        transport.increasePlatformAngle();
        transport.gas(1);
        transport.move();
        transport.turnLeft();
        transport.move();
        assertNotEquals(cordX, (int) transport.coordination.x);
        assertNotEquals(cordY, (int) transport.coordination.y);
    }

    @Test
    public void move_gas_withoutRampAngle() {
        int cordX = (int) transport.coordination.x;
        int cordY = (int) transport.coordination.y;

        transport.decreasePlatformAngle();
        transport.gas(1);
        transport.move();
        transport.turnLeft();
        transport.move();

        assertTrue(cordX == (int) transport.coordination.x);
        assertTrue(cordY == (int) transport.coordination.y);
    }

    @Test
    public void addCar() {
        assertEquals(0, transport.getCargoSize());
        transport.addCar(volvo);
        assert transport.cargo.peek() != null;
        assertEquals(1, transport.getCargoSize());
    }


    @Test
    public void CargoPositionCheck() {
        transport.addCar(saab);
        transport.move();
        assert transport.cargo.peek() != null;
        assertEquals(transport.coordination, transport.cargo.peek().coordination);
    }

    @Test
    public void removeCar() {
        transport.addCar(saab);
        transport.addCar(saab);
        transport.addCar(saab);
        assertEquals(3, transport.getCargoSize());

        Car x =transport.removeCar();
        assertEquals(2, transport.getCargoSize());
        assertNotNull(x);
    }

    @Test
    public void addCarWithAngle() {
        transport.increasePlatformAngle();
        transport.addCar(saab);
        assertEquals(0, transport.getCargoSize());
    }

    @Test
    public void removeCarWithAngle() {
        transport.addCar(saab);
        transport.increasePlatformAngle();
        Car x = transport.removeCar();
        assertEquals(1, transport.getCargoSize());
        assertNull(x);
    }

}
