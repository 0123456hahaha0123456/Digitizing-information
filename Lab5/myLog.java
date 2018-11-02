import java.io.FileWriter;
import java.io.IOException;

public class myLog {
    /**
     * Print message
     * @param printWriter is FileWriter
     * @param message is String
     * @throws IOException
     */
    public static void printlnMessage(FileWriter printWriter, String message)throws IOException{
        printWriter.write(message);
        printWriter.flush();
    }
}
