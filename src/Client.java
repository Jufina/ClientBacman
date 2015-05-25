import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

/**
 * Created by Julia on 24.05.2015.
 */
public class Client {
    /* *JULIA*/
    private static int mpl;

    public int numberClient;


    public static void main(String[] args) {
        Client client=new Client();
        try {
            client.run(Integer.parseInt(args[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run(int recport) throws IOException {

        int pacSize = 20*20+2;
        int sendport = 4444;
        byte data[] = new byte[pacSize];
        InetAddress addr = InetAddress.getLocalHost();

        DatagramPacket pac;
        pac = new DatagramPacket(data, data.length, addr, sendport);

        DatagramSocket s = new DatagramSocket(recport);
        System.out.println("Создан клиент #"+s.getPort());
        s.send(pac);
        s.receive(pac);
        for(int i=0; i<4; i++)
        {
            //System.out.println(pac.getData()[i]+"?"+recport);
            if(pac.getData()[i] == recport)
            {
                numberClient=i;
            }
        }
        s.receive(pac);
        System.out.println(numberClient+" - "+s.getPort());
        GameDrawing gameDrawing = new GameDrawing(numberClient);
        gameDrawing.setVisible(true);
        try {
            s.setSoTimeout(600);
            while (true) {

                gameDrawing.draw(pac.getData());
                data[0]=gameDrawing.getMovePlayer();
                pac.setData(data);
                s.send(pac);
                mpl=data[0];
                s.receive(pac);
            }
        } catch (SocketTimeoutException e) {
            // если время ожидания вышло
            System.out.println("Истекло время ожидания, прием данных закончен");
        }
    }
    /*JULIA */
    public static int getMpl() {
        return mpl;
    }


}
