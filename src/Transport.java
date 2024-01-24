import java.awt.*;
import java.util.Stack;

public class Transport extends Truck {
    public final Stack<Car> load = new Stack<>();
    private static final int loadCapacity = 8;
    public Transport(){
        super("VAH 300",2, Color.GREEN, 200);
    }

    protected void addCar(Car newCar) {
        if(getPlatformAngle()==0 && currentSpeed==0 && load.size() < loadCapacity ) {
            newCar.coordination=this.coordination;
            load.push(newCar);
        }
    }

    public void removeCar() {
        if (getPlatformAngle() == 0 && currentSpeed == 0 && !load.isEmpty()) {
            load.pop();
        }
    }

    public void getLoadPos() {
        for(Car c : load) {
            System.out.println("CAR: " + c.modelName +" ---- POSITION: " + c.coordination);
        }
    }

    public int getLoadSize() {
        return load.size();
    }


    public Stack<Car> getLoadList() {
        return load;
    }







}
