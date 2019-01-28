import javax.sound.midi.Receiver;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Random;

public class Client {
    public static String colour[] = {"red","black","green","magenta","yellow"};
    ArrayList<Rectangle> arr = new ArrayList<Rectangle>();
    private void init()throws IOException{
      /*  csvFormat tmp = new csvFormat();
        ArrayList<People> people = tmp.readCsvFile("input.txt");


        arr = new ArrayList<Rectangle>(){};
        Random rand = new Random();
        Rectangle _duc ;//= new Rectangle(rand.nextInt(50)+20,rand.nextInt(50)+20,colour[4],0,0,people.get(2));

        for(int i=0;i<people.size();i++){
            _duc = new Rectangle(rand.nextInt(700) +20, rand.nextInt(700)+20,colour[i%5],rand.nextInt(50)+20,rand.nextInt(50)+20,people.get(i));
            arr.add(_duc);
        }

        frameClient duc = new frameClient(arr);*/
        frameClient duc = new frameClient(arr);

        DatagramSocket ds = new DatagramSocket(1302);
        while (true){
            clientThread newClientThread = new clientThread(ds,ds.getPort(),duc);
            newClientThread.run();
        }

       // frameClient duc = new frameClient(arr);
    }


    public static void main(String[] args){

        Client client = new Client();
        try{
            client.init();
        }catch(IOException e){}
    }

}

class clientThread implements Runnable {
    private frameClient duc;
    ArrayList<Rectangle> arr;
    public static String colour[] = {"red","black","green","magenta","yellow"};
    private DatagramSocket ds;
    private int port;

    public clientThread(DatagramSocket _ds, int _port,frameClient duc){
        this.duc = duc;
        this.ds  = _ds;
        this.port = _port;
    }
    @Override
    public synchronized void run() {
        try {
            byte[] b1 = new byte[1024];
            DatagramPacket dp1 = new DatagramPacket(b1, b1.length);
            ds.receive(dp1);

            ByteArrayInputStream baos = new ByteArrayInputStream(b1);
            ObjectInputStream oos = new ObjectInputStream(baos);
            ArrayList<People> people = (ArrayList<People>) oos.readObject();


            arr = new ArrayList<Rectangle>(){};
            Rectangle _duc ;

            for(int i=0;i<people.size();i++){
                People _tmp = people.get(i);
                _duc = new Rectangle(_tmp.getCorX(), _tmp.getCorY(),_tmp.getColor(),_tmp.getWidth(),_tmp.getHeight(),_tmp);
                arr.add(_duc);
            }
            duc.changeElements(arr);

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("Sever is not responding. Please reconnect program!");
        }
    }
}
