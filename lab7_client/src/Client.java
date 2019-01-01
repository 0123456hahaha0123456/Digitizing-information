import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Client {
    ArrayList<Rectangle> arr;
    public static String colour[] = {"red","black","green","magenta","yellow"};

    private void init()throws IOException{
        csvFormat tmp = new csvFormat();
        ArrayList<People> people = tmp.readCsvFile("input.txt");


        arr = new ArrayList<Rectangle>(){};
        Random rand = new Random();
        Rectangle _duc ;//= new Rectangle(rand.nextInt(50)+20,rand.nextInt(50)+20,colour[4],0,0,people.get(2));

        for(int i=0;i<people.size();i++){
            _duc = new Rectangle(rand.nextInt(700) +20, rand.nextInt(700)+20,colour[i%5],rand.nextInt(50)+20,rand.nextInt(50)+20,people.get(i));
            arr.add(_duc);
        }

        frameClient duc = new frameClient(arr);
    }


    public static void main(String[] args){
        Client client = new Client();
        try{
            client.init();
        }catch(IOException e){}
    }

}
