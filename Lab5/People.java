//import net.sf.json.JSONObject;
import java.io.Serializable;
import java.lang.Object;
public class People implements Serializable{
    private static final long serialVersionUID = 5129934110456581901L;
    private String name;
    private Job job;
    private int age;
    private boolean onTV;
    private Feelings feeling;

    People(String name,int age, Job job, boolean onTV){
        this.name = name;
        this.job = job;
        this.age = age;
        this.onTV = onTV;
    }
    public int compareTo(People people){
        return (this.name+ this.age).compareTo(people.name+ people.age);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        People people = (People) obj;

        if (age != people.age) return false;
        if (onTV != people.onTV) return false;
        if (name != null ? !name.equals(people.name) : people.name != null) return false;
        if (job != people.job) return false;
        return feeling == people.feeling;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (job != null ? job.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (onTV ? 1 : 0);
        result = 31 * result + (feeling != null ? feeling.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "People{" +"name='" + name + '\'' +", job=" + job +", age=" + age +", onTV=" + onTV +", feeling=" + feeling +'}';
    }

    public String getName(){
        return this.name;
    }
    public Job getJob(){
        return this.job;
    }
    public int getAge(){
        return this.age;
    }
    public void setAge(int age) throws ageCheckedEx {
        if (age <0) throw  new ageCheckedEx();
        else this.age = age;
    }

    public String getOnTV(){
        if (this.onTV == true ) return "on TV";
        else return "in real life";
    }
    public void setFeeling(Feelings code){
        this.feeling = code;
    }
    public Feelings getFeelings(){
        return this.feeling;
    }
    public void setJob(String s){
        this.job = Job.valueOf(s.toUpperCase());
    }
    public void setOnTV(String s){
        this.onTV = Boolean.valueOf(s);
    }
    //abstract
    void Action(){
        System.out.println("");
    };

    //-----------------------------------------------
    static class YoungPeople{
        public void Print() {
            System.out.println("This is person, who is under 18 years old");
        }

        public static void canDo(){
            System.out.println("He 's watching TV");
        }
    }


    /**
     * convert json to people
     */
/*    public static People JSONtoPeop(JSONObject ob){
        String name = (String) ob.get("name");
        int age = (Integer) ob.get("age");
        String tg = (String) ob.get("job");
        Job job = Job.valueOf(tg.toUpperCase());
        boolean onTv = Boolean.valueOf((String)ob.get("onTv"));
        People duc = new People(name,age,job,onTv);
        return duc;
    }
*/
}
