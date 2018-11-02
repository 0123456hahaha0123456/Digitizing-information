
public class Juggler extends People  implements handSkill {
    Juggler(String name, int age, Job job, boolean onTV){
        super(name,age,job,onTV);
    }
    //Plate
    Plate plate = new Plate("plastic", shapePlate.OVAL);

    @Override
    void Action(){
        System.out.println("Action : Toss, Take ");
    }
    @Override
    public void toss(){
        System.out.println(getName() + " toss 5 " + plate.getKind() +' '+ plate.getShape() +" plate on the air");
    }

    @Override
    public void take(int tmp){
        if (tmp ==0 ) {
            throw new takeUncheckedEx();
        } else System.out.println(getName() + " take " + tmp +' '+  plate.getKind() + ' ' + plate.getShape() + " comeback hand");
    }

    @Override
    public String toString(){
        return this.getName() +' ' +' '+ this.getJob() +' '+ this.getOnTV() ;
    }

}
