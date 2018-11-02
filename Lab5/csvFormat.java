import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.TreeMap;
import java.io.FileReader;


public class csvFormat {
    private static final String COMMA_DELIMITER =",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV read
    public ArrayList<People> readCsvFile(String fileName) throws IOException {
        ArrayList<People> arr = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine();
            String currentLine;

            // read file line by line and write
            while ((currentLine = br.readLine()) != null) {
                int tg = 0;
                int i = 0;
                String name = null, job = null, age = null, onTv = null;
                while (currentLine.indexOf(',', tg) != -1) {
                    if (i == 0) {
                        name = currentLine.substring(tg, currentLine.indexOf(',', tg));
                    }
                    ;
                    if (i == 1) {
                        job = currentLine.substring(tg, currentLine.indexOf(',', tg)).toUpperCase();
                    }
                    ;
                    if (i == 2) {
                        age = currentLine.substring(tg, currentLine.indexOf(',', tg));
                    }
                    ;
                    tg = currentLine.indexOf(',', tg) + 1;
                    i++;
                }
                onTv = currentLine.substring(tg);
                arr.add(new People(name, Integer.parseInt(age), Job.valueOf(job), Boolean.valueOf(onTv)));
            }
        } catch (IOException e) {
            System.out.println("No file input");
        }
        return arr;
    }

    //CSV file header
    private static final String FILE_HEADER = "name,job,age,onTV";

    /**
     * @param fileName is String
     * @param treeMap is TreeMap of String and People
     * @throws IOException is IOException
     */
    public static void writeCsvFile(String fileName, TreeMap<String, People> treeMap) throws IOException{
        try{
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(FILE_HEADER+"\n");
            for(int i=0;i<treeMap.size();i++){
                String tg = treeMap.keySet().toArray(new String[treeMap.size()])[i];
                People tmp = treeMap.get(tg);
                fileWriter.write(tmp.getName() +',' + tmp.getJob() +','+ tmp.getAge()+','+tmp.getOnTV() + "\n");
            }
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e){
            System.out.println("No file output");
        }
    }
}
