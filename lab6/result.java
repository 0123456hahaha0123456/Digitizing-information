import java.io.IOException;
import java.io.Serializable;
public class result implements Serializable{
    private static final long serialVersionUID = 5129934110456581901L;
    private String str;

    public result(String str){
        this.str = str;
    }

    public String getStr(){
        return this.str;
    }
    public static void main(String[] args){

    }
}
