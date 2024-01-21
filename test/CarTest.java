import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CarTest {
    static Saab95 saab;
    static Volvo240 volvo;

    @Before
    public void initializer() {
        saab = new Saab95();
        volvo = new Volvo240();
    }


    @Test
    public void testGas () {

        saab.gas(1);
        assertTrue(saab.getCurrentSpeed()>0);
    }


    @Test
    public void testBrake () {

        saab.currentSpeed=1;
        assertTrue(saab.getCurrentSpeed() >0);

        saab.brake(1);
        assertEquals(0, saab.getCurrentSpeed(), 0.0);


    }


    @Test
    public void testMaxSpeed () {
        saab.currentSpeed=saab.getEnginePower();
        saab.gas(1);

        assertEquals(saab.getCurrentSpeed(), saab.getEnginePower(), 0.0);

    }


    @Test
    public void testMinSpeed () {
        volvo.currentSpeed=0;
        volvo.brake(1);

        assertEquals(0, saab.getCurrentSpeed(), 0.0);
    }


    @Test
    public void testTurn () {
        saab.orientation= new Point(0,1);

        saab.turnLeft();
        assertEquals(saab.orientation, new Point(1,0));
        saab.turnLeft();
        assertEquals(saab.orientation, new Point(0,-1));
        saab.turnLeft();
        assertEquals(saab.orientation, new Point(-1,0));

        saab.turnRight();
        assertEquals(saab.orientation, new Point(0,-1));
        saab.turnRight();
        assertEquals(saab.orientation, new Point(1,0));
        saab.turnRight();
        assertEquals(saab.orientation, new Point(0,1));


    }


    @Test
    public void testMove () {
        saab.coordination=new Point2D.Double(0,0);
        saab.currentSpeed=saab.getEnginePower();

        saab.move();
        saab.turnRight();
        saab.move();


        assertTrue(saab.coordination.y > 0 && saab.coordination.x < 0);

    }
    @Test
    public void testTurbo () {
        saab.coordination=new Point2D.Double(0,0);
        saab.orientation=new Point(1,0);

        saab.gas(1);

        saab.setTurboOff();
        saab.move();
        double noTurboX = saab.coordination.x;

        saab.stopEngine();
        saab.coordination=new Point2D.Double(0,0);
        saab.setTurboOn();
        saab.gas(1);

        saab.move();
        assertTrue(saab.coordination.x > noTurboX);




    }

    @Test
    public void testColor() {
        Color currentColor = volvo.getColor();
        volvo.setColor(Color.red);
        assertTrue(currentColor!=volvo.getColor() && volvo.getColor()==Color.red);

    }
    @Test
    public void testDoors() {
        assertTrue(saab.getNrDoors()==2 && volvo.getNrDoors()==4);
    }




}
