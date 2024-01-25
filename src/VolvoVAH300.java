import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class VolvoVAH300 extends Truck {
    protected final Deque<Car> cargo = new ArrayDeque<>();

    protected boolean platform = false;
    private static final int loadCapacity = 8;

    public VolvoVAH300(){
        super("VolvoVAH300",2, Color.GREEN, 200);
    }

    @Override
    protected void increasePlatformAngle() {
        if(getCurrentSpeed() == 0) {
            setPlatformAngle(70);
            if (getPlatformAngle()==70) {
                platform = true;
            }
        }

    }
     @Override
        protected void decreasePlatformAngle() {
            if(getCurrentSpeed() == 0) {
                setPlatformAngle(0);
                if (getPlatformAngle()==0) {
                    platform = false;
                }
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

    //**FOR TESTING AND GAME
/*
    public void getCargoPos() {
        for(Car c : cargo) {
            System.out.println("CAR: " + c.modelName +" , POSITION: X :" + String.format("%.1f", c.coordination.x) + ", Y "+ String.format("%.1f", c.coordination.y));
        }
    }
*/
    public int getCargoSize() {
        return cargo.size();
    }

    public Deque<Car> getLoadList() {
        return cargo;
    }
}
