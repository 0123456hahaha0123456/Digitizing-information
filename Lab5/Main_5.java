import java.io.*;
import java.util.ArrayList;
import java.lang.String;
import com.google.gson.*;
public class Main_5 {

    private String str =new String();

    public String getStr(){
        return this.str;
    }
    public String getCommands(){
        return avaiableCommands();
    }
    public static void main(String[] args) throws IOException, IllegalStateException{

        FileWriter fw = new FileWriter(FileDescriptor.out);

        //fileName = "D:\\Lab3\\lab3\\input.txt";
        //if (args[0]==null) System.out.println("You need to write file input to run");
        /*try{
            args[0].equals(null);
        }catch (Exception e){
            myLog.printlnMessage(fw,"Please write down file input \n");
        }

        try{
            args[1].equals(null);
        }catch (Exception e){
            myLog.printlnMessage(fw,"Please write down file output after file input \n");
        }
        */
        String fileName = "D:\\Lab3\\lab3\\input.txt";
        //String fileName = args[0];
        csvFormat tmp = new csvFormat();
        ArrayList<People> arr = tmp.readCsvFile(fileName);
        if (arr == null) {
            myLog.printlnMessage(fw, "Nothing happend");
            return;
        }

        peopleMap5 mp_ = new peopleMap5(arr);

        Main_5 temp = new Main_5();
        temp.solve(fw);

        Gson gson = new Gson();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String currentLine_;
            while ((currentLine_ = br.readLine())!=null){
                String currentLine = check(currentLine_);

                if (currentLine.equals("clear")){
                    mp_.clear();
                    System.out.println("All was cleared");
                }else
                if (currentLine.equals("load")){
                    mp_ = new peopleMap5(arr);
                    System.out.println("All object was loaded");
                }else
                if (currentLine.equals("info")){
                    mp_.info_5();
                }else
                if (currentLine.startsWith("remove_all")){
                    String jsonStr = check(currentLine.substring(10));
                    if (jsonStr.equals(null)) System.out.println("Please write object as a json type"); else {
                        People duc = loadPeople.loadNewPeople(br,jsonStr);
                        mp_.remove_all(duc);
                    }
                }else
                if (currentLine.startsWith("add_if_min")){
                    String jsonStr = check(currentLine.substring(10));
                    if (jsonStr.equals(null)) System.out.println("Please write object as a json type"); else{
                        People duc = loadPeople.loadNewPeople(br,jsonStr);
                        mp_.add_if_min(duc);
                    }
                }else

                if (currentLine.startsWith("remove_lower")){
                    String jsonStr = check(currentLine.substring(12));
                    if (jsonStr.equals(null)) System.out.println("Please write object as a json type"); else {
                        People duc = loadPeople.loadNewPeople(br,jsonStr);
                        mp_.remove_lower(duc);
                    }
                }else
                if (currentLine.startsWith("remove")){
                    if (currentLine.equals("remove")) System.out.println("Please write key of object");else {
                        String key = check(currentLine.substring(6));
                        if (key.equals(null)) System.out.println("Please write key of object");
                        else {
                            mp_.remove(key);
                        }
                    }
                }else System.out.println("Command doesn't exist");
                tmp.writeCsvFile("D:\\Lab3\\lab3\\output.txt",mp_.getArr());
                //tmp.writeCsvFile(args[1], mp_.getArr());
            }
        }
        catch (IOException e) {
            myLog.printlnMessage(fw,"File was wrong");
        }
        catch (Exception e){
               myLog.printlnMessage(fw,"JSON format was wrong \nPlease write object as a json type");
        }
    }

    public void solve(FileWriter fw) throws IOException{
        myLog.printlnMessage(fw,this.avaiableCommands());
    }

    /**
     * Delete space at the begin and at the end of string
     * @param tmp is String
     * @return String
     */
    static private String check(String tmp){
        int i =0, j= tmp.length()-1;
        if (i>j) return "";
        while(tmp.charAt(i) == ' ') i++;
        while(tmp.charAt(j) == ' ') j--;
        return  tmp.substring(i,j+1);
    }

    /**
     * Show commands that we have
     * @return String
     */
    private String avaiableCommands(){
        return("All commands that we have \n" +
                "add_if_min People\n" +
                "remove_lower People\n" +
                "remove_all People\n" +
                "clear\n" +
                "info\n" +
                "load\n" +
                "remove key\n");
    }
}
/**
 * {"name":"duc","job":"student","age":21,"onTv":"true"}
 * {"name":"phanh","job":"frecken","age":37,"onTv":"false"}
 * {"name":"yen",     "job":    "student","age":20,    "onTv":      "false"}
 */
