import java.io.*;
import java.util.ArrayList;
import java.lang.String;

public class UDPSolve {
    private peopleMap5 mp_;
    private String str = new String();
    private ArrayList<People> arr;
    private csvFormat tmp;
    String fileinput ;
    String fileoutput;
    public UDPSolve(String fileinput, String fileoutput) throws IOException{
        this.fileinput = fileinput;
        this.fileoutput = fileoutput;
        init();
    }

    private void init() throws IOException{

        String fileName = this.fileinput;
        this.tmp = new csvFormat();
        this.arr = tmp.readCsvFile(fileName);
        mp_ = new peopleMap5(arr);
    }
    public String getStr(){
        return this.str;
    }

    public void UDPsolve(request _request) throws IllegalStateException{
        int tg = _request.getTypeCommand();
        People duc = _request.getPeople();
        String key = _request.getKey();
        //UDPSolve temp = new UDPSolve();
        try{
                if (tg == 4) {
                    mp_.clear();
                    this.str = "All was cleared";
                }else
                if (tg == 6){
                    mp_ = new peopleMap5(arr);
                    this.str = "All object was loaded";
                }else
                if (tg == 5){
                    this.str = mp_.info();
                }else
                if (tg == 3){

                    if (duc.equals(null)) this.str= "Please write object as a json type"; else {
                        //People duc = loadPeople.loadNewPeople(jsonStr);
                        mp_.remove_all(duc);
                        this.str = mp_.info();
                    }
                }else
                if (tg == 1){

                    if (duc.equals(null)) this.str = "Please write object as a json type"; else{
                        mp_.add_if_min(duc);
                        this.str = mp_.info();
                    }
                }else
                if (tg == 2){
                    //String jsonStr = check(currentLine.substring(12));
                    if (duc.equals(null)) this.str = "Please write object as a json type"; else {
                        //People duc = loadPeople.loadNewPeople(jsonStr);
                        mp_.remove_lower(duc);
                        this.str = mp_.info();
                    }
                }else
                if (tg == 7){
                    if (key.equals(null)) this.str = "Please write key of object";
                    else {
                            mp_.remove(key);
                            this.str = mp_.info();
                    }
                }
                else
                if (tg == 8){
                    this.str = "For example People : {\"name\":\"duc\",\"job\":\"student\",\"age\":21,\"onTv\":\"true\"}";
                }
                else this.str = "Command doesn't exist";
//                tmp.writeCsvFile("D:\\Lab3\\lab3\\output.txt",mp_.getArr());
                  tmp.writeCsvFile(this.fileoutput,mp_.getArr());
                //tmp.writeCsvFile(args[1], mp_.getArr());

        }
        catch (IOException e) {
            this.str = "File was wrong";
        }
        catch (Exception e){
            this.str = "JSON format was wrong. Please write object as a json type";
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
                "remove key\n"+
                "help\n");
    }
}
/**
 * {"name":"duc","job":"student","age":21,"onTv":"true"}
 * {"name":"phanh","job":"frecken","age":37,"onTv":"false"}
 * {"name":"yen",     "job":    "student","age":20,    "onTv":      "false"}
 */
