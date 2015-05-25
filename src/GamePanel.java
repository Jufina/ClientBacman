import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Julia on 24.05.2015.
 */
public class GamePanel extends JPanel {
    private byte[][] map;
    private byte[] teamScores;
    private JLabel scoreTeam;
    private byte[] movePlayer;
    Image[] img1;
    Image[] img2;
    Image[] img3;
    Image[] img4;

    GamePanel() {
        movePlayer = new byte[4];
        scoreTeam=new JLabel("0:0");
        Font fnt=new Font("Arial", Font.BOLD|Font.ITALIC, 30);
        scoreTeam.setFont(fnt);
        add(scoreTeam);
        map = new byte[20][20];
        teamScores = new byte[2];

        try {
            img1=new Image[4];
            img2=new Image[4];
            img3=new Image[4];
            img4=new Image[4];
            for(int i=0; i<4; i++) {
                img1[i] = ImageIO.read(new File("picture_1_"+i+".gif"));
            }
            for(int i=0; i<4; i++) {
                img2[i] = ImageIO.read(new File("picture_2_"+i+".gif"));
            }
            for(int i=0; i<4; i++) {
                img3[i] = ImageIO.read(new File("picture_3_"+i+".gif"));
            }
            for(int i=0; i<4; i++) {
                img4[i] = ImageIO.read(new File("picture_4_"+i+".gif"));
            }

        }
        catch(IOException ex)
        {
            Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int frW,frH;
        frW = getWidth();
        frH= getHeight();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int sizeBlockX=frW/20;
        int sizeBlockY=frH/20;
        //System.out.println(sizeBlockX+" , "+sizeBlockY);
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
                        g.drawImage(img1[movePlayer[0]], x*sizeBlockX, y*sizeBlockY, null);
                        break;
                    case 2:
                        g.drawImage(img2[movePlayer[1]], x*sizeBlockX, y*sizeBlockY, null);
                        break;
                    case 3:
                        g.drawImage(img3[movePlayer[2]], x * sizeBlockX, y * sizeBlockY, null);
                        break;
                    case 4:
                        /*
                        g2.setColor(Color.magenta);
                        Ellipse2D pl4=new Ellipse2D.Double(x*sizeBlockX,y*sizeBlockY,sizeBlockX,sizeBlockY);
                        g2.draw(pl4);
                        g2.fill(pl4);
                        */

                        g.drawImage(img4[movePlayer[3]], x*sizeBlockX, y*sizeBlockY, null);


                        break;
                    default:
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
        System.arraycopy(gameState, 20*20 + 2, movePlayer, 0, 4);
    }
}
