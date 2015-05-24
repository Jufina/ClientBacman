import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Julia on 24.05.2015.
 */
public class GamePanel extends JPanel {
    private byte[][] map;
    private byte[] teamScores;
    private JLabel scoreTeam;

    GamePanel() {
        scoreTeam=new JLabel("0:0");
        Font fnt=new Font("Arial", Font.BOLD|Font.ITALIC, 30);
        scoreTeam.setFont(fnt);
        add(scoreTeam);
        map = new byte[20][20];
        teamScores = new byte[2];
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int frW,frH;
        frW = getWidth();
        frH= getHeight();

        int sizeBlockX=frW/20;
        int sizeBlockY=frH/20;

        for(int y=0; y<20; y++) {
            for(int x=0; x<20; x++) {
                switch(map[y][x]) {
                    case 5:
                        g2.setColor(Color.BLACK);
                        Rectangle wall=new Rectangle(x*sizeBlockX,y*sizeBlockY,sizeBlockX,sizeBlockY);
                        g2.draw(wall);
                        g2.fill(wall);
                        break;
                    case 6:
                        g2.setColor(Color.ORANGE);
                        Ellipse2D ball=new Ellipse2D.Double(x*sizeBlockX,y*sizeBlockY,sizeBlockX,sizeBlockY);
                        g2.draw(ball);
                        g2.fill(ball);
                        break;
                    case 1:

                        g2.setColor(Color.CYAN);
                        Ellipse2D pl1=new Ellipse2D.Double(x*sizeBlockX,y*sizeBlockY,sizeBlockX,sizeBlockY);
                        g2.draw(pl1);
                        g2.fill(pl1);
                        break;
                    case 2:
                        g2.setColor(Color.RED);
                        Ellipse2D pl2=new Ellipse2D.Double(x*sizeBlockX,y*sizeBlockY,sizeBlockX,sizeBlockY);
                        g2.draw(pl2);
                        g2.fill(pl2);
                        break;
                    case 3:
                        g2.setColor(Color.BLUE);
                        Ellipse2D pl3=new Ellipse2D.Double(x*sizeBlockX,y*sizeBlockY,sizeBlockX,sizeBlockY);
                        g2.draw(pl3);
                        g2.fill(pl3);
                        break;
                    case 4:
                        g2.setColor(Color.magenta);
                        Ellipse2D pl4=new Ellipse2D.Double(x*sizeBlockX,y*sizeBlockY,sizeBlockX,sizeBlockY);
                        g2.draw(pl4);
                        g2.fill(pl4);
                        break;
                }
            }
        }


    }

    public void setGameState(byte[] gameState) {
        for (int i=0; i<20; i++) {
            for (int j=0; j<20; j++) {
                map[i][j] = gameState[i*20 + j];
            }
        }
        teamScores[0] = gameState[20*20];
        teamScores[1] = gameState[20*20 + 1];
        scoreTeam.setText(teamScores[0]+":"+teamScores[1]);
    }
}
