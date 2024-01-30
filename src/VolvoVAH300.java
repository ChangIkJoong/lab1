import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class VolvoVAH300 extends Truck {
    protected final Deque<Car> cargo = new ArrayDeque<>();
    protected boolean platform = false;
    protected static final int loadCapacity = 8;

    public VolvoVAH300(){
        super("VolvoVAH300",2, Color.GREEN, 200);
    }

    @Override
    public void increasePlatformAngle() {
        if(getCurrentSpeed() == 0) {
            setPlatformAngle(70);
            platform = getPlatformAngle() == 70;
        }

    }
     @Override
     public void decreasePlatformAngle() {
            if(getCurrentSpeed() == 0) {
                setPlatformAngle(0);
                platform = getPlatformAngle() == 70;
            }
        }

        @Override
        public void move() {
            if(platform) {
                double xTraverse = (currentSpeed * orientation.getX());
                double yTraverse = (currentSpeed * orientation.getY());
                coordination.y = (yTraverse + coordination.y);
                coordination.x = (xTraverse + coordination.x);
            }
        }
        @Override
        public void gas(double amount) {
            if (platform) {
                incrementSpeed(speedInterval(amount));
            }
        }

    protected void addCar(Car newCar) {
        if(!platform && currentSpeed==0 && cargo.size() < loadCapacity ) {
            newCar.coordination=this.coordination;
            cargo.push(newCar);
        }
    }

    protected Car removeCar() {
        Car x = null;
        if (!platform && currentSpeed == 0 && !cargo.isEmpty()) {
            x = cargo.pop();
        }
        return x;
    }

    //TODO För att spelet ska kunna Testas, fungera och plocka samt avlasta grejjer...
    public int getCargoSize() {
        return cargo.size();
    }

    public Deque<Car> getLoadList() {
        return cargo;
    }
}
