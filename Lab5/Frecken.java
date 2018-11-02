public class Frecken extends People implements  handWave, canSpeak{
    Frecken(String name,int age, Job job, boolean onTV){
        super(name,age,job,onTV);
    }
    @Override
    public void Action(){
        System.out.println("Action : wave hand, speak");
    }

    @Override
    public void wave(){
        System.out.println(getName() + " waved hand");
    }

    @Override
    public void speak(){
        System.out.println("She is saying something like a joke");
    }

    @Override
    public String toString() {
        return "Frecken{}";
    }

    // member
    class housework{
        public void yearsold(){
            System.out.println("She is " + getAge() +" years old");
        }
    }
}
