import java.util.*;

public class Solve_ {

    private float carlon_;
    private float maly_;
    private int tmp_;
    Solve_(int tmp_){
        this.tmp_ = tmp_;
    }
    List<People> tmp = new ArrayList<People>();

    public void add(People people){
        tmp.add(people);
    }
    public void process(){

        switch(tmp_){
            case 0 : case 1 :   tmp.get(0).setFeeling(Feelings.SAD);
                                break;
            case 2 : case 3 :   tmp.get(0).setFeeling(Feelings.NORMAL);
                                break;
            case 4 : case 5 :   tmp.get(0).setFeeling(Feelings.HAPPY);
                                break;
        }
        tmp.get(1).setFeeling(Feelings.SAD);
        if (tmp.get(0).getFeelings()==Feelings.HAPPY) tmp.get(1).setFeeling(Feelings.HAPPY);
        System.out.println(tmp.get(0).getName() +" feels " + tmp.get(0).getFeelings());
        System.out.println(tmp.get(1).getName() +" feels " + tmp.get(1).getFeelings());
    }
}
