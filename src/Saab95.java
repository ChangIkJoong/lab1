import java.awt.*;

public class Saab95 extends Car {

    private boolean turboOn;

    
    public Saab95(){
        super("saab95", 2, Color.red, 125);
	    turboOn = false;
        stopEngine();
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }


    @Override
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }
    
    // TODO fix this method according to lab pm
}
