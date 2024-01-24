import java.awt.*;
import java.util.Stack;

public class CarCarrier extends Scania {
    private final Stack<Car> load = new Stack<>();
    public CarCarrier(){
        modelName="VAH 300";
        setColor(Color.black);
        int platformAngle = 0;
        stopEngine();
    }
    @Override
    protected void addCar(Car newCar) {
        if(getPlatformAngle()==0 && currentSpeed==0 && load.size() < 10 ) {
            newCar.coordination=this.coordination;
            load.push(newCar);
        }
    }
    @Override
    protected void removeCar() {
        if(getPlatformAngle()==0 && currentSpeed==0 && !load.isEmpty()) {
            load.pop();
        }
    }
    @Override
    public void getLoadPos() {
        for(Car c : load) {
            System.out.println("CAR: " + c.modelName +" ---- POSITION: " + c.coordination);
        }
    }

    @Override
    public int getLoadSize() {
        return load.size();
    }

    @Override
    public Stack<Car> getLoadList() {
        return load;
    }








}
