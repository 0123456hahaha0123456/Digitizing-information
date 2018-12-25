import java.io.*;

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

    public static void main(String[] args) throws IOException{
        Sever duc = new Sever();
        duc.init();
        frameLogin.solve(user,password);
    }
}
