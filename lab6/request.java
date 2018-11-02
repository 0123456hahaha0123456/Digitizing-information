import java.io.IOException;
import java.io.Serializable;

public class request implements Serializable{
    private static final long serialVersionUID = 5129934110456581901L;
    private String str;
    private int typeCommand ;
    private People people;
    private String key;
    public request(String str){
        this.str = str;
        solve();
    }

    public void setKey(String key){
        this.key = key;
    }
    public String getKey(){return this.key;}
    public People getPeople() {
        return people;
    }
    public int getTypeCommand(){
        return this.typeCommand;
    }
    public void setPeople(People people){
        this.people = people;
    }
    private void setTypeCommand(int type){
        this.typeCommand = type;
    }
    private void solve(){
        try{
            String _str = check(this.str);
            if (_str.startsWith("add_if_min")) {
                setTypeCommand(1);
                String jsonStr = check(_str.substring(10));
                if (jsonStr.equals(null)) setPeople(null);
                else {
                    People duc = loadPeople.loadNewPeople(jsonStr);
                    setPeople(duc);
                }
                return;
            }
            if (_str.startsWith("remove_lower")){
                setTypeCommand(2);
                String jsonStr = check(_str.substring(12));
                if (jsonStr.equals(null)) setPeople(null);
                else {
                    People duc = loadPeople.loadNewPeople(jsonStr);
                    setPeople(duc);
                }
                return;
            }
            if (_str.startsWith("remove_all")){
                setTypeCommand(3);
                String jsonStr = check(_str.substring(10));
                if (jsonStr.equals(null)) setPeople(null);
                else {
                    People duc = loadPeople.loadNewPeople(jsonStr);
                    setPeople(duc);
                }
                return;
            }
            if (_str.equals("clear")){
                setTypeCommand(4);
                return;
            }
            if (_str.equals("info")){
                setTypeCommand(5);
                return;
            }
            if (_str.equals("load")){
                setTypeCommand(6);
                return;
            }
            if (_str.startsWith("remove")){
                setTypeCommand(7);
                if (_str.equals("remove")) { setKey(null); }
                else {
                    key = check(_str.substring(6));
                    if (key.equals((Object)null)) { setKey(null);}
                    else { setKey(_str);}
                }
            }
            if (_str.equals("help")){
                setTypeCommand(8);
            }
        }catch (Exception e){
            this.str = "JSON format was wrong. Please write object as a json type";
        }

    }

    static private String check(String tmp){
        int i =0, j= tmp.length()-1;
        if (i>j) return "";
        while(tmp.charAt(i) == ' ') i++;
        while(tmp.charAt(j) == ' ') j--;
        return  tmp.substring(i,j+1);
    }
}
