import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static  void main1(String[] args){//} throws ageCheckedException {

        //Carlon
        Carlon carlon = new Carlon("Carlon", 18, Job.PUPIL, false);
        System.out.println(carlon);
        carlon.Action();
        carlon.speak();
        FOODS tmp = FOODS.getFoodbyCode(3);
        SPEEDS tmp_ = SPEEDS.Fast;
        carlon.eat(tmp, tmp_);
        carlon.see();
        try{
            carlon.setAge(carlon.getAge());
        }
        catch (ageCheckedEx e){
            System.out.println(e.getMessage());
        }
        //Maly
        Maly maly = new Maly("Maly", 17, Job.STUDENT, false);
        System.out.println(maly);
        maly.Action();
        maly.speak();
        FOODS _tmp = FOODS.getFoodbyCode(2);
        SPEEDS _tmp_ = SPEEDS.Slow;
        maly.eat(_tmp, _tmp_);
        maly.see();
        System.out.println(maly.equals(new Maly("Maly", 17, Job.STUDENT, false)));

        //Bok
        Frecken Bok = new Frecken("Bok", 40, Job.FRECKEN, true);
        System.out.println(Bok);
        Bok.Action();
        Bok.speak();
        Bok.wave();
        //Duc
        Juggler duc = new Juggler("Duc", 20, Job.JUNGGLER, true);
        System.out.println(duc);
        duc.Action();
        duc.toss();
        //int tmp_1=5;
        int tmp_1 = (int) Math.round(Math.random() * 6)-1;// celi(2.1) -> 3; floor(2.6) -> 2
        duc.take(tmp_1) ;

        // test
        // local class
        class Solve {

            private float carlon_;
            private float maly_;
            private int tmp_;
            Solve(){
                this.tmp_ = tmp_1;
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

        Solve test = new Solve();
        test.add(carlon);
        test.add(maly);
        test.add(duc);
        test.process();

        // lab 4

        // static nested class
        // need to create instance bz Print is non-static method
        People.YoungPeople  young = new People.YoungPeople();
        young.Print();

        // no need to create instance bz canDo is static method
        People.YoungPeople.canDo();

        //non-static nested class
        //anonymous
        canEat example = (food, speeds) -> System.out.println("All of them can eat " + food + ' ' + speeds);
        example.eat(FOODS.SOUP, SPEEDS.Fast);

        //member
        Frecken yen = new Frecken("Yen",70, Job.FRECKEN, true);
        Frecken.housework _yen = yen.new housework();
        _yen.yearsold();

    }
}
