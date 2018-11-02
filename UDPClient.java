import java.io.*;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UDPClient {

    static private String check(String tmp){
        int i =0, j= tmp.length()-1;
        if (i>j) return "";
        while((tmp.charAt(i) == ' ') && (i<j)) i++;
        while((tmp.charAt(j) == ' ') && (j>0)) j--;
        if (i>j) return "";
        return  tmp.substring(i,j+1);
    }

    public static void main(String[] args) throws Exception{
        DatagramSocket ds = new DatagramSocket();
        //DatagramPacket dp1;
        try{
            Scanner sc = new Scanner(System.in);
            Main_5 tmp = new Main_5();
            System.out.println(tmp.getCommands());
            System.out.println("Please write down your command");
            while (true) {
                String str ;
                str = sc.nextLine();
                if (str.equals("exit")) break;
                str = check(str);
                if (str.startsWith("remove_all") || str.startsWith("add_if_min") || str.startsWith("remove_lower") || str.startsWith("remove")){
                    jsonChecker jsonKeeper = new jsonChecker();
                    jsonKeeper.addString(str);
                    while(!jsonKeeper.endCheck()){
                        str = sc.nextLine();
                        jsonKeeper.addString(str);
                    }
                    str = jsonKeeper.getJson();
                }

                request _request = new request(str);
                //byte[] b = str.getBytes();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(_request);
                oos.flush();

                byte[] b = baos.toByteArray();
                InetAddress ia = InetAddress.getLocalHost();
                DatagramPacket dp = new DatagramPacket(b, b.length, ia, 1302);
                ds.connect(ia,1302);
                ds.send(dp);
/*
                System.out.println(ds.getPort());
                if (ds.isConnected()==false) {
                    System.out.println("Sever is not responding. Please reconnect program!");
                }
                else {
                    byte[] b1 = new byte[1024];
                    dp1 = new DatagramPacket(b1, b1.length);
                    ds.receive(dp1);
                    String s = new String(dp1.getData()).trim();
                    System.out.println(s);
                }
*/

                clientThread newThread = new clientThread(ds,ds.getPort());
                new Thread(newThread).start();
            }
        }

        catch (NoSuchElementException e ){
            System.out.println("Please enter your commands");
            System.exit(1);
        }catch (IOException e){
            //e.printStackTrace();
            System.out.println("Sai me no roi");
            System.exit(1);
        }
    }
}


class clientThread implements Runnable {
    private DatagramSocket ds;
    private int port;

    public clientThread(DatagramSocket _ds, int _port){
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
            result _result = (result)oos.readObject();
            System.out.println(_result.getStr());

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("Sever is not responding. Please reconnect program!");
        }
    }
}