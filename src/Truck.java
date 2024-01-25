import java.awt.*;

public abstract class Truck extends Car {
    protected int platformAngle;
    protected final int increaseAmount = 1;

    public Truck(String model, int nrDoors, Color color, double enginePower) {
        super(model, nrDoors, color, enginePower);
    }

    public int getPlatformAngle() {
        return platformAngle;
    }


    public void setPlatformAngle(int n) {
        platformAngle=n;
    }


    public void increasePlatformAngle() {
        if(getCurrentSpeed() == 0) {
            platformAngle = Math.min(70, platformAngle + increaseAmount);
            setPlatformAngle(70);
        }
    }

    public void decreasePlatformAngle() {
        if(getCurrentSpeed() == 0) {
            platformAngle= Math.max(0, platformAngle - increaseAmount);
            setPlatformAngle(0);
        }
    }
    @Override
    public void move() {
        if(platformAngle==0) {
            double xTraverse = (currentSpeed * orientation.getX());
            double yTraverse = (currentSpeed * orientation.getY());
            coordination.y = (yTraverse + coordination.y);
            coordination.x = (xTraverse + coordination.x);
        }
    }
    @Override
    public void gas(double amount){
        if(getPlatformAngle()==0) {
            incrementSpeed(speedInterval(amount));
        }
    }
}
