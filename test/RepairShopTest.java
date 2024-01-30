import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.List;

import static org.junit.Assert.*;

public class RepairShopTest {
    static Saab95 saab;
    static Volvo240 volvo;

    static VolvoVAH300 transport;

    static RepairShop<Car> verkstad;
    static RepairShop<Volvo240> verkstadVolvo;


    @Before
    public void initializer() {
        saab = new Saab95();
        volvo = new Volvo240();
        transport = new VolvoVAH300();
        verkstad = new RepairShop<>(5,new Point2D.Double(100,200));
        verkstadVolvo = new RepairShop<>( 5, new Point2D.Double(100,200));
    }

    @Test
    public void addCarWrong() {
        verkstadVolvo.carStorage.clear();
        verkstadVolvo.addCar(volvo);
        //verkstadVolvo.addCar(saab);
    }

    @Test
    public void addCar() {
        verkstad.carStorage.clear();
        verkstad.addCar(volvo);
        verkstad.addCar(saab);
        assertNotEquals(0, verkstad.carStorage.size());
    }
    @Test
    public void removeCar() {
        verkstad.addCar(volvo);
        assertEquals(1, verkstad.carStorage.size());

        verkstad.RemoveCar();
        assertEquals(0, verkstad.carStorage.size());
    }
}
