import java.awt.*;

public class Scania extends Truck {
    protected int platformAngle;
    public Scania(){
        super("Scania", 2, Color.cyan, 200);
        platformAngle =0;
        stopEngine();
    }
}
