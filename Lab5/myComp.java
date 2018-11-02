import java.util.Comparator;

public class myComp implements Comparator<String> {
    /**
     * compare two string. If the first one is bigger, then return 1. If the first one is smaller, then return -1. Else return 0
     * @param o1 String
     * @param o2 String
     * @return 0/1/-1
     */
    @Override
    public int compare(String o1, String o2){
        return o1.compareTo(o2);
    }
}
