import static java.lang.System.out;

public class MyCarApp {
    public static void main(String[] args) {
        Car saab = new Saab95();
        Car volvo = new Volvo240();


        int x =0;
        saab.gas(1);
            out.println("orientation ORIGIN: "+saab.orientation + "\n");

        while( x < 16) {
            //out.println(saab.coordination);
            //out.println(saab.orientation);
            //saab.move();
            //out.println(saab.coordination);
            saab.turnRight();
            saab.move();
            out.println("orientation: " + saab.orientation);
            out.println(("coordinates: " + saab.coordination + "\n"));
            saab.gas(0.5);

            x++;
        }
    }
}


