import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

import static java.lang.System.out;

public class CarGame extends JPanel implements Runnable{
    final int originalTileSize=10;
    final int scale=1;
    final int tileSize = originalTileSize * scale;

    static int screenWidth = 1660;
    static int screenHeight = 720;

    Thread gameThread;

    KeyHandler keyH = new KeyHandler();

    int FPS = 60;

    //car default position = 100;

    int carX=screenWidth/2;
    int carY=screenHeight/2;

    double rotationV = Math.toRadians(90);

    int carLoadOffset=0;


    //TODO CAR ATTRIBUTE
    VolvoVAH300 car = new VolvoVAH300();
    RepairShop verkstad = new RepairShop("Chalmers", 40,List.of(new String[]{"Volvo240", "Saab95"}), new Point2D.Double(100, 200));
    RepairShop verkstad2 = new RepairShop("KTH", 40,List.of(new String[]{"Saab95"}), new Point2D.Double(800, 500));

    RepairShop verkstad3 = new RepairShop("LTH", 40,List.of(new String[]{"Volvo240"}), new Point2D.Double(1300, 100));
    List<RepairShop> workshopS = new ArrayList<>(List.of(verkstad, verkstad2, verkstad3));


    public CarGame () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.LIGHT_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread= new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        verkstad.carStorage.addAll(new ArrayList<>(List.of(new Saab95(), new Volvo240(), new Volvo240(), new Saab95(), new Volvo240(), new Volvo240(), new Saab95())));
        verkstad2.carStorage.addAll(new ArrayList<>(List.of(new Saab95(),new Saab95(), new Saab95(), new Saab95())));
        verkstad3.carStorage.addAll(new ArrayList<>(List.of(new Volvo240(), new Volvo240(), new Volvo240(),new Volvo240(), new Volvo240())));



        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {
            //System.out.println("Looooping");


            update();

            repaint();

            try {

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime=remainingTime/1000000;

                if(remainingTime<0) {
                    remainingTime=0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            }

            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void update(){

        if(keyH.upPressed) {
            car.gas(0.1);

        }
        else if(keyH.downPressed) {
            car.brake(0.1);

        }

        else if(keyH.leftPressed) {
            car.turnLeft();
            rotationV-=Math.toRadians(90);

            out.println("left");
            out.println(car.orientation);
            keyH.leftPressed=false;
        }
        else if(keyH.rightPressed) {
            car.turnRight();
            rotationV+=Math.toRadians(90);

            out.println("right");
            out.println(car.orientation);
            keyH.rightPressed=false;
        }

        else if(keyH.qPressed) {
            car.increasePlatformAngle();
        }

        else if(keyH.aPressed) {
            car.decreasePlatformAngle();
        }


        else if(keyH.nPressed && car.getCurrentSpeed()==0 &&(car.getPlatformAngle()==0 || !car.platform)) {
            for(RepairShop v : workshopS) {
                if(v.isWithinRectangle(car.coordination)) {
                    v.loadTransport(car);
                    if(carLoadOffset>=car.getCargoSize()*2) {
                        carLoadOffset=car.getCargoSize()*2;
                    }
                    break;
                }
            }
            keyH.nPressed=false;
        }

        else if(keyH.mPressed && car.getCurrentSpeed()==0 &&car.getPlatformAngle()==0) {
            for(RepairShop v : workshopS) {
                if(v.isWithinRectangle(car.coordination)) {
                    v.unloadTransport(car);
                    if (carLoadOffset < 0) {
                        carLoadOffset = 0;
                    }
                    break;
                }
            }
            keyH.mPressed=false;
        }

        else if(keyH.bPressed ) {
            //car.getCargoPos();
            keyH.bPressed=false;
        }

        carLoadOffset=car.getCargoSize()*2;


        car.move();

        if (car.coordination.x > screenWidth-10) {
            car.coordination.x =screenWidth-10;
        }
        if(car.coordination.x < 0) {
            car.coordination.x=0;
        }

        if (car.coordination.y > screenHeight-10) {
            car.coordination.y =screenHeight-10;
        }
        if(car.coordination.y < 0) {
            car.coordination.y=0;
        }

        carX= (int) car.coordination.x;
        carY= (int) car.coordination.y;

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        for(RepairShop v : workshopS) {
            drawWorkshop(g, v);
        }

        for(RepairShop v : workshopS) {
            drawWorkshopName(g, v);
        }


        drawCar(g);

        drawInstructions(g);
        drawCarRest(g);
        drawCarFirst(g);
        drawCarList(g);
        drawCarMyText(g);


        drawSpeed(g);
        drawPosition(g);

        if(car.getPlatformAngle()!=0) {
            drawPlatform(g);
            drawRamp(g);
        }




    }
    public void drawCarFirst(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.YELLOW);
        int offset = screenHeight-35;
        g2.fillRect((int) 5, (int) offset, 80,17);
    }

    public void drawCarRest(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.ORANGE);
        int offset = screenHeight-35;
        g2.fillRect((int) 5, (int) offset, 80,-120);
    }

