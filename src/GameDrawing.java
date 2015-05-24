import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Julia on 24.05.2015.
 */
public class GameDrawing extends JFrame {
    private static GamePanel gp;
    private byte movePlayer;
    public void GameDrawing() {
        gp=new GamePanel();
        add(gp);
        KeyListener kl=new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String keyText = (e.getKeyText(e.getKeyCode()));

                if (keyText.equals("S")) {
                    movePlayer=2;
                }
                if (keyText.equals("W")) {
                    movePlayer=0;
                }
                if (keyText.equals("A")) {
                    movePlayer=3;
                }
                if (keyText.equals("D")) {
                    movePlayer=1;
                }
            }
        };
        addKeyListener(kl);
    }
    public void draw(byte[] gameState) {
        gp.setGameState(gameState);
        gp.repaint();
    }
    public byte getMovePlayer() {
        return movePlayer;
    }
}
