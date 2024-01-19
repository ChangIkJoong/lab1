import java.awt.*;

public interface CarMethods {

    public int getNrDoors();

    public double getEnginePower();

    public double getCurrentSpeed();

    public Color getColor();

    public void setColor(Color clr);

    public void startEngine();

    void stopEngine();
    void incrementSpeed(double amount);
    void decrementSpeed(double amount);

    void gas(double amount);
    void brake(double amount);

    double speedFactor();


}
