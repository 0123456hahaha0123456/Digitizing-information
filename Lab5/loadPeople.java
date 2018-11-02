import java.io.BufferedReader;
import com.google.gson.*;
import java.io.IOException;
import java.util.Scanner;
public class loadPeople {
    public static People loadNewPeople(BufferedReader fw,String s) throws IOException{
        String jsonPart=s;
        jsonChecker jsonKeeper = new jsonChecker();
        try{
            jsonKeeper.addString(jsonPart);
            while(!jsonKeeper.endCheck()){
                jsonPart = fw.readLine();
                jsonKeeper.addString(jsonPart);
            }
        }
        catch (IOException e){
            System.out.println("File was wrong");
        }

        Gson gson = new Gson();
        try {
            peopleJSON duc_ = gson.fromJson(jsonKeeper.getJson(), peopleJSON.class);
            People duc = convertJSON(duc_);
            return duc;
        } catch (Exception e){
            return null;
        }
    }

    public static People loadNewPeople(String s){
        Scanner sc = new Scanner(System.in);
        String jsonPart=s;
        jsonChecker jsonKeeper = new jsonChecker();

        jsonKeeper.addString(jsonPart);
        while(!jsonKeeper.endCheck()){
                jsonPart = sc.nextLine();
                jsonKeeper.addString(jsonPart);
        }

        Gson gson = new Gson();
        try {
            peopleJSON duc_ = gson.fromJson(jsonKeeper.getJson(), peopleJSON.class);
            People duc = convertJSON(duc_);
            return duc;
        } catch (Exception e){
            return null;
        }
    }

    private static People convertJSON(peopleJSON duc_){
        Job job = Job.valueOf(duc_.getJob().toUpperCase());
        boolean onTv = Boolean.valueOf(duc_.getOnTV());
        return(new People(duc_.getName(),duc_.getAge(),job,onTv));
    }
}
