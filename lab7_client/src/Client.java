
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;


public class Client {
    public static String colour[] = {"red","black","green","magenta","yellow"};
    ArrayList<Rectangle> arr = new ArrayList<Rectangle>();

    //use to sendData to sever to update elements when open client GUI
    private void sendData() throws IOException{
        DatagramSocket ds1 = new DatagramSocket();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        String str = "LinhAnhmaiyeuDuc";
        oos.writeObject(str);
        oos.flush();

        byte[] b = baos.toByteArray();
        InetAddress ia = InetAddress.getLocalHost();
        DatagramPacket dp = new DatagramPacket(b, b.length, ia, 8080);
        ds1.send(dp);
        System.out.println("data send");
    }

    private void init()throws IOException{

        frameClient duc = new frameClient(arr);
        sendData();
        DatagramSocket ds = new DatagramSocket(1302);

        while (true){
            clientThread newClientThread = new clientThread(ds,ds.getPort(),duc);
            newClientThread.run();
        }
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
