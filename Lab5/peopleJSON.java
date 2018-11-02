public class peopleJSON {
    private String name;
    private String job;
    private int age;
    private String onTv;
    peopleJSON(String name,int age, String job, String onTv){
        this.name = name;
        this.job = job;
        this.age = age;
        this.onTv = onTv;
    }

    /**
     * Get name of people
     * @return name
     */
    public String getName(){
        return this.name;
    }

    /**
     * get Job of people
     * @return job
     */
    public String getJob(){
        return this.job;
    }

    /**
     * get Age of people
     * @return age
     */
    public int getAge(){
        return this.age;
    }

    /**
     * get getonTV of people
     * @return onTV
     */
    public String getOnTV(){
        return this.onTv;
    }
}
