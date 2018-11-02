
import com.google.gson.*;
public class testJSON {
    private String s;
    public testJSON(String s){
        this.s = s;
    }

    private void solve(){
        Gson gson = new Gson();
        peopleJSON temp = gson.fromJson(this.s,peopleJSON.class);
        System.out.println(temp.getName() + temp.getAge()+ temp.getJob() + temp.getOnTV());
    }
}
