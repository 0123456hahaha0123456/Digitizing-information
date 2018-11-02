import java.util.ArrayList;
import java.util.TreeMap;
import java.lang.String;
import java.io.*;
public class peopleMap5 {
    private TreeMap<String, People> arr = new TreeMap<String,People>(new myComp());

    peopleMap5(ArrayList<People> arr_){
        for(int i=0;i<arr_.size();i++){
            arr.put(arr_.get(i).getName()+ arr_.get(i).getAge(), arr_.get(i));
        }
    }
    /**
     *Clean all collection
     */
    public void clear(){
        arr.clear();
    }

    /**
     * Remove People from Collection
     * @param ke is a String
     */
    public void remove(String ke){
        arr.remove(ke);
        //System.out.println("Successfully!");
    }

    /**
     * remove all peoples from Collection which is smaller than given People
     * @param duc is People
     */
    public void remove_lower(People duc){
        ArrayList<String> temp = new ArrayList<>();
        for(int i=0;i<arr.size();i++){
            String tg = arr.keySet().toArray(new String[arr.size()])[i];
            if (arr.get(tg).compareTo(duc)< 0) temp.add(tg);
        }
        for(int i=0;i<temp.size();i++) arr.remove(temp.get(i));
        //System.out.println("Successfully!");
    }

    /**
     * remove_all peoples from Collection which is equals then given People
     * @param duc is People
     */

    public void remove_all(People duc){
        ArrayList<String> temp = new ArrayList<>();
        for(int i=0;i<arr.size();i++){
            String tg = arr.keySet().toArray(new String[arr.size()])[i];
            if (arr.get(tg).compareTo(duc)== 0) temp.add(tg);
        }
        for(int i=0;i<temp.size();i++) arr.remove(temp.get(i));
        //System.out.println("Successfully!");
    }

    /**
     * add People into Collection if this given People is smaller than minimum People
     * @param  duc is People
     */
    public void add_if_min(People duc){
        boolean bool = true;
        for(int i=0;i<arr.size();i++){
            String tg = arr.keySet().toArray(new String[arr.size()])[i];
            if (arr.get(tg).compareTo(duc) < 0) bool = false;
        }
        if (bool){
            arr.put(duc.getName()+ duc.getAge(), duc);
        }
        //System.out.println("Successfully!");
    }

    /**
     * Print info about Collection
     */
    public String info(){
        return("Kind : TreeMap \n" +
                "Size of Collection : " + arr.size());
    }
    public void info_5(){
        System.out.println("Kind : TreeMap \n" +
                "Size of Collection : " + arr.size());
    }
    /**
     * Write down colection into file output and save it
     * @throws  IOException is IOException
     * @param  fileName is File
     */
    public void save(File fileName) throws  IOException{
        FileWriter fileWriter = new FileWriter(fileName);

    }

    /**
     * @duc. HELLO WORLD
     * @return arr
     */
    public TreeMap<String,People> getArr(){
        return this.arr;
    }
}