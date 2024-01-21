import java.awt.*;

public interface CarMethods {

     int getNrDoors();

     double getEnginePower();

     double getCurrentSpeed();

     Color getColor();

     void setColor(Color clr);

     void startEngine();

    void stopEngine();
    void incrementSpeed(double amount);
    void decrementSpeed(double amount);

    void gas(double amount);
    void brake(double amount);

    double speedFactor();


}
