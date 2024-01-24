import java.awt.*;
import java.io.PrintStream;

public class Scania extends Car {
    private int platformAngle;
    private final int increaseAmount = 1;
    public Scania(){
        super("Scania", 2, Color.GRAY, 200);
        platformAngle =0;
        stopEngine();
    }
    @Override
    public int getPlatformAngle() {
        return platformAngle;
    }

    @Override
    public void increasePlatformAngle() {
        if(getCurrentSpeed() == 0) {
            platformAngle = Math.min(70, platformAngle + increaseAmount);
        }
    }
    @Override
    public void decreasePlatformAngle() {
        if(getCurrentSpeed() == 0) {
            platformAngle= Math.max(0, platformAngle - increaseAmount);
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
        if(platformAngle==0) {
            incrementSpeed(speedInterval(amount));
        }
    }
    @Override
    public void brake(double amount){
        if(platformAngle==0) {
            decrementSpeed(speedInterval(amount));
        }
    }

    @Override
    protected double speedFactor(){
        return enginePower * 0.01;
    }





}
