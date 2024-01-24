import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed,leftPressed,rightPressed, qPressed, aPressed ,nPressed, mPressed, bPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP) {
            upPressed=true;

        }
        if(code == KeyEvent.VK_DOWN) {
            downPressed=true;

        }
        if(code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT) {
            rightPressed=true;
        }
//PLATFORM
        if(code == KeyEvent.VK_Q) {
            qPressed=true;
        }
        if(code == KeyEvent.VK_A) {
            aPressed=true;
        }
//LOAD, n = add, m = remove, b = getPos
        if(code == KeyEvent.VK_N) {
            nPressed=true;
        }
        if(code == KeyEvent.VK_M) {
            mPressed=true;
        }

        if(code == KeyEvent.VK_B) {
            bPressed=true;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();


        if(code == KeyEvent.VK_UP) {
            upPressed=false;

        }
        if(code == KeyEvent.VK_DOWN) {
            downPressed=false;

        }
        if(code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT) {
            rightPressed=false;

        }
        //PLATFORM
        if(code == KeyEvent.VK_Q) {
            qPressed=false;
        }
        if(code == KeyEvent.VK_A) {
            aPressed=false;
        }
        //LOAD, n = add, m = remove, b = getPos
        if(code == KeyEvent.VK_N) {
            nPressed=false;
        }
        if(code == KeyEvent.VK_M) {
            mPressed=false;
        }
        if(code == KeyEvent.VK_B) {
            bPressed=false;
        }

    }
}