    public void drawCarMyText(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        int offset = screenHeight-160;
        g2.drawString("CARGO: "+ car.getCargoSize(), 5 , offset);
    }

    public void drawCarList(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        int offset = (int) screenHeight-20 ;
        for (Car carM : car.getLoadList()) {
            g2.drawString(carM.modelName, 10 , offset);
            offset -=17;
        }
    }




    public void drawWorkshopName(Graphics g, RepairShop v) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.DARK_GRAY);
        int offset = (int) v.coordination.y +5;
        for (Car carM : v.carStorage) {
            g2.drawString(carM.modelName, (int) v.coordination.x-70,offset);
            offset +=17;
        }
    }
    public void drawWorkshop(Graphics g, RepairShop v) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect((int) v.coordination.x, (int) v.coordination.y, 100,50);
    }

    public void drawCar(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Point carBodySize=new Point(20, 10);
        Point carWheelSize=new Point(5, 3);

        AffineTransform transform = new AffineTransform();
        AffineTransform oldTransform = g2.getTransform();

        transform.rotate(rotationV, carX+(carBodySize.x), carY+(carBodySize.y/2));

        g2.setTransform(transform);

        // body
        g2.setColor(car.getColor());
        g2.fillRect(carX, carY, carBodySize.x,carBodySize.y);

        // wheels
        g2.setColor(Color.GRAY);
        g2.fillRect(carX, carY + carBodySize.y , carWheelSize.x, carWheelSize.y); // Back right wheel
        g2.fillRect(carX, carY- carWheelSize.y, carWheelSize.x, carWheelSize.y); // back left wheel

        g2.fillRect(carX + carBodySize.x - carWheelSize.x, carY + carBodySize.y , carWheelSize.x, carWheelSize.y); // Front right wheel
        g2.fillRect(carX + carBodySize.x - carWheelSize.x, carY - carWheelSize.y, carWheelSize.x, carWheelSize.y); // Front left wheel

        g2.setTransform(oldTransform);


        //g2.dispose();
    }

    public void drawSpeed(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.black);
        g2.drawString( "SPEEDOMETER: "+ String.format("%.1f", car.getCurrentSpeed()),20, 20);
        //g2.dispose();
    }

    public void drawPosition(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.black);
        g2.drawString( "POS: X ["+ String.format("%.1f",car.coordination.x) + "] : [" + String.format("%.1f",car.coordination.y) + "]",20, 35);
        //g2.dispose();
    }

    public void drawInstructions(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.drawString( " Q - RAMP UP : A - RAMP DOWN : N - ADD LOAD : M - REMOVE LOAD" ,screenWidth/2, screenHeight-20);
        //g2.dispose();
    }

    public void drawPlatform(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.black);
        g2.drawString( "PLATFORM: "+ car.getPlatformAngle(),20, 50);
        //g2.dispose();
    }



    public void drawLoad(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        AffineTransform transform = new AffineTransform();
        AffineTransform oldTransform = g2.getTransform();

        Point carBodySize=new Point(20, 10);

        transform.rotate(rotationV, carX+(carBodySize.x), carY+((double) carBodySize.y /2));

        g2.setTransform(transform);

        g2.setColor(Color.red);
        g2.fillRect(carX, carY, + carLoadOffset, tileSize);
        //g2.dispose();

        g2.setTransform(oldTransform);


    }

    public void drawRamp(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        AffineTransform transform = new AffineTransform();
        AffineTransform oldTransform = g2.getTransform();

        Point carBodySize=new Point(20, 10);

        transform.rotate(rotationV, carX+(carBodySize.x), carY+((double) carBodySize.y /2));

        g2.setTransform(transform);

        g2.setColor(Color.BLUE);
        g2.fillRect(carX, carY, 4, tileSize);
        //g2.dispose();

        g2.setTransform(oldTransform);


    }
}
