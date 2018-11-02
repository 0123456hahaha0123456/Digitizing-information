import java.io.*;
import java.net.*;

public class UDPSever extends Thread{
    private UDPSolve _duc;
    public UDPSever(String fileinput, String fileoutput) throws IOException{
        this._duc = new UDPSolve(fileinput,fileoutput);
    }

    public String solve(request _request) throws IOException {
        this._duc.UDPsolve(_request);
        return _duc.getStr();
    }

    public static void main(String[] args)throws Exception{
        DatagramSocket ds = new DatagramSocket(1302);
        UDPSever duc = new UDPSever(args[0], args[1]);
        //UDPSever duc = new UDPSever("D:\\Lab3\\lab3\\input.txt", "D:\\Lab3\\lab3\\output.txt");
        System.out.println("Sever is listening ");
        while (true) {
            byte[] b1 = new byte[1024];
            DatagramPacket dp1 = new DatagramPacket(b1, b1.length);
            ds.receive(dp1);
            System.out.println("Data is received");

            ByteArrayInputStream baos = new ByteArrayInputStream(b1);
            ObjectInputStream oos = new ObjectInputStream(baos);
            request _request = (request)oos.readObject();
            //-------------------------------------------
            severThread newThread = new severThread(ds,dp1,dp1.getPort(),_request, duc);
            new Thread(newThread).start();
        }
    }
}

class severThread implements Runnable {
    private DatagramSocket ds;
    private DatagramPacket dp1;
    private int port;
    private request _request;
    private UDPSever duc;
    public severThread(DatagramSocket _ds, DatagramPacket _dp1, int _port, request _request, UDPSever duc){
        this.ds  = _ds;
        this.dp1 = _dp1;
        this.port = _port;
        this._request = _request;
        this.duc = duc;
    }
    @Override
    public synchronized void run() {
        try {
            String result1 = duc.solve(_request);
            result _result = new result(result1);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(_result);
            oos.flush();

            byte[] b = baos.toByteArray();
            InetAddress ia = this.dp1.getAddress();
            DatagramPacket dp = new DatagramPacket(b, b.length, ia, port);
            this.ds.send(dp);
            System.out.println("Data is sent");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//5129934110456581901
// -8080612250036536549