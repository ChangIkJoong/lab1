import javax.swing.*;
import java.awt.*;

import static java.lang.System.out;

public class CarGame extends JPanel implements Runnable{
    final int originalTileSize=10;
    final int scale=1;
    final int tileSize = originalTileSize * scale;

    final int screenWidth = 1660;
    final int screenHeight = 720;

    Thread gameThread;

    KeyHandler keyH = new KeyHandler();

    int FPS = 60;

    //car default position = 100;

    int carX=screenWidth/2;
    int carY=screenHeight/2;


    Car car = new Scania();

    public CarGame () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
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

            out.println("left");
            out.println(car.orientation);
            keyH.leftPressed=false;
        }
        else if(keyH.rightPressed) {
            car.turnRight();

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
        draw(g);
        draw2(g);
        draw3(g);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(car.getColor());
        g2.fillRect(carX, carY, tileSize,tileSize);

        //g2.dispose();
    }

    public void draw2(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.black);
        g2.drawString( "SPEEDOMETER: "+ String.format("%.1f", car.getCurrentSpeed()),20, 20);
        //g2.dispose();
    }

    public void draw3(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.black);
        g2.drawString( "PLATFORM: "+ String.valueOf(car.getPlatformAngle()),20, 35);
        //g2.dispose();
    }
}
