public class Maly extends People implements canSee, canEat, canSpeak {
    Maly(String name,int age, Job job, boolean onTV){
        super(name,age,job,onTV);
    }

    @Override
    public void Action(){
        System.out.println("Action : speak, see, eat");
    }
    @Override
    public void see(){
        System.out.println(this.getName() + " is seeing TV bored");
    }

    @Override
    public void eat(FOODS tmp,SPEEDS _tmp){
        System.out.println(this.getName() + " is eating " + tmp+' ' + _tmp);
    }
    @Override
    public void speak(){
        System.out.println("I'm bored");
    }

    @Override
    public String toString() {
        return "Maly{}";
    }
}
