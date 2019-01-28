import java.io.Serializable;

public class Request implements Serializable{
    private peopleMap5 mp;
    public Request(peopleMap5 mp ){
        this.mp = mp;
    }

    public peopleMap5 getMp() {
        return this.mp;
    }
}
