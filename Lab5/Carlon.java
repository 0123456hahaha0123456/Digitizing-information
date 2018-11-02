public class Carlon extends People implements  canSee, canEat, canSpeak {
    Carlon(String name,int age, Job job, boolean onTV){
        super(name,age,job,onTV);
    }

    @Override
    public void Action(){
        System.out.println("Action : speak, see, eat");
    }
    @Override
    public void see(){
        System.out.println(this.getName() + " is seeing TV");
    }
    @Override
    public void speak(){
        System.out.println(this.getName() + "I'm a litte boy");
    }

    @Override
    public void eat(FOODS tmp,SPEEDS _tmp){
        System.out.println(this.getName() + " is eating "+ tmp + ' '+ _tmp );
    }

    @Override
    public String toString() {
        return "Carlon{}";
    }
}
