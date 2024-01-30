import java.awt.geom.Point2D;
import java.util.*;

public class RepairShop<T> {
    protected final List<T> carStorage;
    public Point2D.Double coordination;
    private final int capacity;


    public RepairShop(int capacity, Point2D.Double coords) {
        this.capacity=capacity;
        this.carStorage = new ArrayList<>(capacity);
        this.coordination=coords;
    }

    protected void addCar(T newCar) {
            if(carStorage.size() < capacity) {
                carStorage.add((newCar));
        }
    }

    protected T RemoveCar() {
        return carStorage.removeLast();
    }

    //TODO För att spelet ska kunna fungera och plocka samt avlasta grejjer...
/*
    public boolean isWithinRectangle(Point2D.Double other) {
        Point2D.Double topLeft = new Point2D.Double(coordination.x-10, coordination.y-10);
        Point2D.Double bottomRight = new Point2D.Double(coordination.x + 110, coordination.y + 60);

        return other.x >= topLeft.x && other.x <= bottomRight.x &&
                other.y >= topLeft.y && other.y <= bottomRight.y;
    }

    private boolean validateModel(Car car) {
        return carStorage.getLast().modelName.equals(car.modelName);
    }

 */
}
