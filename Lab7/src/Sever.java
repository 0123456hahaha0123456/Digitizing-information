import java.awt.*;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


public class Sever {
    static String user ;
    static String password;

    private void init() throws IOException{
        BufferedReader fw;
        File login = new File("login.txt");
        FileInputStream fos = new FileInputStream(login);
        fw = new BufferedReader(new InputStreamReader(fos));
        user = fw.readLine();
        password = fw.readLine();
    }

    //check when client open
    private void checkClient(){
        checkThread duc = new checkThread();
        while (true){
            duc.run();
            if (duc.getOk()) {
                frameWork.sendToClient();
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        Sever duc = new Sever();
        duc.init();
        frameLogin.solve(user,password);
        duc.checkClient();
    }
}


class checkThread implements Runnable {
    private DatagramSocket ds;
    private boolean ok;

    public boolean getOk(){
        return this.ok;
    }

    public checkThread() {
        ok = false;
        try{
            ds = new DatagramSocket(8080);
        }catch (SocketException e){}
    }

    @Override
    public void run() {
        try {
            byte[] b1 = new byte[1024];
            DatagramPacket dp1 = new DatagramPacket(b1, b1.length);
            this.ds.receive(dp1);

            ByteArrayInputStream baos = new ByteArrayInputStream(b1);
            ObjectInputStream oos = new ObjectInputStream(baos);
            String test = (String) oos.readObject();
            if (test.equals("LinhAnhmaiyeuDuc")) this.ok = true;
            System.out.println("data recieved");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Sever is not responding. Please reconnect program!");
        }
    }
}