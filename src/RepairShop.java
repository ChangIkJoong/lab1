import java.awt.geom.Point2D;
import java.util.*;

public class RepairShop {
    private final String name;
    protected final int maxStorageCapacity;
    protected final List<String> acceptedCars;
    protected final List<Car> carStorage;

    public Point2D.Double coordination;



    public RepairShop(String name, int capacity, List<String> acceptedCars, Point2D.Double coords) {
        this.acceptedCars=acceptedCars;
        this.name=name;
        this.maxStorageCapacity = capacity;
        this.carStorage = new ArrayList<>();
        this.coordination=coords;
    }

    private boolean validateModel(Car car) {
        return acceptedCars.contains(car.modelName);
    }

    protected void unloadTransport(VolvoVAH300 transport) {
        if (transport.cargo.peek() != null && validateModel((transport.cargo.peek())) && carStorage.size() < maxStorageCapacity) {
                Car newCar = transport.removeCar();
                newCar.coordination = this.coordination;
                carStorage.add(newCar);
        }
    }

    protected void loadTransport(VolvoVAH300 transport) {
        if(!carStorage.isEmpty()) {
            Car newCar = carStorage.removeLast();
            newCar.coordination = transport.coordination;
            transport.addCar(newCar);
        }
    }

    //TODO För att spelet ska kunna fungera och plocka samt avlasta grejjer...

    public boolean isWithinRectangle(Point2D.Double other) {
        Point2D.Double topLeft = new Point2D.Double(coordination.x, coordination.y);
        Point2D.Double bottomRight = new Point2D.Double(coordination.x + 100, coordination.y + 50);

        return other.x >= topLeft.x && other.x <= bottomRight.x &&
                other.y >= topLeft.y && other.y <= bottomRight.y;
    }
}
