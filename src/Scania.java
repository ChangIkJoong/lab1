import java.awt.*;
import java.io.PrintStream;
import java.util.Stack;

public class Scania extends Truck {
    protected int platformAngle;
    public Scania(){
        super("Scania", 2, Color.cyan, 200);
        platformAngle =0;
        stopEngine();
    }
}
