import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.List;

import static org.junit.Assert.*;

public class ScaniaTest {
    static Saab95 saab;
    static Volvo240 volvo;

    static Scania transport;

    static RepairShop verkstad;


    @Before
    public void initializer() {
        saab = new Saab95();
        volvo = new Volvo240();
        transport = new Scania();
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
    }

    @Test
    public void decreasePlatformAngle() {
        transport.setPlatformAngle(50);
        int currentAngle = transport.getPlatformAngle();
        transport.decreasePlatformAngle();
        assertTrue(transport.getPlatformAngle() < currentAngle);
    }

    @Test
    public void testMaxMinAngle() {
        transport.setPlatformAngle(70);
        int currentAngle = transport.getPlatformAngle();
        transport.increasePlatformAngle();
        assertEquals(70, transport.getPlatformAngle());

        transport.setPlatformAngle(0);
        currentAngle = transport.getPlatformAngle();
        transport.decreasePlatformAngle();
        assertEquals(0, transport.getPlatformAngle());
    }

    @Test
    public void move_gas_withRampAngleAndGas() {
        int cordX = (int) transport.coordination.x;
        int cordY = (int) transport.coordination.y;

        transport.increasePlatformAngle();
        transport.gas(1);
        transport.move();
        assertEquals(cordX, (int) transport.coordination.x);
        assertEquals(cordY, (int) transport.coordination.y);
    }

    @Test
    public void move_gas_withoutRampAngle() {
        int cordX = (int) transport.coordination.x;
        int cordY = (int) transport.coordination.y;

        transport.setPlatformAngle(0);
        transport.gas(1);
        transport.move();
        transport.turnLeft();
        transport.move();

        assertTrue(cordX != (int) transport.coordination.x);
        assertTrue(cordY!= (int) transport.coordination.y);
    }
}