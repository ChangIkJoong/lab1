import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class BilVerkstad {
    private final String name;
    private final int maxCarStorage;
    private final List<String> acceptedCars;
    private final List<Car> carStorage;

    public Point2D.Double coordination = new Point2D.Double();



    public BilVerkstad(String name, int storage, int x, int y, List<String> acceptedCars) {
        this.acceptedCars = acceptedCars;
        this.name=name;
        this.maxCarStorage = storage;
        this.carStorage = new ArrayList<>();
        this.coordination.x= x;
        this.coordination.y=y;
    }

    private boolean validateModel(Car car) {
        return acceptedCars.contains(car.modelName);
    }

    protected void enterCar(Car car) {
    }







}
